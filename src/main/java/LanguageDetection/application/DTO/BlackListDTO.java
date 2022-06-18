package LanguageDetection.application.DTO;

import LanguageDetection.domain.model.BlackListItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;

@NoArgsConstructor
@EqualsAndHashCode
public class BlackListDTO {

    @Getter
    URL url;

    public BlackListDTO(BlackListItem blackListItem) {
        this.url = blackListItem.identity().getBlackListUrlObject();
    }

}
