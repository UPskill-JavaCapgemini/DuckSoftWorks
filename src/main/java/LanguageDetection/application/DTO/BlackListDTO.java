package LanguageDetection.application.DTO;

import LanguageDetection.domain.model.BlackListItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.net.URL;

@NoArgsConstructor
/**
 * Represents a BlackListDTO containing the Url that was inputted by the admin
 */
public class BlackListDTO {

    @Getter
    URL url;

    public BlackListDTO(BlackListItem blackListItem) {
        this.url = blackListItem.identity().getBlackListUrlObject();
    }

}
