package LanguageDetection.application.dtos.assemblers;

import LanguageDetection.application.dtos.AnalysisDTO;
import LanguageDetection.domain.entities.example.Language;
import org.springframework.stereotype.Service;


@Service
public class AnalysisDTOAssembler {

    private AnalysisDTOAssembler() {
    }

    public AnalysisDTO toDTO(Language language) {
        return new AnalysisDTO(language);
    }
}