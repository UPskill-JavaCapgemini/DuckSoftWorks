package LanguageDetection.application.dtos;

import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.ValueObjects.URL;
import LanguageDetection.domain.entities.Task;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NewTaskInfoDTO {
    @Getter @Setter
    URL url;
    @Getter
    Task.Category category;
    @Getter
    TimeOut timeOut;


}
