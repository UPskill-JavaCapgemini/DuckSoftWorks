package LanguageDetection.domain.ValueObjects.SystemUser;

import LanguageDetection.domain.shared.ValueObject;
import org.jetbrains.annotations.NotNull;

public class UserName implements ValueObject, Comparable<UserName> {

    private String userName;

    public UserName(String uName) {
        this.userName = uName;
    }

    @Override
    public String toString() {
        return this.userName;
    }

    /* For ORM purposes */
    public UserName(){
        this.userName = null;
    }

    @Override
    public int compareTo(@NotNull UserName o) {
        return userName.compareTo(o.userName);
    }
}
