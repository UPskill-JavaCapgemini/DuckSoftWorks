package LanguageDetection.application.DTO.DTOAssemblers;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.domain.model.BlackListItem;
import org.springframework.stereotype.Service;

@Service
public class BlackListDomainDTOAssembler {

    private BlackListDomainDTOAssembler(){}

    public BlackListDTO toDTO(BlackListItem blackList){
        return new BlackListDTO(blackList);
    }
}
