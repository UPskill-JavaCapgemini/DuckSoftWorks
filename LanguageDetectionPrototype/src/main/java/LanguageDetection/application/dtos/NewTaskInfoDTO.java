package LanguageDetection.application.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NewTaskInfoDTO {
    @Getter @Setter
    String text;
}
