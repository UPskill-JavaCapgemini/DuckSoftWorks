package LanguageDetection.Domain.model;

import LanguageDetection.domain.model.Role;
import LanguageDetection.domain.model.ValueObjects.ERole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    public void shouldCreateANewAdminRole(){
        Role role = new Role(ERole.ROLE_ADMIN);

        assertNotNull(role);
    }

    @Test
    public void ensureItsPossibleToGetNameFromRole(){
        Role role = new Role(ERole.ROLE_ADMIN);

        assertEquals(role.getName(), ERole.ROLE_ADMIN);
    }

}