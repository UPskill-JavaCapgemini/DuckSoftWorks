package LanguageDetection.domain.ValueObjects;
import LanguageDetection.domain.shared.ValueObject;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

public class InputUrl implements ValueObject, Comparable<InputUrl> {

    private final java.net.URL url;


    public InputUrl(java.net.URL url){
        this.url=url;
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
        return this.url.getPath();
    }

    public URL getUrlObject(){
        return this.url;
    }

    @Override
    public boolean equals(InputUrl url) {
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
        return url.getPath().compareTo(o.getUrlObject().getPath());
    }
}