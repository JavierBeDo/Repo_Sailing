package cat.institutmarianao.sailing.ws.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.institutmarianao.sailing.ws.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

	// Buscar por ID
	@Override
	Optional<Trip> findById(Long id);

	// Buscar todos los viajes
	@Override
	List<Trip> findAll();

	// Buscar todos los viajes por nombre de usuario del cliente
	List<Trip> findAllByClient(String clientUsername);
}
