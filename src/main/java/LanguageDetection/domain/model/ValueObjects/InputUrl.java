package LanguageDetection.domain.model.ValueObjects;
import LanguageDetection.domain.shared.ValueObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


@Embeddable
@Component
/**
 * Represents an InputUrl . The url that was inputted by the user for text language analysis
 * It validates if the inputted url string ends with a TXT extension and if the text contain within it is compliant with UTF_8 charset
 */
public class InputUrl implements ValueObject, Comparable<InputUrl> {


    private final java.net.URL url;

    @Transient
    Text text;


    public InputUrl(String url) throws MalformedURLException {
        if(StringUtils.endsWithAny(url.toLowerCase(),".txt")) {
            this.url = new URL(url);
        } else {
            throw new IllegalArgumentException("The URL doesn't contain a txt file or it´s not valid");
        }
        try {
            String extractedText = parseContentOfUrlToString(this.url);
            this.text = new Text(extractedText);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method represents an InputUrl textually
     * Returns a String with the InputUrl information
     *
     * @return a String with a textual representation of the InputUrl
     */
    @Override
    public String toString() {
        return this.url.toString();
    }

    /**
     * The empty Constructor for creating an InputUrl. Used for ORM purposes
     */
    protected InputUrl(){
        this.url=null;
    }

    /**
     * This method returns the Url
     *
     * @return the Url
     */
    public URL getUrlObject(){
        return this.url;
    }
    /**
     * This method returns the text of InputUrl
     *
     * @return the Text of InputUrl
     */
    public Text getTextOfUrl() { return this.text;}

    @Override
    public int compareTo(@NotNull InputUrl o) {
        assert url != null;
        return url.toString().compareTo(o.getUrlObject().toString());
    }
    /**
     * This method parses the content of the Url to String. It is used for Text content validation in agreement with UTF_8 charset
     *
     * @throws IOException if the parsed content of the Text is not compliant with UTF_8
     * @return the parsed String with the Text content
     */
    protected String parseContentOfUrlToString(URL url) throws IOException {
        try (InputStream inputStream = url.openStream()) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
    }
}