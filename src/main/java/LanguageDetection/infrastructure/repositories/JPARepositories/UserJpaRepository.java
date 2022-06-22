package LanguageDetection.infrastructure.repositories.JPARepositories;


import java.util.Optional;


import LanguageDetection.domain.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserJpaRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

}