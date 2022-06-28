package LanguageDetection.domain.model;

import java.util.Optional;

public interface IUserRepository {

    public User saveUser(User user);

    public Optional<User> findUserByUserName(String userName);

    Long countPersistedUsers();

}
