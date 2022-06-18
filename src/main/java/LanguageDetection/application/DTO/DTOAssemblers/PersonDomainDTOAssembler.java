package LanguageDetection.application.DTO.DTOAssemblers;

import LanguageDetection.application.DTO.PersonDTO;
import LanguageDetection.domain.ValueObjects.PersonId;
import org.springframework.stereotype.Service;

@Service
public class PersonDomainDTOAssembler {

    private PersonDomainDTOAssembler() {
    }

    public PersonDTO toDTO(PersonId id, String firstName, String lastName ) {
        return new PersonDTO(id, firstName, lastName);
    }
}