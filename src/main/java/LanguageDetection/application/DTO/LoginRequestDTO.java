package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

// fonte: https://bezkoder.com/spring-boot-jwt-authentication/

@AllArgsConstructor
public class LoginRequestDTO extends RepresentationModel<UserDTO> {
    @Getter
    String username;
    @Getter
    String password;
}
