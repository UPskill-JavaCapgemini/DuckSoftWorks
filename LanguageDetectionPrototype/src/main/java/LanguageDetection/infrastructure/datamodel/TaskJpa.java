package LanguageDetection.infrastructure.datamodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="countries")
public class TaskJpa {

	// todo: if it's a value object use: @EmbeddedId
	// todo: the class must be @Embeddable and uses the value object directly from the domain
	// todo: if it's a generated value, it must implement the EntityId and Serializable interfaces
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Long id;

	@Getter
	private String name;
}