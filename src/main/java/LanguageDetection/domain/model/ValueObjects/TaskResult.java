package LanguageDetection.domain.model.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Component
@Embeddable
public class TaskResult implements ValueObject {

    @Enumerated(EnumType.STRING)
    private Language language;

    /*
     * For ORM Purposes
     */
    protected TaskResult(){}

    public TaskResult(Language language){
        this.language = language;
    }
}
