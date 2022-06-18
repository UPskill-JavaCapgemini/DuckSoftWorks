package LanguageDetection.domain.model.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Embeddable;
import java.net.MalformedURLException;
import java.net.URL;

@Embeddable
public class BlackListUrl implements ValueObject, Comparable<BlackListUrl> {

    /**
     * getter for the BlackList url as a string
     */
    private final URL blackListUrl;

    /* For ORM purposes */
    protected BlackListUrl() {
        this.blackListUrl = null;
    }

    /**
     * constructor for the BlackListUrl with validation
     * it prevents the BlackListUrl to be created with severeal rules provided by the java URL Class.
     */
    public BlackListUrl(String blackListUrl) throws MalformedURLException {

        this.blackListUrl = new URL(blackListUrl);
    }

    @Override
    public String toString() {
        return blackListUrl.toString();
    }


    public URL getBlackListUrlObject() {
        return this.blackListUrl;
    }

    @Override
    public boolean equals(Object otherBlackListUrl) {

        return this.blackListUrl.toString().equals(otherBlackListUrl.toString());
    }

    @Override
    public int hashCode() {
        return blackListUrl != null ? blackListUrl.hashCode() : 0;
    }

    @Override
    public int compareTo(@NotNull BlackListUrl other) {

        return blackListUrl.toString().compareTo(other.getBlackListUrlObject().toString());
    }
}
