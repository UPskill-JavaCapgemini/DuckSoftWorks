package LanguageDetection.infrastructure.datamodel;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="category")
public class CategoryJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    long id;
    @Getter
    String categoryName;

    public CategoryJpa(String categoryName) {
        this.categoryName = categoryName;
    }

}
