package LanguageDetection.infrastructure.repositories.JPARepositories;


import LanguageDetection.domain.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserJpaRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

}