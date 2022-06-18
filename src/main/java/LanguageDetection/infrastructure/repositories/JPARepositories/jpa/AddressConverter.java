package LanguageDetection.infrastructure.repositories.JPARepositories.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AddressConverter implements AttributeConverter<AddressJpa, String> {

    private static final String SEPARATOR = ", ";

	@Override
    public String convertToDatabaseColumn(AddressJpa address) {
        if (address == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (address.getStreet() != null && !address.getStreet()
            .isEmpty()) {
            sb.append(address.getStreet());
            sb.append(SEPARATOR);
        }

		if (address.getCity() != null && !address.getCity()
            .isEmpty()) {
            sb.append(address.getCity());
            sb.append(SEPARATOR);
        }

		if (address.getPostalCode() != null && !address.getPostalCode()
			.isEmpty()) {
			sb.append(address.getPostalCode());
			sb.append(SEPARATOR);
		}

        return sb.toString();
    }


    @Override
    public AddressJpa convertToEntityAttribute(String dbAddress) {
        if (dbAddress == null || dbAddress.isEmpty()) {
            return null;
        }

        String[] pieces = dbAddress.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

		String street="", city="", postalCode="";
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        if (dbAddress.contains(SEPARATOR)) {
            street = firstPiece;

            if (pieces.length >= 2 && pieces[1] != null && !pieces[1].isEmpty()) {
				city = pieces[1];
				
				if (pieces.length >= 3 && pieces[2] != null && !pieces[2].isEmpty()) {
					postalCode = pieces[2];
				}
            }
        }
        String countryCode = pieces[3];

        AddressJpa address = new AddressJpa(street, city, postalCode, countryCode, null);
        return address;
    }
}