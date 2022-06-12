package LanguageDetection.application.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NewTaskInfoDTO {
    @Getter @Setter
    String url;
    @Getter
    String category;
    @Getter
    int timeOut;


}
