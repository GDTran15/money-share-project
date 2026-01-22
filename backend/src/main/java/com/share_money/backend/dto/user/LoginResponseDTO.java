package com.share_money.backend.dto.user;

public record LoginResponseDTO(
        String token,
        String username
) {
}
