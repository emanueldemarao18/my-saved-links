package com.github.emanuel.application.mapper;

import com.github.emanuel.api.dto.request.LinkRequestDTO;
import com.github.emanuel.api.dto.response.LinkResponseDTO;
import com.github.emanuel.infrastructure.entity.Link;
import com.github.emanuel.infrastructure.entity.Panel;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LinkMapper {

    public Link mapToEntity(LinkRequestDTO dto, Panel panel) {
        Link link = new Link();
        link.setText(dto.text());
        link.setUrl(dto.url());
        link.setIcon(dto.icon());
        link.setTextColor(dto.textColor());
        link.setColor(dto.color());
        link.setPanel(panel);
        return link;
    }


    public LinkResponseDTO mapToResponseDTO(Link link) {
        return new LinkResponseDTO(
                link.getId(),
                link.getText(),
                link.getUrl(),
                link.getIcon(),
                link.getColor(),
                link.getTextColor()
        );
    }
}
