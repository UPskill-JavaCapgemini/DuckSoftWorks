package LanguageDetection.domain.entities.example;

import LanguageDetection.domain.ValueObjects.Language;
import LanguageDetection.domain.ValueObjects.Text;
import LanguageDetection.domain.shared.AggregateRoot;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
@Getter
public class Task implements AggregateRoot <Date>{
    Date date;
    Text text;
    Language lang;

    public Task (Text txt, Language lg){
        this.date = new Date(System.currentTimeMillis());
        this.text = txt;
        this.lang = lg;
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
