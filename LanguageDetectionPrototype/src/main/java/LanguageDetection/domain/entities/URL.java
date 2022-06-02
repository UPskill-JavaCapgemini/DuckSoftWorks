package LanguageDetection.domain.entities;

import LanguageDetection.domain.shared.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class URL implements Entity<URL> {
    @Getter
    private URL url;

    public URL(URL url) {
        this.url = url;
    }

    @Override
    public boolean sameAs(Object other) {
        return other.equals(url);
    }

    @Override
    public int compareTo(URL otherUrl) {
        return Entity.super.compareTo(otherUrl);
    }

    @Override
    public URL identity() {
        return url;
    }

    @Override
    public boolean hasIdentity(URL id) {
        return Entity.super.hasIdentity(id);
    }
}
