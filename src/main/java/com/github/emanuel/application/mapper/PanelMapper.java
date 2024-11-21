package com.github.emanuel.application.mapper;

import com.github.emanuel.api.dto.request.LinkRequestDTO;
import com.github.emanuel.api.dto.request.PanelRequestDTO;
import com.github.emanuel.api.dto.response.PanelResponseDTO;
import com.github.emanuel.api.dto.response.PanelUpdateResponseDTO;
import com.github.emanuel.infrastructure.entity.Link;
import com.github.emanuel.infrastructure.entity.Panel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PanelMapper {

    @Inject
    LinkMapper linkMapper;

    public Panel mapToEntity(PanelRequestDTO dto) {
        Panel panel = new Panel();
        panel.setTitle(dto.title());
        panel.setIcon(dto.icon());

       /* List<Link> links = dto.links().stream()
                .map(linkDto -> {
                    Link link = linkMapper.mapToEntity(linkDto, panel);
                    return link;
                })
                .collect(Collectors.toList());

        panel.setLinks(links);*/
        return panel;
    }


    private Link mapToEntity(LinkRequestDTO linkRequestDTO) {
        Link link = new Link();
        link.setText(linkRequestDTO.text());
        link.setUrl(linkRequestDTO.url());
        link.setIcon(linkRequestDTO.icon());
        link.setColor(linkRequestDTO.color());
        link.setTextColor(linkRequestDTO.textColor());
        return link;
    }

    public PanelResponseDTO mapToResponseDTO(Panel panel) {
        List<Link> links = panel.getLinks();
        return new PanelResponseDTO(
                panel.getId(),
                panel.getTitle(),
                panel.getIcon(),
                links
        );
    }

    public PanelUpdateResponseDTO mapToUpdateResponseDTO(Panel panel) {
        return new PanelUpdateResponseDTO(
                panel.getId(),
                panel.getTitle(),
                panel.getIcon()
        );
    }
}
