package cat.institutmarianao.sailing.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;

public interface TripTypeRepository extends JpaRepository<TripType, Long> {

//	// Buscar por ID
//	@Override
//	Optional<TripType> findById(Long id);
//
//	// Buscar todos los tipos de viaje
//	@Override
//	List<TripType> findAll();

	@Query("SELECT u FROM TripType u WHERE" + "(u.category IN ?1 OR ?1 IS NULL) AND"
			+ "((u.price BETWEEN ?2 AND ?3) OR (?2 IS NULL OR ?3 IS NULL)) AND"
			+ "((u.maxPlaces BETWEEN ?4 AND ?5) OR (?4 IS NULL OR ?3 IS NULL)) AND"
			+ "((u.duration BETWEEN ?6 AND ?7) OR (?6 IS NULL OR ?7 IS NULL))" + "ORDER BY u.id ASC")
	List<TripType> findAllByFilters(Category category, Double priceFrom, Double priceTo, Integer maxPlacesFrom,
			Integer maxPlacesTo, Integer durationFrom, Integer durationTo);
}
