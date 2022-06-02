package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.shared.Entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class BlackList implements Entity<InputUrl> {
    private InputUrl url;

    public BlackList(String url) throws MalformedURLException {
        this.url = new InputUrl(getHostNameFromUrl(url));
    }

    public boolean sameAs(Object otherBlackList) {
        return this.equals(otherBlackList);
    }


    public int compareTo(@NotNull InputUrl otherURL) {
        return url.compareTo(otherURL);
    }

    @JsonGetter
    public InputUrl identity() {
        return this.url;
    }


    @Override
    public boolean hasIdentity(InputUrl id) {
        return Entity.super.hasIdentity(id);
    }

    private String getHostNameFromUrl(String url){
         return this.url.getUrlObject().getHost();
    }
}