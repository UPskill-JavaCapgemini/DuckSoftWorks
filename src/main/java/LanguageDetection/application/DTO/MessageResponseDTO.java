package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

// fonte: https://bezkoder.com/spring-boot-jwt-authentication/

@AllArgsConstructor
public class MessageResponseDTO extends RepresentationModel<UserDTO> {
    @Getter
    String message;
}