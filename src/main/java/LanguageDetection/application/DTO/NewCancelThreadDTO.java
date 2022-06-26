package LanguageDetection.application.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
/**
 * Represents a NewCancelThreadDTO containing the Task id that was inputted by the admin in order to cancel a language analysis process
 */
public class NewCancelThreadDTO {
    @Setter
    @Getter
    Long id;
}
