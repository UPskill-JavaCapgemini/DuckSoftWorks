package LanguageDetection.infrastructure.repositories.JPARepositories.jpa;

import LanguageDetection.domain.ValueObjects.ERole;
import LanguageDetection.domain.ValueObjects.RoleId;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class RoleJpa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Embedded
	private RoleId id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	protected RoleJpa() {}

	public RoleJpa(ERole name) {
		this.name = name;
	}

	public RoleJpa(RoleId id, ERole name) {
		this.id = id;
		this.name = name;
	}

	public RoleId getId() {
		return id;
	}

	public void setId(RoleId id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}
