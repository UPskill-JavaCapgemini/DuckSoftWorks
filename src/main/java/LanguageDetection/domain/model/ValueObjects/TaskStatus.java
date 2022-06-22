package LanguageDetection.domain.model.ValueObjects;

import LanguageDetection.domain.shared.ValueObject;
import lombok.Getter;
    /**
     * Represents a TaskStatus. It's possible values are Concluded, Canceled and Processing
     */
    @Getter
    public enum TaskStatus implements ValueObject {
        Concluded,
        Canceled,
        Processing
    }

