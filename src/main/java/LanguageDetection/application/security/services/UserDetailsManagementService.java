package LanguageDetection.application.security.services;

import LanguageDetection.domain.DomainService.UserDetailsDomainService;
import LanguageDetection.domain.model.User;

import LanguageDetection.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



// fonte: https://bezkoder.com/spring-boot-jwt-authentication/
@Service
public class UserDetailsManagementService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsDomainService.build(user);
    }

}