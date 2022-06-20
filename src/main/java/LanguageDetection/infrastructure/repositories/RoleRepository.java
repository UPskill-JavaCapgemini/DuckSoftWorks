package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.model.Role;
import LanguageDetection.domain.model.ValueObjects.ERole;
import LanguageDetection.infrastructure.repositories.JPARepositories.RoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public class RoleRepository {
    @Autowired
    RoleJpaRepository roleJpaRepository;



    public Role save(Role role) {
        Role savedRole = roleJpaRepository.save(role);

        return savedRole;
    }

    public Optional<Role> findByName(ERole name) {
        Optional<Role> opRole = roleJpaRepository.findByName(name);

        if (opRole.isPresent()) {
            return opRole;
        } else
            return Optional.empty();
    }
}

