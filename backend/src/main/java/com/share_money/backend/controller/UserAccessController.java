    package com.share_money.backend.controller;

    import com.share_money.backend.dto.user.LoginRequestDTO;
    import com.share_money.backend.dto.user.LoginResponseDTO;
    import com.share_money.backend.dto.user.UserRegisterDTO;
    import com.share_money.backend.service.UserService;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

    @RequiredArgsConstructor
    @RestController
    public class UserAccessController {

        private final UserService userService;

        @PostMapping("/register")
        public ResponseEntity<String> registerAccountForUser(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
            userService.createAccountForUser(userRegisterDTO);
            return ResponseEntity.ok("Registered Successfully");
        }

        @PostMapping("/login")
        public ResponseEntity<LoginResponseDTO> loginIntoAccount(@Valid @RequestBody LoginRequestDTO loginRequestDTO){

            return ResponseEntity.ok(userService.loginUser(loginRequestDTO));
        }

    }
