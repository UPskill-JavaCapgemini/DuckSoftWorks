package LanguageDetection.infrastructure.datamodel;

import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="task")
public class TaskJpa {

	// todo: if it's a value object use: @EmbeddedId
	// todo: the class must be @Embeddable and uses the value object directly from the domain
	// todo: if it's a generated value, it must implement the EntityId and Serializable interfaces
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Date date;

	@Getter
	private Task.Category category;

	@Getter
	private Task.Language language;

	@Getter
	private TimeOut timeOut;

	@Getter
	private InputUrl url;

	@Getter
	private Task.CurrentStatus currentStatus;

}