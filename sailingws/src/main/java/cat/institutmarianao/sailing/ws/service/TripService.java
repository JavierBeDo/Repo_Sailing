package cat.institutmarianao.sailing.ws.service;

import java.util.Date;
import java.util.List;

import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.Trip.Status;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface TripService {

	List<Trip> findAll(Category category, Status status, String client, Date from, Date to);

	Trip getById(@NotNull Long id);

	List<Trip> findAllByClientUsername(@NotBlank String username, Category category, Status status, Date startDate,
			Date endDate);

	Trip save(@NotNull @Valid Trip trip);

	Action saveAction(@NotNull Action action);

}
