package LanguageDetection.application.DTO;

import LanguageDetection.domain.model.ValueObjects.RoleId;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class RoleDTO extends RepresentationModel<RoleDTO> {
    @Getter
    RoleId id;
    @Getter
    String name;
}
