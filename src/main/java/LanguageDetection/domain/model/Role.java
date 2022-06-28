package LanguageDetection.domain.model;

import LanguageDetection.domain.model.ValueObjects.ERole;
import LanguageDetection.domain.shared.Entity;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "roles")
public class Role implements Entity<Integer> {
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

  @Override
  public boolean sameAs(Object other) {
    return this.equals(other);
  }

  @Override
  public Integer identity() {
    return this.id;
  }


}