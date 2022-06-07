package LanguageDetection.domain.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.net.MalformedURLException;
import java.net.URL;

@Embeddable
@Table
public class BlackListUrl implements ValueObject, Comparable<BlackListUrl> {

    private final URL blackListUrl;

    /* For ORM purposes */
    protected BlackListUrl() {
        this.blackListUrl = null;
    }

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
        BlackListUrl url = (BlackListUrl) otherBlackListUrl;

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
