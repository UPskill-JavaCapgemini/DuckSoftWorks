package LanguageDetection.infrastructure.repositories.JPARepositories.jpa;

import LanguageDetection.domain.ValueObjects.GroupId;
import LanguageDetection.domain.ValueObjects.PersonId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name="groups")
public class GroupJpa {
	@Getter
	@EmbeddedId
	private GroupId id;

	@Getter
	private String name;

	@Getter
	@OneToMany(mappedBy = "id.groupJpa", cascade = CascadeType.ALL /*, fetch = FetchType.EAGER*/ )
	private List<AdminJpa> administrators;

	private static final Logger log = LoggerFactory.getLogger(GroupJpa.class);

	public GroupJpa(long id, String name ) {
		this.id = new GroupId(id);
		this.name = name;
		this.administrators = new ArrayList<AdminJpa>();
	}

	public GroupJpa(GroupId id, String name ) {
		this.id = id;
		this.name = name;
		this.administrators = new ArrayList<AdminJpa>();
	}

	public String toString() {
        return "Group {" +
                "id='" + id.toString() + '\'' +
                '}';
	}

	public boolean addAdministrator( PersonId adminId ) {
		AdminJpa adminJpa = new AdminJpa(this, adminId);

		return administrators.add(adminJpa);
	}

	public boolean removeAdministrator( PersonId adminId ) {
		AdminJpa adminJpa = new AdminJpa(this, adminId);
		log.info("Size admins: " + administrators.size());
for(AdminJpa a : administrators ) {
	log.info("Remove Admin: " + a.toString());
}
		return administrators.remove( adminJpa );
	}
}
