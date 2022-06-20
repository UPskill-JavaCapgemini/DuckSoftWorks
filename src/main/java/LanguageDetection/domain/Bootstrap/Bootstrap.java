package LanguageDetection.domain.Bootstrap;


import LanguageDetection.domain.model.ValueObjects.ERole;
import LanguageDetection.domain.model.Role;
import LanguageDetection.domain.model.*;
import LanguageDetection.infrastructure.repositories.UserRepository;
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
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        createAndSaveBlackListItem();
        createBaseCategories();
        createRoles();
        createAdmin();
        createUsers();
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

    private void createRoles(){

        Role admin = new Role(ERole.ROLE_ADMIN);
        Role user = new Role(ERole.ROLE_USER);

        roleRepository.save(admin);
        roleRepository.save(user);
    }

    private void createAdmin(){

            String adminPW = "passwordfixe";


        User admin = new User(
                "danilima90",
                "danilima90@hotmail.com",
                passwordEncoder.encode(adminPW));

        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).get();
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        admin.setRoles(roles);

        userRepository.save(admin);

    }

    private void createUsers(){

        String user1PW  = "quackingonce";
        String user2PW = "quackingtwice";

        User user1  = new User("firstduck","firstduck@gmail.com", passwordEncoder.encode(user1PW));
        User user2  = new User("secondduck","secondduck@gmail.com", passwordEncoder.encode(user2PW));

        Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        user1.setRoles(roles);
        user2.setRoles(roles);

        userRepository.save(user1);
        userRepository.save(user2);
    }
}
