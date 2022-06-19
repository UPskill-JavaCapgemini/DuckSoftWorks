package LanguageDetection.domain.Bootstrap;


import LanguageDetection.domain.model.BlackListItem;
import LanguageDetection.domain.model.Category;
import LanguageDetection.domain.model.IBlackListItemRepository;
import LanguageDetection.domain.model.ICategoryRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.net.MalformedURLException;

@Component
public class Bootstrap implements InitializingBean {

    @Autowired
    IBlackListItemRepository iBlackListItemRepository;

    @Autowired
    ICategoryRepository iCategoryRepository;

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        createAndSaveBlackListItem();
        createBaseCategories();
    }

    private void createAndSaveBlackListItem() throws MalformedURLException {
        BlackListItem blackListItem = new BlackListItem("http://127.0.0.1");
        iBlackListItemRepository.saveBlackListItem(blackListItem);
    }

    private void createBaseCategories() {

        Category philosophy = new Category("Philosophy");
        Category economics = new Category("Economics");
        Category mechanics = new Category("Mechanics");
        Category nutrition = new Category("Nutrition");
        Category sports = new Category("Sports");

        philosophy.defineAsBaseCategory();
        economics.defineAsBaseCategory();
        mechanics.defineAsBaseCategory();
        nutrition.defineAsBaseCategory();
        sports.defineAsBaseCategory();

        iCategoryRepository.saveCategory(philosophy);
        iCategoryRepository.saveCategory(economics);
        iCategoryRepository.saveCategory(mechanics);
        iCategoryRepository.saveCategory(nutrition);
        iCategoryRepository.saveCategory(sports);


    }
}
