package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

// fonte: https://bezkoder.com/spring-boot-jwt-authentication/

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class LoginRequest extends RepresentationModel<PersonDTO> {
    @Getter
    String username;
    @Getter
    String password;
}
