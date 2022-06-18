package LanguageDetection.domain.model;

import LanguageDetection.domain.model.ValueObjects.ERole;
import LanguageDetection.domain.model.ValueObjects.RoleId;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Role {
    @Getter
    private RoleId id;
    @Getter
    private ERole name;

    public Role(RoleId roleId, ERole name) {
        this.id = roleId;
        this.name = name;
    }
}