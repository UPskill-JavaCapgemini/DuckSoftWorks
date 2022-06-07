package LanguageDetection.application.Bootstrap;


import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.application.services.BlackListService;
import LanguageDetection.application.services.CategoryService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.MalformedURLException;

@Service
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

        NewCategoryInfoDTO economia = new NewCategoryInfoDTO("economia");
        NewCategoryInfoDTO filosofia = new NewCategoryInfoDTO("filosofia");
        NewCategoryInfoDTO mecanica = new NewCategoryInfoDTO("mecânica");
        NewCategoryInfoDTO nutricionismo = new NewCategoryInfoDTO("nutricionismo");
        NewCategoryInfoDTO desporto = new NewCategoryInfoDTO("desporto");

        categoryService.createCategory(economia);
        categoryService.createCategory(filosofia);
        categoryService.createCategory(mecanica);
        categoryService.createCategory(nutricionismo);
        categoryService.createCategory(desporto);
    }
}
