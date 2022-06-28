package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

// fonte: https://bezkoder.com/spring-boot-jwt-authentication/

@AllArgsConstructor
public class JwtResponseDTO extends RepresentationModel<UserDTO> {
    @Getter
    String token;
    @Getter
    long id;
    @Getter
    String username;
    @Getter
    List<String> roles;
}
