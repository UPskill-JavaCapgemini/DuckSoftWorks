package LanguageDetection.application.services;

import LanguageDetection.application.DTO.BlackListDTO;
import LanguageDetection.application.DTO.DTOAssemblers.BlackListDomainDTOAssembler;
import LanguageDetection.application.DTO.NewBlackListInfoDTO;
import LanguageDetection.domain.DomainService.BlackListService;
import LanguageDetection.domain.model.ValueObjects.BlackListUrl;
import LanguageDetection.domain.model.BlackListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
/**
 * Represents the BlackListManagementService. The applicational service for BlackList functionalities
 */
public class BlackListManagementService {

    @Autowired
    BlackListService blackListService;

    @Autowired
    BlackListDomainDTOAssembler blackListDomainDTOAssembler;

    /**
     * This method attempts to create and save a blackListItem with the information provided by the admin.
     *
     * @param blackListInputUrlDTO the NewBlackListInfoDTO containing the information about the BlackListItem to be created and saved
     * @return BlackListDTO assembled through the BlackListDomainDTOAssembler wrapped in an Optional if successful or an empty Optional
     */
    public Optional<BlackListDTO> createAndSaveBlackListItem(NewBlackListInfoDTO blackListInputUrlDTO) {
        try {
            BlackListItem blackListItem = new BlackListItem(blackListInputUrlDTO.getUrl());
            BlackListItem blackListToRepo = blackListService.saveBlackListItem(blackListItem);
            return Optional.of(blackListDomainDTOAssembler.toDTO(blackListToRepo));

        }catch (MalformedURLException  | IllegalArgumentException ie){
            return Optional.empty();
        }
    }

    /**
     * This method deletes a persisted BlackListItem if the item is persisted in the database
     * Returns a boolean that indicates if the deletion operation was successful
     *
     *
     * @param blackListInfoDTO the NewBlackListInfoDTO containing the information about the BlackListItem to be deleted
     * @return true if the BlackListItem has been successfully deleted, false if not
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
     * This method fetches information for all blackListItems persisted in the database and returns a list of BlackListDTO
     * containing them if there are any, or an empty list if no BlackListItems were persisted in the database
     *
     * @return a BlackListDTO list
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
