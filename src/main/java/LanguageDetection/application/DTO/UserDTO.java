package LanguageDetection.application.DTO;


import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a UserDTO and it's used by Spring Security
 */
@AllArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {
    @Getter
    long id;
}