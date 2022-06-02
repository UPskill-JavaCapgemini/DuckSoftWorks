package LanguageDetection.domain.ValueObjects;
import LanguageDetection.domain.shared.ValueObject;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

public class InputUrl implements ValueObject, Comparable<InputUrl> {


    private final java.net.URL url;


    public InputUrl(String url) throws MalformedURLException {

        this.url= new URL(url);
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
        return url.getPath().compareTo(o.getUrlObject().toString());
    }
}