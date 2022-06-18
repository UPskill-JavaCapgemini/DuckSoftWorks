package LanguageDetection.domain.ValueObjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Embeddable
public class GroupId implements EntityId, Serializable {
    private static final long serialVersionUID = -2467088616863451371L;

    private long groupId;

    protected GroupId() {}

    public GroupId(long id) {
        this.groupId = id;
    }
}