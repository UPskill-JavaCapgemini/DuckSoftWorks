package LanguageDetection.domain.ValueObjects;
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

    @Override
    public String toString() {
        return this.url.toString();
    }

    /* For ORM purposes */
    protected InputUrl(){
        this.url=null;
    }

    public URL getUrlObject(){
        return this.url;
    }

    public Text getTextOfUrl() { return this.text;}

    @Override
    public boolean equals(Object otherURL) {
        InputUrl url = (InputUrl) otherURL;
        if (url.getUrlObject().getHost() == this.getUrlObject().getHost()) {
            return true;
        } else return false;
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }

    @Override
    public int compareTo(@NotNull InputUrl o) {
        assert url != null;
        return url.toString().compareTo(o.getUrlObject().toString());
    }

    protected String parseContentOfUrlToString(URL url) throws IOException {
        try (InputStream inputStream = url.openStream()) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
    }
}