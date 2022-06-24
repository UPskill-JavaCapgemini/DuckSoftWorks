package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.model.Role;
import LanguageDetection.domain.model.ValueObjects.ERole;
import LanguageDetection.infrastructure.repositories.JPARepositories.RoleJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;


class RoleRepositoryTest {

    @InjectMocks
    RoleRepository roleRepository;

    @Mock
    RoleJpaRepository roleJpaRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUserShouldReturnSameUserObject(){
        //Arrange
        Role role = new Role(ERole.ROLE_USER);

        Mockito.when(roleJpaRepository.save(role)).thenReturn(role);

        //Act / Assert
        Assertions.assertEquals(roleRepository.save(role), role);
    }

    @Test
    void findByUsernameShouldReturnOptionalOfUserWhenUserNameExists(){
        //Arrange
        Role role = new Role(ERole.ROLE_ADMIN);

        Mockito.when(roleJpaRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(Optional.of(role));

        //Act / Assert
        Assertions.assertEquals(roleRepository.findByName(ERole.ROLE_ADMIN), Optional.of(role));
    }

    @Test
    void findByUsernameShouldReturnOptionalEmptyWhenUsernameDoesNotExist(){
        //Arrange
        Role role = new Role(ERole.ROLE_ADMIN);

        Mockito.when(roleJpaRepository.findByName(ERole.ROLE_ADMIN)).thenReturn(Optional.empty());

        //Act / Assert
        Assertions.assertEquals(roleRepository.findByName(ERole.ROLE_ADMIN), Optional.empty());
    }

}