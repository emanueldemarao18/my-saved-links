package com.github.emanuel.application.service;

import com.github.emanuel.api.dto.request.LinkRequestDTO;
import com.github.emanuel.api.dto.request.PanelRequestDTO;
import com.github.emanuel.api.dto.request.PanelUpdateRequestDTO;
import com.github.emanuel.api.dto.response.PanelResponseDTO;
import com.github.emanuel.api.dto.response.PanelUpdateResponseDTO;
import com.github.emanuel.application.mapper.PanelMapper;
import com.github.emanuel.infrastructure.entity.Link;
import com.github.emanuel.infrastructure.entity.Panel;
import com.github.emanuel.infrastructure.repository.PanelRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class PanelService {

    @Inject
    PanelRepository panelRepository;

    @Inject
    PanelMapper panelMapper;



    public List<PanelResponseDTO> listAll() {
        List<PanelResponseDTO> list = new ArrayList<>();
        PanelMapper panelMapper1 = panelMapper;
        for (Panel panel : panelRepository.listAll()) {
            PanelResponseDTO panelResponseDTO = panelMapper1.mapToResponseDTO(panel);
            list.add(panelResponseDTO);
        }
        return list;
    }

    public Optional<PanelResponseDTO> findById(Long id) {
        return panelRepository.findByIdOptional(id)
                .map(panelMapper::mapToResponseDTO);
    }

    @Transactional
    public PanelResponseDTO create(PanelRequestDTO panelRequestDTO) {
        Panel panel = panelMapper.mapToEntity(panelRequestDTO);
        panelRepository.persist(panel);
        return panelMapper.mapToResponseDTO(panel);
    }

    @Transactional
    public Optional<PanelUpdateResponseDTO> update(Long id, PanelUpdateRequestDTO panelUpdateRequestDTO) {
        Panel existingPanel = panelRepository.findById(id);
        if (existingPanel != null) {
            existingPanel.setTitle(panelUpdateRequestDTO.title());
            existingPanel.setIcon(panelUpdateRequestDTO.icon());

           /* existingPanel.getLinks().clear();

            if (panelRequestDTO.links() != null) {
                for (LinkRequestDTO linkRequest : panelRequestDTO.links()) {
                    Link link = new Link(linkRequest.text(), linkRequest.url(), linkRequest.icon(), linkRequest.color(), linkRequest.textColor());
                    link.setPanel(existingPanel);
                    existingPanel.getLinks().add(link);
                }
            }*/

            panelRepository.persist(existingPanel);
            return Optional.of(panelMapper.mapToUpdateResponseDTO(existingPanel));
        }

        return Optional.empty();
    }


    @Transactional
    public boolean delete(Long id) {
        return panelRepository.deleteById(id);
    }

    @Transactional
    public Long deleteAll() {
        return panelRepository.deleteAll();
    }
}
