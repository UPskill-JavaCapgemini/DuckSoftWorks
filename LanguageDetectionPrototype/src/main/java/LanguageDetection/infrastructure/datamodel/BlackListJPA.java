package LanguageDetection.infrastructure.datamodel;

import LanguageDetection.domain.ValueObjects.InputUrl;
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
    InputUrl ulr;
}
