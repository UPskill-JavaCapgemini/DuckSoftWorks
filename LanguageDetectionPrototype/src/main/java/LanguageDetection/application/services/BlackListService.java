package LanguageDetection.application.services;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.DTOAssemblers.BlackListDomainDTOAssembler;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.domain.entities.BlackListItem;
import LanguageDetection.domain.entities.IBlackListItem;
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

    public BlackListDTO createAndSaveBlackListItem(NewBlackListInfoDTO inputUrl) throws MalformedURLException {
        String url = inputUrl.getUrl();
        BlackListItem blackListItem = new BlackListItem(url);
        BlackListItem savedBlackListItem = iBlackListItem.saveBlackListItem(blackListItem);

        return assembler.toDTO(savedBlackListItem);
    }

    public boolean deleteBlackListItem(NewBlackListInfoDTO blackListInfoDTO) throws MalformedURLException {
        String url = blackListInfoDTO.getUrl();
        BlackListItem blackListItem = new BlackListItem(url);
        return iBlackListItem.deleteByBlackListUrl(blackListItem);
    }

    public List<BlackListDTO> getAllBlackListItems() throws MalformedURLException {
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
