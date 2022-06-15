package LanguageDetection.application.Bootstrap;


import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.application.services.BlackListManagementService;
import LanguageDetection.application.services.CategoryService;
import LanguageDetection.domain.entities.Category;
import LanguageDetection.domain.entities.ICategoryRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.net.MalformedURLException;

@Component
public class Bootstrap implements InitializingBean {

    @Autowired
    BlackListManagementService blackListManagementService;

    @Autowired
    ICategoryRepository iCategoryRepository;

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        createAndSaveBlackListItem();
        createBaseCategories();
    }

    private void createAndSaveBlackListItem() throws MalformedURLException {
        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO("http://127.0.0.1");
        blackListManagementService.createAndSaveBlackListItem(blackListInfoDTO);
    }

    private void createBaseCategories() {

        Category economics = new Category("Economics");
        Category philosophy = new Category("Philosophy");
        Category mechanics = new Category("Mechanics");
        Category nutrition = new Category("Nutrition");
        Category sports = new Category("Sports");

        Category.defineAsBaseCategory(economics);
        Category.defineAsBaseCategory(philosophy);
        Category.defineAsBaseCategory(mechanics);
        Category.defineAsBaseCategory(nutrition);
        Category.defineAsBaseCategory(sports);

        iCategoryRepository.saveCategory(economics);
        iCategoryRepository.saveCategory(philosophy);
        iCategoryRepository.saveCategory(mechanics);
        iCategoryRepository.saveCategory(nutrition);
        iCategoryRepository.saveCategory(sports);


    }
}
