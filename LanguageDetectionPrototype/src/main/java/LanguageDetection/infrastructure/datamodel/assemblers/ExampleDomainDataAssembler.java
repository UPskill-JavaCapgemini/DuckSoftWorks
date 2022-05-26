package LanguageDetection.infrastructure.datamodel.assemblers;

import com.example.domain.entities.example.Example;
import com.example.infrastructure.datamodel.ExampleJpa;
import org.springframework.stereotype.Service;

@Service
public class ExampleDomainDataAssembler {

	public ExampleJpa toData(Example example ) {
		return new ExampleJpa(example.getId(), example.getName());
	}

	public Example toDomain( ExampleJpa example) {
		return new Example( example.getId(), example.getName() );
	}
}