package LanguageDetection.Domain.model;

import LanguageDetection.domain.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void createUserWithCorrectAttributes(){
        User user = new User("duck1", "duck1@gmail.com", "password");

        assertNotNull(user);
    }

    @Test
    public void ensureItsPossibleToGetUsernameFromUser(){
        User user = new User("duck1", "duck1@gmail.com", "password");

        assertEquals(user.getUsername(), "duck1");
    }

    @Test
    public void ensureItsPossibleToGetPasswordFromUser(){
        User user = new User("duck1", "duck1@gmail.com", "password");

        assertEquals(user.getPassword(), "password");
    }

    @Test
    public void ensureItsPossibleToGetEmailFromUser(){
        User user = new User("duck1", "duck1@gmail.com", "password");

        assertEquals(user.getEmail(), "duck1@gmail.com");
    }

}