package LanguageDetection.application.services;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.DTOAssemblers.BlackListDomainDTOAssembler;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.domain.DomainService.BlackListService;
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
     * @throws MalformedURLException
     */

    /*public Optional<BlackListDTO> createAndSaveBlackListItem(NewBlackListInfoDTO blackListInputUrlDTO) throws MalformedURLException {
        if (isUrlValid(blackListInputUrlDTO.getUrl()) && !isBlackListed(blackListInputUrlDTO)) {
            BlackListItem blackListItem = new BlackListItem(blackListInputUrlDTO.getUrl());
            Optional<BlackListItem> persistedBlackListItem = iBlackListItemRepository.findByBlackListItem(blackListItem);
            if (persistedBlackListItem.isEmpty()) {
                try {
                    BlackListItem blackListToRepo = iBlackListItemRepository.saveBlackListItem(blackListItem);
                    return Optional.of(blackListDomainDTOAssembler.toDTO(blackListToRepo));
                } catch (IllegalArgumentException e){
                    return Optional.empty();
                }
            }
        }
        return Optional.empty();
    }*/
    public Optional<BlackListDTO> createAndSaveBlackListItem(NewBlackListInfoDTO blackListInputUrlDTO) {
        try {
            BlackListItem blackListItem = new BlackListItem(blackListInputUrlDTO.getUrl());
            Optional<BlackListItem> blackListToRepo = blackListService.saveBlackListItem(blackListItem);
            if(blackListToRepo.isPresent()){
                return Optional.of(blackListDomainDTOAssembler.toDTO(blackListToRepo.get()));
            } else {
                return Optional.empty();
            }
        }catch (MalformedURLException e){
            return Optional.empty();
        }
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
        return blackListService.deleteByBlackListUrl(blackListItem);
    }

    /**
     * Method that allows the search for all the BlacklList items in the repository.
     *
     * @return list of blackListDTOS
     * @throws MalformedURLException
     */

    public List<BlackListDTO> getAllBlackListItems() throws MalformedURLException {
        List<BlackListItem> blackListItems = blackListService.findAllBlackListItems();

        List<BlackListDTO> blackListDTOS = new ArrayList<>();

        for (BlackListItem blackListItem : blackListItems) {
            BlackListDTO blackListDTO = blackListDomainDTOAssembler.toDTO(blackListItem);
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
        return blackListService.isBlackListed(blackList);
    }
}
