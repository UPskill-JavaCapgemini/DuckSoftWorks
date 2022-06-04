package LanguageDetection.domain.entities;

import LanguageDetection.domain.shared.Entity;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public class Category implements Entity {

    @Getter
    String category;

    public Category(String category) {
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Object identity() {
        return null;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }
}
