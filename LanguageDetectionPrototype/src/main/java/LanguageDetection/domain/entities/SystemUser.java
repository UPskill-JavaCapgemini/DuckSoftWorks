package LanguageDetection.domain.entities.example;

import LanguageDetection.domain.ValueObjects.SystemUser.Password;
import LanguageDetection.domain.ValueObjects.SystemUser.UserName;
import LanguageDetection.domain.shared.AggregateRoot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class SystemUser implements AggregateRoot<UserName> {

    /**
     * The Username of the System user
     */
    @Id
    @Getter
    UserName userName;
    /**
     * The password of the System user
     */
    @Getter
    Password password;
    /**
     * The role of the System user
     */
    @Getter
    Role role;


    /**
     * Constructor of the System user
     * @param uName Username  of the system user
     * @param pass password of the system user
     * @param role role that system user plays
     */
    public SystemUser(UserName uName, Password pass, Role role) {
        this.userName = uName;
        this.password = pass;
        this.role = role;

    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(UserName other) {
        return AggregateRoot.super.compareTo(other);
    }

    /**
     * method that identify the System user
     * @return the Username
     */
    @Override
    public UserName identity() {
        return this.userName;
    }

    @Override
    public boolean hasIdentity(UserName id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    public enum Role {
        User,
        Administrator
    }
}
