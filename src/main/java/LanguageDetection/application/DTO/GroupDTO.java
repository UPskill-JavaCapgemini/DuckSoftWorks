package LanguageDetection.application.DTO;

import LanguageDetection.domain.model.ValueObjects.GroupId;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class GroupDTO extends RepresentationModel<GroupDTO> {
    @Getter
    GroupId id;
    @Getter
    String name;
}
