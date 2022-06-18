package LanguageDetection.application.services;

import LanguageDetection.domain.ValueObjects.ERole;
import LanguageDetection.domain.ValueObjects.RoleId;
import LanguageDetection.domain.entities.Role;

import LanguageDetection.infrastructure.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleService() {
    }

    public Role createAndSaveRole(RoleId id, ERole name ) {
        Role role = new Role(id,name);

        return roleRepository.save(role);
    }
}