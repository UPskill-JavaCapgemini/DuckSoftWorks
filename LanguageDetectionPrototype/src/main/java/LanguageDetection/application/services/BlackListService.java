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
import java.util.Optional;

@Service
public class BlackListService {

    @Autowired
    IBlackListItem iBlackListItem;
    @Autowired
    BlackListDomainDTOAssembler assembler;

    /**
     * Creates a new BlackListItem with a NewBlackListInfoDTO received by parameter.
     * The method verifies if the Item already exists in the repository before
     * the creation with the method isBlackListed.
     * Then it saves it through the Interface IBlackListItem in the Data Base Repository.
     *
     * @param inputUrlDTO the NewBlackListInfoDTO object that contains an Url as a String.
     * @return BlackListDTO assembled through the BlackListDomainDTOAssembler wrapped in an Optional.
     * @throws MalformedURLException
     */

    public Optional<BlackListDTO> createAndSaveBlackListItem(NewBlackListInfoDTO inputUrlDTO) throws MalformedURLException {
        BlackListItem blackListItem = new BlackListItem(inputUrlDTO.getUrl());
        if (iBlackListItem.isBlackListed(blackListItem)) {
            return Optional.empty();
        }BlackListItem savedBlackListItem = iBlackListItem.saveBlackListItem(blackListItem);
        return Optional.of(assembler.toDTO(savedBlackListItem));
    }

    /**
     * Method that allows an Item (url) of the BlackList to be removed.
     *
     * @param blackListInfoDTO
     * @return a deleted blackListItem
     * @throws MalformedURLException
     */

    public boolean deleteBlackListItem(NewBlackListInfoDTO blackListInfoDTO) throws MalformedURLException {
        String url = blackListInfoDTO.getUrl();
        BlackListItem blackListItem = new BlackListItem(url);
        return iBlackListItem.deleteByBlackListUrl(blackListItem);
    }

    /**
     * Method that allows the search for all the BlacklList items in the repository.
     *
     * @return list of blackListDTOS
     * @throws MalformedURLException
     */

    public List<BlackListDTO> getAllBlackListItems() throws MalformedURLException {
        List<BlackListItem> blackListItems = iBlackListItem.findAllBlackListItems();

        List<BlackListDTO> blackListDTOS = new ArrayList<>();

        for (BlackListItem blackList : blackListItems) {
            BlackListDTO blackListDTO = assembler.toDTO(blackList);
            blackListDTOS.add(blackListDTO);
        }

        return blackListDTOS;
    }

    /**
     * The method that verifies if an Item already exists in the repository.
     *
     * @param inputBlackList
     * @return boolean that difines if the url is arleady present in the repository list or not.
     * @throws MalformedURLException
     */

    public boolean isBlackListed(NewBlackListInfoDTO inputBlackList) throws MalformedURLException {
        BlackListItem blackList = new BlackListItem(inputBlackList.getUrl());
        return iBlackListItem.isBlackListed(blackList);
    }
}
