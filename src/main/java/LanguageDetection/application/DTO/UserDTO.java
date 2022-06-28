package LanguageDetection.application.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/**
 * Represents a UserDTO and it's used by Spring Security
 */
@AllArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {
    @Getter
    long id;
}