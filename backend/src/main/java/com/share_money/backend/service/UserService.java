package com.share_money.backend.service;

import com.share_money.backend.dto.user.GetAllUserByNameResponseDTO;
import com.share_money.backend.dto.user.LoginRequestDTO;
import com.share_money.backend.dto.user.LoginResponseDTO;
import com.share_money.backend.dto.user.UserRegisterDTO;
import com.share_money.backend.exception.UserAlreadyExistException;
import com.share_money.backend.model.User;
import com.share_money.backend.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public void createAccountForUser(UserRegisterDTO userRegisterDTO) {
        boolean userExist = userRepo.existsByUsername(userRegisterDTO.username());
        boolean emailExist = userRepo.existsByEmail(userRegisterDTO.email());
        if (userExist || emailExist){
            throw new UserAlreadyExistException("Username or email already exist");
        }
        User user = User.builder()
                        .username(userRegisterDTO.username())
                                .email(userRegisterDTO.email())
                .password(bCryptPasswordEncoder.encode(userRegisterDTO.password()))
                                        .dateOfBirth(userRegisterDTO.dateOfBirth())
                                                .build();
        userRepo.save(user);
    }

    public LoginResponseDTO loginUser(LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginRequestDTO.username(),loginRequestDTO.password()));

        User user = userRepo.findByUsername(loginRequestDTO.username());
        String token = jwtService.generateToken(user,user.getUserId());
        return new LoginResponseDTO(token);

    }


    public Page<GetAllUserByNameResponseDTO> getUserBySearch(Pageable pageable, String search, Long userId) {
        return userRepo.findUserByUsernameExceptUserId(pageable,search,userId);
    }
}
