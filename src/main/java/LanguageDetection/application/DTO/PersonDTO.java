package LanguageDetection.application.DTO;


import LanguageDetection.domain.ValueObjects.PersonId;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class PersonDTO extends RepresentationModel<PersonDTO> {
    @Getter
    PersonId id;
    @Getter
    String firstName;
    @Getter
    String lastName;
}