package LanguageDetection.domain.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import lombok.Getter;

@Getter
public class TaskResult implements ValueObject {
    private Language language;

    protected TaskResult(){}

    public TaskResult(Language language){
        this.language = language;
    }

    @Override
    public String toString(){
        return language.toString();
    }
}
