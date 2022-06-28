package LanguageDetection.infrastructure.repositories;


import LanguageDetection.domain.model.IUserRepository;
import LanguageDetection.domain.model.User;
import LanguageDetection.infrastructure.repositories.JPARepositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    UserJpaRepository userJpaRepository;


    public User saveUser(User user) {
        User savedUserJpa = this.userJpaRepository.save(user);

        return savedUserJpa;
    }

    @Override
    @Transactional
    public Optional<User> findUserByUserName(String userName) {
        Optional<User> opPersonJpa = userJpaRepository.findByUsername(userName);

        if (opPersonJpa.isPresent()) {
            User userJpa = opPersonJpa.get();

            return Optional.of(userJpa);
        } else
            return Optional.empty();
    }

    @Override
    public Long countPersistedUsers() {
        Long persistedUserCount = userJpaRepository.count();
        return persistedUserCount;
    }
}