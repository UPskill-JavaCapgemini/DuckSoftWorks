package LanguageDetection.infrastructure.repositories.JPARepositories.jpa;

import LanguageDetection.domain.model.ValueObjects.PersonId;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="admins")
public class AdminJpa {

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    static class GroupPersonIdJpa implements Serializable {
        private static final long serialVersionUID = 4517981563951180888L;

        @Getter
        @ManyToOne
        @JoinColumn(name = "group_id")
        private GroupJpa groupJpa;

        @Getter
        @Column(nullable = false, updatable = false)
        private PersonId personId;
    }

    @Getter
    @EmbeddedId
    GroupPersonIdJpa id;

    public AdminJpa(GroupJpa groupJpa, PersonId personId ) {
        this.id = new GroupPersonIdJpa(groupJpa, personId);
    }

    public PersonId getPersonId() {
        return id.getPersonId();
    }
}
