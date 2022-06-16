package LanguageDetection.application.services;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.DTOAssemblers.BlackListDomainDTOAssembler;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.domain.DomainService.BlackListService;
import LanguageDetection.domain.ValueObjects.BlackListUrl;
import LanguageDetection.domain.entities.BlackListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlackListManagementService {

    @Autowired
    BlackListService blackListService;

    @Autowired
    BlackListDomainDTOAssembler blackListDomainDTOAssembler;

    /**
     * Creates a new BlackListItem with a NewBlackListInfoDTO received by parameter.
     * The method verifies if the Item already exists in the repository before
     * the creation with the method isBlackListed.
     * Then it saves it through the Interface IBlackListItemRepository in the Data Base Repository.
     *
     * @param blackListInputUrlDTO the NewBlackListInfoDTO object that contains an Url as a String.
     * @return BlackListDTO assembled through the BlackListDomainDTOAssembler wrapped in an Optional.
     */
    public Optional<BlackListDTO> createAndSaveBlackListItem(NewBlackListInfoDTO blackListInputUrlDTO) {
        try {
            BlackListItem blackListItem = new BlackListItem(blackListInputUrlDTO.getUrl());
            BlackListItem blackListToRepo = blackListService.saveBlackListItem(blackListItem);
            return Optional.of(blackListDomainDTOAssembler.toDTO(blackListToRepo));
        }catch (MalformedURLException e){
            return Optional.empty();
        }
    }

    /**
     * Method that allows an Item (url) of the BlackList to be removed.
     *
     * @param blackListInfoDTO
     * @return a deleted blackListItem
     */

    public boolean deleteBlackListItem(NewBlackListInfoDTO blackListInfoDTO) {
        try {
            String url = blackListInfoDTO.getUrl();
            BlackListUrl blackListUrl = new BlackListUrl(url);
            return blackListService.deleteByBlackListUrl(blackListUrl);
        } catch (MalformedURLException urlException){
            return false;
        }
    }

    /**
     * Method that allows the search for all the BlacklList items in the repository.
     *
     * @return list of blackListDTOS
     */

    public List<BlackListDTO> getAllBlackListItems() {
        List<BlackListItem> blackListItems = blackListService.findAllBlackListItems();

        List<BlackListDTO> blackListDTOS = new ArrayList<>();

        for (BlackListItem blackListItem : blackListItems) {
            BlackListDTO blackListDTO = blackListDomainDTOAssembler.toDTO(blackListItem);
            blackListDTOS.add(blackListDTO);
        }

        return blackListDTOS;
    }
}
