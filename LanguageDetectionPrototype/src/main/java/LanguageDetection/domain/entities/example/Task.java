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

    public Task (String txt, Language lg){
        this.date = new Date(System.currentTimeMillis());
        this.text = new Text(txt);
        this.lang = lg;
    }


    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(Date other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public Date identity() {
        return this.date;
    }

    @Override
    public boolean hasIdentity(Date id) {
        return AggregateRoot.super.hasIdentity(id);
    }
}
