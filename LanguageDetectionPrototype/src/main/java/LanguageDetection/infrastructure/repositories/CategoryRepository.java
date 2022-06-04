package LanguageDetection.infrastructure.repositories;
import LanguageDetection.domain.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.MalformedURLException;

public class CategoryRepository {

    @Autowired
    CategoryDomainDataAssembler categoryAssembler;


    @Autowired
    CategoryJpaRepository categoryJpaRepository;


    public Category save(Category category ) throws MalformedURLException {
        CategoryJpa categoryJpa = categoryAssembler.toData(category);

        CategoryJpa savedCategoryJpa = categoryJpaRepository.save( categoryJpa );

        return categoryAssembler.toDomain(savedCategoryJpa);
    }
}
