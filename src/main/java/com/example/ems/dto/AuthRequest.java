package com.example.ems.dto;

public record AuthRequest(
        String username,
        String password
) {
}
