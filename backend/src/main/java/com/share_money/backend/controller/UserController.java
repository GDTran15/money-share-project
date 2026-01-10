package com.share_money.backend.controller;

import com.share_money.backend.dto.user.GetAllUserByNameResponseDTO;
import com.share_money.backend.model.User;
import com.share_money.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;



    @GetMapping()
    public ResponseEntity<PagedModel<GetAllUserByNameResponseDTO>> getUsersByName(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "search", defaultValue = "") String search,
            @AuthenticationPrincipal User user

    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<GetAllUserByNameResponseDTO> pagedModel = userService.getUserBySearch(pageable,search,user.getUserId());
        return ResponseEntity.ok(new PagedModel<>(pagedModel));
    }





}


