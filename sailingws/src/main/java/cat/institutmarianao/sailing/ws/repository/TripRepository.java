package cat.institutmarianao.sailing.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.institutmarianao.sailing.ws.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

}
