package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.shared.AggregateRoot;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import org.apache.commons.validator.routines.UrlValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.net.MalformedURLException;

//import static org.apache.commons.validator.routines.UrlValidator.ALLOW_ALL_SCHEMES;
//import static org.apache.commons.validator.routines.UrlValidator.ALLOW_LOCAL_URLS;

/**
 * Class that that represents the Items that are going to be placed in a BlackList of not accepted Urls
 * for the text language identification.
 * The first url was inserted throught Bootstrap and the rest can be added by the Administrator.
 */

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Repository
@Entity
@Table
public class BlackListItem implements AggregateRoot<BlackListUrl> {

    /**
     * Gets the Value Object BlackListUrl where the business validations are implemented.
     */
    @EmbeddedId
    private BlackListUrl url;

    /**
     * Constructs a BlackListItem with an url as a String.
     */
    public BlackListItem(String url) throws MalformedURLException {
        /*if (urlValidator(url)){
            throw new MalformedURLException();
        }*/
            this.url = new BlackListUrl(url);
    }

    public boolean sameAs(Object otherBlackList) {
        return this.equals(otherBlackList);
    }


    public int compareTo(@NotNull BlackListUrl otherURL) {
        return url.compareTo(otherURL);
    }

    /**
     * method that identify the BlackListUrl
     * @return the url
     */
    @JsonGetter
    public BlackListUrl identity() {
        return this.url;
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