package cat.institutmarianao.sailing.ws.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cat.institutmarianao.sailing.ws.model.Action.Type;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.TripType.Category;

public interface TripRepository extends JpaRepository<Trip, Long> {

	// Buscar por ID
	@Override
	Optional<Trip> findById(Long id);

	// Buscar todos los viajes
	@Override
	List<Trip> findAll();

	@Query("SELECT t FROM Trip t " + "JOIN t.departure d " + "JOIN d.tripType tt " + "JOIN t.tracking a "
			+ "WHERE (:category IS NULL OR tt.category = :category) " + " AND (:type IS NULL OR a.type = :type)"
			+ "AND (:clientUsername IS NULL OR t.client.username LIKE CONCAT('%', :clientUsername, '%')) "
			+ "AND (:startDate IS NULL OR d.date >= :startDate) " + "AND (:endDate IS NULL OR d.date <= :endDate) "
			+ "ORDER BY t.id ASC")

	List<Trip> findAllByFilters(@Param("category") Category category, @Param("type") Type type,
			@Param("clientUsername") String clientUsername, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);

}
