package LanguageDetection.domain.entities.example;

import LanguageDetection.domain.shared.AggregateRoot;
import org.jetbrains.annotations.NotNull;

public class Task implements AggregateRoot {
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
