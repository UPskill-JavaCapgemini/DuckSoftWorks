package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;


@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class SignupRequest extends RepresentationModel<UserDTO> {
    @Getter
    String username;
    @Getter
    String email;
    @Getter
    String password;
}
