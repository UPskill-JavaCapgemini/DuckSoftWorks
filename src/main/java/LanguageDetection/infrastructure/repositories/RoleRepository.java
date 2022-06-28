package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.model.IRoleRepository;
import LanguageDetection.domain.model.Role;
import LanguageDetection.domain.model.ValueObjects.ERole;
import LanguageDetection.infrastructure.repositories.JPARepositories.RoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public class RoleRepository implements IRoleRepository {
    @Autowired
    RoleJpaRepository roleJpaRepository;



    public Role saveRole(Role role) {
        Role savedRole = roleJpaRepository.save(role);

        return savedRole;
    }

    public Optional<Role> findRoleByName(ERole name) {
        Optional<Role> opRole = roleJpaRepository.findByName(name);

        if (opRole.isPresent()) {
            return opRole;
        } else
            return Optional.empty();
    }

    @Override
    public Long countPersistedRoles() {
        Long persistedRoleCount = roleJpaRepository.count();
        return persistedRoleCount;
    }
}

