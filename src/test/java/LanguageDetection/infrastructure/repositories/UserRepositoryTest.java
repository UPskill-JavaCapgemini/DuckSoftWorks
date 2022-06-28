package LanguageDetection.infrastructure.repositories;

import LanguageDetection.domain.model.User;
import LanguageDetection.infrastructure.repositories.JPARepositories.UserJpaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @InjectMocks
    UserRepository userRepository;

    @Mock
    UserJpaRepository userJpaRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUserShouldReturnSameUserObject(){
        //Arrange
        User user = new User("duck", "duckpassword");

        Mockito.when(userJpaRepository.save(user)).thenReturn(user);

        //Act / Assert
        Assertions.assertEquals(userRepository.saveUser(user), user);
    }

    @Test
    void findByUsernameShouldReturnOptionalOfUserWhenUserNameExists(){
        //Arrange
        User user = new User("duck", "duckpassword");

        Mockito.when(userJpaRepository.findByUsername("duck")).thenReturn(Optional.of(user));

        //Act / Assert
        Assertions.assertEquals(userRepository.findUserByUserName("duck"), Optional.of(user));
    }

    @Test
    void findByUsernameShouldReturnOptionalEmptyWhenUsernameDoesNotExist(){
        //Arrange
        User user = new User("duck", "duckpassword");

        Mockito.when(userJpaRepository.findByUsername("duck")).thenReturn(Optional.empty());

        //Act / Assert
        Assertions.assertEquals(userRepository.findUserByUserName("duck"), Optional.empty());
    }

}