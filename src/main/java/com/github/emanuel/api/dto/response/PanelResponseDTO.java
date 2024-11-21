package com.github.emanuel.api.dto.response;

import com.github.emanuel.infrastructure.entity.Link;

import java.util.List;

public record PanelResponseDTO(
        Long id,
        String title,
        String icon,
         List<Link>links
) {
}
