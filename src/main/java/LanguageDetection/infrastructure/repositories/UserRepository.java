package LanguageDetection.infrastructure.repositories;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


import LanguageDetection.domain.model.User;
import LanguageDetection.infrastructure.repositories.JPARepositories.UserJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

        @Autowired
        UserJpaRepository userJpaRepository;


        public User save(User user) {
        User savedUserJpa = this.userJpaRepository.save(user);

        return savedUserJpa;
    }

    @Transactional
    public Optional<User> findById(long id) {
        Optional<User> opPersonJpa = userJpaRepository.findById(id);

        if(opPersonJpa.isPresent()) {
            User userJpa = opPersonJpa.get();

            return Optional.of(userJpa);
        }
        else
            return Optional.empty();
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        Optional<User> opPersonJpa = userJpaRepository.findByUsername(username);

        if(opPersonJpa.isPresent()) {
            User userJpa = opPersonJpa.get();

            return Optional.of(userJpa);
        }
        else
            return Optional.empty();
    }


    @Transactional
    public List<User> findAll() {
        List<User> setUserJpa = (List<User>) userJpaRepository.findAll();

        List<User> setUsers = new ArrayList<User>();
        for( User userJpa : setUserJpa) {
            setUsers.add(userJpa);
        }

        return setUsers;
    }

    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

}