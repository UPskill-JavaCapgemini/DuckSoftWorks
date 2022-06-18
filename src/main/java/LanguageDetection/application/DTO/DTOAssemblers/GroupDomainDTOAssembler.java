package LanguageDetection.application.DTO.DTOAssemblers;

import LanguageDetection.application.DTO.GroupDTO;
import LanguageDetection.domain.ValueObjects.GroupId;
import org.springframework.stereotype.Service;

@Service
public class GroupDomainDTOAssembler {

    private GroupDomainDTOAssembler() {
    }

    public GroupDTO toDTO(GroupId id, String name ) {
        return new GroupDTO(id, name);
    }
}