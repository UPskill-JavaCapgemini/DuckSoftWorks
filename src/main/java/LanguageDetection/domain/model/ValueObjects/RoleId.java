package LanguageDetection.domain.model.ValueObjects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RoleId implements EntityId, Serializable {
	private static final long serialVersionUID = -544476909836408198L;

	private long roleId;
}