package LanguageDetection.infrastructure.repositories.JPARepositories;


import LanguageDetection.infrastructure.repositories.JPARepositories.jpa.AdminJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdminJpaRepository extends CrudRepository<AdminJpa, Long> {

    AdminJpa findById(long id);

    //List<AdminJpa> findAllByGroupId( GroupId id);
    List<AdminJpa> findAll();
}
