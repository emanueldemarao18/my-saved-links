package com.github.emanuel.api.dto.response;

public record LinkResponseDTO(
        Long id,
        String text,
        String url,
        String icon,
        String color,
        String textColor
) {
}
