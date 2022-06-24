package LanguageDetection.domain.model;

import LanguageDetection.domain.model.ValueObjects.ERole;

import java.util.Optional;

public interface IRoleRepository {
    public Role saveRole(Role role);

    public Optional<Role> findRoleByName(ERole role);
}
