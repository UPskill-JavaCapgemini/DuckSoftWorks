package LanguageDetection.domain.Bootstrap;


import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.application.DTO.NewCategoryInfoDTO;
import LanguageDetection.application.services.BlackListManagementService;
import LanguageDetection.application.services.CategoryService;
import LanguageDetection.domain.ValueObjects.ERole;
import LanguageDetection.domain.ValueObjects.PersonId;
import LanguageDetection.domain.ValueObjects.RoleId;
import LanguageDetection.domain.entities.*;
import LanguageDetection.domain.factory.TaskFactory;
import LanguageDetection.infrastructure.repositories.PersonRepository;
import LanguageDetection.infrastructure.repositories.RoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

@Component
public class Bootstrap implements InitializingBean {

    @Autowired
    IBlackListItemRepository iBlackListItemRepository;

    @Autowired
    ICategoryRepository iCategoryRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        createAndSaveBlackListItem();
        createBaseCategories();
        createRoles();
        createAdmin();
    }

    private void createAndSaveBlackListItem() throws MalformedURLException {
        BlackListItem blackListItem = new BlackListItem("http://127.0.0.1");
        iBlackListItemRepository.saveBlackListItem(blackListItem);
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

    private void createRoles(){
        RoleId adminId = new RoleId(1);
        RoleId userId = new RoleId(2);

        Role admin = new Role(adminId, ERole.ROLE_ADMIN);
        Role user = new Role(userId,ERole.ROLE_USER);

        roleRepository.save(admin);
        roleRepository.save(user);
    }

    private void createAdmin(){
        PersonId adminId = new PersonId(1);
        String adminPW = "passwordfixe";


        Person admin = new Person(adminId,"Daniel",
                "Lima",
                "danilima90@hotmail.com",
                "danilima90",
                passwordEncoder.encode(adminPW));

        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).get();
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        admin.setRoles(roles);

        personRepository.save(admin);

    }
}
