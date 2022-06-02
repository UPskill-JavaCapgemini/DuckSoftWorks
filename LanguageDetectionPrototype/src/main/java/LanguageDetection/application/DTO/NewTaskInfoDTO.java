package LanguageDetection.application.DTO;

import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.entities.Task;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NewTaskInfoDTO {
    @Getter @Setter
    InputUrl url;
    @Getter
    Task.Category category;
    @Getter
    TimeOut timeOut;


}
