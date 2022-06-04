package LanguageDetection.infrastructure.datamodel;

import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="task")
public class  TaskJpa {

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Getter
	@CreationTimestamp
	@Column(name="timestamp", nullable = false, updatable = false)
	private Date date;
	@Getter
	private String category;
	@Getter
	private String language;
	@Getter
	private int timeOut;
	@Getter
	private String url;
	@Getter
	private String currentStatus;


	public TaskJpa(String url, int timeOut, String category, Task.Language language, Task.CurrentStatus status) {
		this.url = url;
		this.timeOut = timeOut;
		this.category = category;
		this.language = language.toString();
		this.currentStatus = status.toString();

	}
}