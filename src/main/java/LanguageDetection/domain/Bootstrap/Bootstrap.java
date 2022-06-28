package LanguageDetection.domain.Bootstrap;


import LanguageDetection.domain.model.*;
import LanguageDetection.domain.model.ValueObjects.ERole;
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
    IRoleRepository roleRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Long NO_CATEGORY_COUNT = 0L;
    private static final Long MAX_USER_COUNT = 3L;
    private static final Long MAX_ROLE_COUNT = 2L;
    private static final Long MAX_BLACKLISTITEM_COUNT = 1L;

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
        Long persistedBlackListItems = iBlackListItemRepository.countPersistedBlackListItems();
        if (persistedBlackListItems < MAX_BLACKLISTITEM_COUNT){
            BlackListItem blackListItem = new BlackListItem("http://127.0.0.1");
            iBlackListItemRepository.saveBlackListItem(blackListItem);
        }
    }

    private void createBaseCategories() {
        Long categoryCount = iCategoryRepository.countPersistedCategories();
        if (categoryCount == NO_CATEGORY_COUNT) {
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

    private void createRoles() {
        Long roleCount = roleRepository.countPersistedRoles();

        if (roleCount < MAX_ROLE_COUNT){

            Role admin = new Role(ERole.ROLE_ADMIN);
            Role user = new Role(ERole.ROLE_USER);

            roleRepository.saveRole(admin);
            roleRepository.saveRole(user);

        }
    }

    private void createAdmin() {

        Long userCount = userRepository.countPersistedUsers();

        if (userCount < MAX_USER_COUNT) {

            String adminPW = "1234";

            User admin = new User("adminduck", passwordEncoder.encode(adminPW));
            Role adminRole = roleRepository.findRoleByName(ERole.ROLE_ADMIN).get();
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            admin.setRoles(roles);

            userRepository.saveUser(admin);
        }
    }

    private void createUsers() {

        Long userCount = userRepository.countPersistedUsers();

        if (userCount < MAX_USER_COUNT)
        {
            String user1PW = "1234";
            String user2PW = "1234";

            User user1 = new User("firstduck", passwordEncoder.encode(user1PW));
            User user2 = new User("secondduck", passwordEncoder.encode(user2PW));

            Role userRole = roleRepository.findRoleByName(ERole.ROLE_USER).get();
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);

            user1.setRoles(roles);
            user2.setRoles(roles);

            userRepository.saveUser(user1);
            userRepository.saveUser(user2);
        }
    }

}
