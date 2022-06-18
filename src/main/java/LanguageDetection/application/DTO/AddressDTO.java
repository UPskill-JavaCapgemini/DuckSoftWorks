package LanguageDetection.application.DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ToString
public class AddressDTO extends RepresentationModel<AddressDTO> {
     @Getter
     String street;
     @Getter
     String city;
     @Getter
     String postalCode;
     @Getter
     String countryCode;
}
