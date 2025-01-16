package cat.institutmarianao.sailing.ws.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.institutmarianao.sailing.ws.model.TripType;

public interface TripTypeRepository extends JpaRepository<TripType, Long> {

	// Buscar por ID
	@Override
	Optional<TripType> findById(Long id);

	// Buscar todos los tipos de viaje
	@Override
	List<TripType> findAll();
}
