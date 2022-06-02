package LanguageDetection.infrastructure.datamodel;

import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.ValueObjects.URL;
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

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Getter
	private Date date;
	@Getter
	private Task.Category category;
	@Getter
	private Task.Language language;
	@Getter
	private TimeOut timeOut;
	@Getter
	private URL url;
	@Getter
	private Task.CurrentStatus currentStatus;

	public TaskJpa(URL url, TimeOut timeOut, Task.Category category) {
		this.url = url;
		this.timeOut = timeOut;
		this.category = category;

	}
}