package com.github.emanuel.api.dto.request;


public record LinkRequestDTO(
        String text,
        String url,
        String icon,
        String color,
        String textColor,
        Long panelId
) {
}
