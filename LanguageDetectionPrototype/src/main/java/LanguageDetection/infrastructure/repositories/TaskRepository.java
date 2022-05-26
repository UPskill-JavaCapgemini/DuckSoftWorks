package LanguageDetection.infrastructure.repositories;

import com.example.domain.entities.example.Example;
import com.example.infrastructure.datamodel.ExampleJpa;
import com.example.infrastructure.datamodel.assemblers.ExampleDomainDataAssembler;
import com.example.infrastructure.repositories.jpa.ExampleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {

	@Autowired
	ExampleJpaRepository exampleJpaRepository;

	@Autowired
	ExampleDomainDataAssembler exampleAssembler;

	public Example save( Example example ) {
		ExampleJpa exampleJpa = exampleAssembler.toData(example);
		ExampleJpa savedExampleJpa = exampleJpaRepository.save( exampleJpa );
		return exampleAssembler.toDomain(savedExampleJpa);
	}

	public Optional<Example> findById(Long id) {
		Optional<ExampleJpa> example = exampleJpaRepository.findById(id);
		return this.toExample(example);
	}

	public List<Example> findByNameContaining(String name) {
		List<ExampleJpa> examples = exampleJpaRepository.findByNameContaining(name);
		return this.toExamples(examples);
	}

	public List<Example> findAll() {
		List<ExampleJpa> examples = exampleJpaRepository.findAll();
		return this.toExamples(examples);
	}

	public List<Example> findAllCustomQuery() {
		List<ExampleJpa> examples = exampleJpaRepository.customQuery();
		return this.toExamples(examples);
	}

	/**
	 * Transform a Optional Jpa into Optional domain model.
	 * @param jpa a Jpa to be transformed
	 * @return a domain model object
	 */
	private Optional<Example> toExample(Optional<ExampleJpa> jpa) {
		if ( jpa.isPresent() ) {
			Example example = exampleAssembler.toDomain(jpa.get());
			return Optional.of( example );
		}
		else
			return Optional.empty();
	}

	/**
	 * Transform a List of Jpa into a List of domain model objects.
	 * @param jpas a list of Jpas to be transformed
	 * @return a List of domain model objects
	 */
	private List<Example> toExamples(List<ExampleJpa> jpas) {
		List<Example> countries = new ArrayList<>();
		for (ExampleJpa example: jpas) {
			countries.add(exampleAssembler.toDomain(example));
		}
		return countries;
	}
}