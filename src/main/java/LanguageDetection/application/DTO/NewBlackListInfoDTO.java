package LanguageDetection.application.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NewBlackListInfoDTO {
    @Getter
    @Setter
    String url;
}
