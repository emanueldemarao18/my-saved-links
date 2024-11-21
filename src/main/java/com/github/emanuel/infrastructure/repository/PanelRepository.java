package com.github.emanuel.infrastructure.repository;

import com.github.emanuel.infrastructure.entity.Panel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanelRepository implements PanacheRepository<Panel> {
}
