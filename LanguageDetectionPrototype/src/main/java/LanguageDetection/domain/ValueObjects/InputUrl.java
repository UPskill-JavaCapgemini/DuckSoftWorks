package LanguageDetection.domain.ValueObjects;
import LanguageDetection.domain.shared.ValueObject;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.net.MalformedURLException;
import java.net.URL;



@Embeddable
@Table
public class InputUrl implements ValueObject, Comparable<InputUrl> {


    private final java.net.URL url;


    public InputUrl(String url) throws MalformedURLException {
        if(StringUtils.endsWithAny(url.toLowerCase(),".txt")) {
            this.url = new URL(url);
        } else {
            throw new IllegalArgumentException("The URL doesn't contain a txt file");
        }
    }

    @Override
    public String toString() {
        return this.url.toString();
    }

    /* For ORM purposes */
    public InputUrl(){
        this.url=null;
    }

    public String getUrl(){
        return this.url.toString();
    }

    public URL getUrlObject(){
        return this.url;
    }

    @Override
    public boolean equals(Object otherURL) {
        InputUrl url = (InputUrl) otherURL;
        if (url.getUrlObject().getHost() == this.getUrlObject().getHost()) {
            return true;
        } else return false;
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }

    @Override
    public int compareTo(@NotNull InputUrl o) {
        assert url != null;
        return url.toString().compareTo(o.getUrlObject().toString());
    }
}