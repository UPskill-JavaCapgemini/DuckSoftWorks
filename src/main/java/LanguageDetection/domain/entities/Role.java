package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.ERole;
import LanguageDetection.domain.ValueObjects.RoleId;
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