package LanguageDetection.application.DTO;

import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.entities.BlackListItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@EqualsAndHashCode
public class BlackListDTO {

    @Getter
    BlackListUrl url;

    public BlackListDTO(BlackListItem blackListItem) {
        this.url = blackListItem.identity();
    }

    @Override
    public String toString(){
        return url.getBlackListUrlObject().toString();
    }
}
