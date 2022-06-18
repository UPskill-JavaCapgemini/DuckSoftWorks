package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.ValueObjects.ERole;
import LanguageDetection.domain.entities.Role;
import LanguageDetection.infrastructure.repositories.JPARepositories.RoleJpaRepository;
import LanguageDetection.infrastructure.repositories.JPARepositories.assemblers.RoleDomainDataAssembler;
import LanguageDetection.infrastructure.repositories.JPARepositories.jpa.RoleJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public class RoleRepository {
    @Autowired
    RoleJpaRepository roleJpaRepository;

    @Autowired
    RoleDomainDataAssembler roleAssembler;

    public Role save(Role role) {
        RoleJpa roleJpa = roleAssembler.toData(role);

        RoleJpa savedRoleJpa = roleJpaRepository.save(roleJpa);

        return roleAssembler.toDomain(savedRoleJpa);
    }

    public Optional<Role> findByName(ERole name) {
        Optional<RoleJpa> opRole = roleJpaRepository.findByName(name);

        if (opRole.isPresent()) {
            Role role = roleAssembler.toDomain(opRole.get());
            return Optional.of(role);
        } else
            return Optional.empty();
    }
}

