package LanguageDetection.application.services;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.DTOAssemblers.BlackListDomainDTOAssembler;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.domain.entities.BlackList;
import LanguageDetection.domain.factories.BlackListFactory;
import LanguageDetection.infrastructure.repositories.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlackListService {

    @Autowired
    BlackListFactory factory;
    @Autowired
    BlackListRepository repository;
    @Autowired
    BlackListDomainDTOAssembler assembler;

    public BlackListDTO createBlackListItem(NewBlackListInfoDTO inputUrl) throws MalformedURLException {
        String url = inputUrl.getUrl();
        BlackList blackListItem = factory.createBlackListItem(url);
        BlackList savedBlackListItem = repository.save(blackListItem);

        return assembler.toDTO(savedBlackListItem);
    }

    public List<BlackListDTO> findAll() throws MalformedURLException {
        List<BlackList> blackListItems = repository.findAll();

        List<BlackListDTO> blackListDTOS = new ArrayList<>();

        for (BlackList blackList : blackListItems) {
            BlackListDTO blackListDTO = assembler.toDTO(blackList);
            blackListDTOS.add(blackListDTO);
        }

        return blackListDTOS;
    }
}