package LanguageDetection.application.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
/**
 * Represents a NewTaskInfoDTO containing the url, category and timeout information that was inputted by the admin
 * for creating and saving a task
 */
public class NewTaskInfoDTO {
    @Getter @Setter
    String url;
    @Getter
    String category;
    @Getter
    int timeOut;


}
