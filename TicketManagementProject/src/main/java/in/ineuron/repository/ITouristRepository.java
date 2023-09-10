package in.ineuron.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ineuron.model.Tourist;

public interface ITouristRepository extends JpaRepository<Tourist, Integer> {

	// Implementation class will be generated dynamically by Spring Data JPA using InMemory Proxy classes
	
}
