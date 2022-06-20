package LanguageDetection.application.DTO.DTOAssemblers;

import LanguageDetection.application.DTO.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserDomainDTOAssembler {

    private UserDomainDTOAssembler() {
    }

    public UserDTO toDTO(long id) {
        return new UserDTO(id);
    }
}