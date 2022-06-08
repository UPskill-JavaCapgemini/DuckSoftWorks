package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.shared.AggregateRoot;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.net.MalformedURLException;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Repository
@Entity
@Table
public class BlackListItem implements AggregateRoot<BlackListUrl> {
    @EmbeddedId
    private BlackListUrl url;

    public BlackListItem(String url) throws MalformedURLException {
        this.url = new BlackListUrl(url);
    }

    public boolean sameAs(Object otherBlackList) {
        return this.equals(otherBlackList);
    }


    public int compareTo(@NotNull BlackListUrl otherURL) {
        return url.compareTo(otherURL);
    }

    @JsonGetter
    public BlackListUrl identity() {
        return this.url;
    }

    @Override
    public boolean hasIdentity(BlackListUrl id) {
        return AggregateRoot.super.hasIdentity(id);
    }
}