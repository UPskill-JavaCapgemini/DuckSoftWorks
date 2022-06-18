package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;


@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class SignupRequest extends RepresentationModel<PersonDTO> {
    @Getter
    long id;
    @Getter
    String username;
    @Getter
    String firstName;
    @Getter
    String lastName;
    @Getter
    String email;
    @Getter
    String password;
}
