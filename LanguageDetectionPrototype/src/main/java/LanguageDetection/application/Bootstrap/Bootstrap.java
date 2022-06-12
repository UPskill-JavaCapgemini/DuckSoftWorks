package LanguageDetection.application.Bootstrap;


import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.application.services.BlackListService;
import LanguageDetection.application.services.CategoryService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.MalformedURLException;

@Component
public class Bootstrap implements InitializingBean {

    @Autowired
    BlackListService blackListService;

    @Autowired
    CategoryService categoryService;

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        createAndSaveBlackListItem();
        createBaseCategories();
    }

    private void createAndSaveBlackListItem() throws MalformedURLException {
        NewBlackListInfoDTO blackListInfoDTO = new NewBlackListInfoDTO("http://127.0.0.1");
        blackListService.createAndSaveBlackListItem(blackListInfoDTO);
    }

    private void createBaseCategories() throws MalformedURLException {

        NewCategoryInfoDTO economics = new NewCategoryInfoDTO("Economics");
        NewCategoryInfoDTO philosophy = new NewCategoryInfoDTO("Philosophy");
        NewCategoryInfoDTO mechanics = new NewCategoryInfoDTO("Mechanics");
        NewCategoryInfoDTO nutrition = new NewCategoryInfoDTO("Nutrition");
        NewCategoryInfoDTO sports = new NewCategoryInfoDTO("Sports");

        categoryService.createAndSaveCategory(economics);
        categoryService.createAndSaveCategory(philosophy);
        categoryService.createAndSaveCategory(mechanics);
        categoryService.createAndSaveCategory(nutrition);
        categoryService.createAndSaveCategory(sports);
    }
}