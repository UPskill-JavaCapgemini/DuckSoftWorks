package LanguageDetection.domain.entities;

import LanguageDetection.domain.ValueObjects.GroupId;
import LanguageDetection.domain.ValueObjects.PersonId;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
public class Group {
    private GroupId id;
    private String name;
    List<PersonId> administrators = new ArrayList<PersonId>();

    public Group(long id, String name, PersonId adminId) {
        this.id = new GroupId(id);

        this.name = name;

        this.administrators.add(adminId);
    }

    public Group(GroupId id, String name, List<PersonId> admins) {
        this.id = id;

        this.name = name;
        this.administrators = admins;
    }

    public boolean addAdministrator(PersonId adminId) {
        return administrators.add(adminId);
    }

    public boolean removeAdministrator(PersonId adminId) {
        return administrators.remove(adminId);
    }

    public List<PersonId> getAdministrators() {
        return administrators;
    }
}