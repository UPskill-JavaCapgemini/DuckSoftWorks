package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
