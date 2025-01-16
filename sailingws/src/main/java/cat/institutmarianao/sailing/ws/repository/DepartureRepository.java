package cat.institutmarianao.sailing.ws.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import cat.institutmarianao.sailing.ws.model.Departure;

public interface DepartureRepository extends JpaRepository<Departure, Long> {

	// Buscar una salida por tripTypeId, fecha y hora de salida
	@Nullable
	Departure findOneByTripTypeIdAndDateAndDeparture(Long tripTypeId, Date date, Date departure);

	// Obtener todas las salidas para un tipo de viaje y fecha específica
	List<Departure> findAllByTripTypeIdAndDate(Long tripTypeId, Date date);
}
