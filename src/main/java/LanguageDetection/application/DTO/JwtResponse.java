package LanguageDetection.application.DTO;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

// fonte: https://bezkoder.com/spring-boot-jwt-authentication/

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class JwtResponse extends RepresentationModel<UserDTO> {
    @Getter
    @Setter
    String token;
    @Getter
    @Setter
    long id;
    @Getter
    @Setter
    String username;
    @Getter
    @Setter
    String email;
    @Getter
    @Setter
    List<String> roles;
}
