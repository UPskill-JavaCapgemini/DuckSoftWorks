package LanguageDetection.domain.ValueObjects;


import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PersonId implements EntityId, Serializable {
    private static final long serialVersionUID = -544476909836408198L;

    private long personId;
}