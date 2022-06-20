package LanguageDetection.domain.util;

import LanguageDetection.application.security.services.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {

    public static Long getUserNameId() {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getId();
    }
}
