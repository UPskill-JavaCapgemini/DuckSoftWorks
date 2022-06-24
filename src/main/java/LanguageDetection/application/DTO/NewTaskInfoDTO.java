package LanguageDetection.application.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class NewTaskInfoDTO {
    @Getter @Setter
    String url;
    @Getter
    String category;
    @Getter
    int timeOut;


}
