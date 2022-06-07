package LanguageDetection.application.services;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.DTOAssemblers.BlackListDomainDTOAssembler;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.domain.entities.IBlackListItem;
import LanguageDetection.infrastructure.repositories.BlackListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlackListService {

    @Autowired
    IBlackListItem iBlackListItem;
    @Autowired
    BlackListDomainDTOAssembler assembler;

    public BlackListDTO createBlackListItem(NewBlackListInfoDTO inputUrl) throws MalformedURLException {
        String url = inputUrl.getUrl();
        BlackListItem blackListItem = new BlackListItem(inputUrl.getUrl());
        BlackListItem savedBlackListItem = iBlackListItem.saveBlackListItem(blackListItem);

        return assembler.toDTO(savedBlackListItem);
    }

    public List<BlackListDTO> findAll() throws MalformedURLException {
        List<BlackListItem> blackListItems = iBlackListItem.findAllBlackListItems();

        List<BlackListDTO> blackListDTOS = new ArrayList<>();

        for (BlackListItem blackList : blackListItems) {
            BlackListDTO blackListDTO = assembler.toDTO(blackList);
            blackListDTOS.add(blackListDTO);
        }

        return blackListDTOS;
    }

    public boolean isBlackListed(NewBlackListInfoDTO inputBlackList) throws MalformedURLException {
        BlackListItem blackList = new BlackListItem(inputBlackList.getUrl());
        return iBlackListItem.isBlackListed(blackList);
    }
}
