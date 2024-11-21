package com.github.emanuel.application.service;

import com.github.emanuel.api.dto.request.LinkRequestDTO;
import com.github.emanuel.api.dto.response.LinkResponseDTO;
import com.github.emanuel.application.mapper.LinkMapper;
import com.github.emanuel.infrastructure.entity.Link;
import com.github.emanuel.infrastructure.entity.Panel;
import com.github.emanuel.infrastructure.repository.LinkRepository;
import com.github.emanuel.infrastructure.repository.PanelRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class LinkService {

    @Inject
    LinkRepository linkRepository;

    @Inject
    PanelRepository panelRepository;

    @Inject
    LinkMapper linkMapper;

    public List<LinkResponseDTO> listAll() {
        return linkRepository.listAll().stream()
                .map(linkMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<LinkResponseDTO> findById(Long id) {
        return linkRepository.findByIdOptional(id)
                .map(linkMapper::mapToResponseDTO);
    }

    @Transactional
    public LinkResponseDTO create(LinkRequestDTO linkRequestDTO) {
        if (linkRequestDTO.panelId() == null) {
            throw new IllegalArgumentException("Panel ID must be provided to create a link.");
        }

        Panel panel = panelRepository.findById(linkRequestDTO.panelId());
        if (panel == null) {
            throw new EntityNotFoundException("Panel not found with id: " + linkRequestDTO.panelId());
        }

        Link link = linkMapper.mapToEntity(linkRequestDTO, panel);
        linkRepository.persist(link);

        return linkMapper.mapToResponseDTO(link);
    }



    @Transactional
    public Optional<LinkResponseDTO> update(Long id, LinkRequestDTO linkRequestDTO) {
        Link existingLink = linkRepository.findById(id);
        if (existingLink != null) {
            existingLink.setText(linkRequestDTO.text());
            existingLink.setUrl(linkRequestDTO.url());
            existingLink.setIcon(linkRequestDTO.icon());
            existingLink.setTextColor(linkRequestDTO.textColor());
            existingLink.setColor(linkRequestDTO.color());
            return Optional.of(linkMapper.mapToResponseDTO(existingLink));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean delete(Long id) {
        return linkRepository.deleteById(id);
    }
}
