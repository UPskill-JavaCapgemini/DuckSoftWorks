package LanguageDetection.domain.model;

import LanguageDetection.domain.model.ValueObjects.ERole;
import LanguageDetection.domain.shared.ValueObject;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role implements ValueObject {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole name;

  protected Role() {
  }

  public Role(ERole name) {
    this.name = name;
  }
  public ERole getName() {
    return name;
  }

}