package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.shared.AggregateRoot;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.net.MalformedURLException;


/**
 * Class that that represents the Items that are going to be placed in a BlackList of not accepted Urls
 * for the text language identification.
 * The first url was inserted through Bootstrap and the rest can be added by the Administrator.
 */

@Getter
@Component
@Entity
@Table
public class BlackListItem implements AggregateRoot<BlackListUrl> {

    /**
     * Gets the Value Object BlackListUrl where the business validations are implemented.
     */
    @EmbeddedId
    private BlackListUrl blackListUrl;

    protected BlackListItem(){}

    /**
     * Constructs a BlackListItem with an url as a String.
     */
    public BlackListItem(String blackListUrl) throws MalformedURLException {

            this.blackListUrl = new BlackListUrl(blackListUrl);
    }

    public boolean sameAs(Object otherBlackList) {
        return this.equals(otherBlackList);
    }


    public int compareTo(@NotNull BlackListUrl otherURL) {
        return blackListUrl.compareTo(otherURL);
    }

    /**
     * method that identify the BlackListUrl
     * @return the url
     */
    @JsonGetter
    public BlackListUrl identity() {
        return this.blackListUrl;
    }

    @Override
    public boolean hasIdentity(BlackListUrl id) {
        return AggregateRoot.super.hasIdentity(id);
    }


    /*private static boolean urlValidator(String url) {
        // Get an `UrlValidator` using default schemes
        UrlValidator defaultValidator = new UrlValidator();
        return defaultValidator.isValid(url);
    }*/
}