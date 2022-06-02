package LanguageDetection.infrastructure.datamodel;

import LanguageDetection.domain.ValueObjects.InputUrl;
import LanguageDetection.domain.ValueObjects.TimeOut;
import LanguageDetection.domain.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="blacklist")
public class BlackListJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    long id;
    @Getter
    String url;

    public BlackListJPA(String url) {
        this.url = url;
    }
}
