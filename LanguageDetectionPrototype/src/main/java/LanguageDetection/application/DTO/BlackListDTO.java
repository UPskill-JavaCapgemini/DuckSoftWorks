package LanguageDetection.application.DTO;

import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.entities.BlackList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BlackListDTO {

    @Getter
    InputUrl url;

    public BlackListDTO(BlackList blackListItem) {
        this.url = blackListItem.identity();
    }
}
