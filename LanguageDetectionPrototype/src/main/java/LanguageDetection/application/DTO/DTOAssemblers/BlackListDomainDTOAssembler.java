package LanguageDetection.application.DTO.DTOAssemblers;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.domain.entities.BlackList;
import org.springframework.stereotype.Service;

@Service
public class BlackListDomainDTOAssembler {

    private BlackListDomainDTOAssembler(){}

    public BlackListDTO toDTO(BlackList blackList){
        return new BlackListDTO(blackList);
    }
}
