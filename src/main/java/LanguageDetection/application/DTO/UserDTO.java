package LanguageDetection.application.DTO;


import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class UserDTO extends RepresentationModel<UserDTO> {
    @Getter
    long id;
}