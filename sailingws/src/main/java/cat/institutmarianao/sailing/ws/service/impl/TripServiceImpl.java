package cat.institutmarianao.sailing.ws.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.exception.ForbiddenException;
import cat.institutmarianao.sailing.ws.exception.NotFoundException;
import cat.institutmarianao.sailing.ws.model.Action;
import cat.institutmarianao.sailing.ws.model.Action.Type;
import cat.institutmarianao.sailing.ws.model.Trip;
import cat.institutmarianao.sailing.ws.model.Trip.Status;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.repository.ActionRepository;
import cat.institutmarianao.sailing.ws.repository.TripRepository;
import cat.institutmarianao.sailing.ws.security.JwtUtils;
import cat.institutmarianao.sailing.ws.service.TripService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class TripServiceImpl implements TripService {

	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private ActionRepository actionRepository;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public List<Trip> findAll(Category category, Status status, String client, Date from, Date to) {
		if (!jwtUtils.isAdmin())
			throw new ForbiddenException(
					messageSource.getMessage("error.Forbidden.trips.find", null, LocaleContextHolder.getLocale()));

		Type type = null;
		if (status != null) {
			type = getTypeFromStatus(status);
		}

		return tripRepository.findAllByFilters(category, type, client, from, to);
	}

	@Override
	public Trip getById(@NotNull Long id) {
		if (!jwtUtils.isAdmin())
			throw new ForbiddenException(
					messageSource.getMessage("error.Forbidden.trips.get", null, LocaleContextHolder.getLocale()));

		return tripRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(messageSource.getMessage("error.NotFound.resource.by.id",
						new String[] { "Trip", Long.toString(id) }, LocaleContextHolder.getLocale())));
	}

	@Override
	public List<Trip> findAllByClientUsername(@NotBlank String username, Category category, Status status,
			Date startDate, Date endDate) {
		if (!jwtUtils.isAdmin())
			throw new ForbiddenException(
					messageSource.getMessage("error.Forbidden.trips.get", null, LocaleContextHolder.getLocale()));

		Type type = null;
		if (status != null) {
			type = getTypeFromStatus(status);
		}

		return tripRepository.findAllByFilters(category, type, username, startDate, endDate);
	}

	@Override
	public Trip save(@NotNull @Valid Trip trip) {
		if (!jwtUtils.isAdmin())
			throw new ForbiddenException(
					messageSource.getMessage("error.Forbidden.trips.get", null, LocaleContextHolder.getLocale()));
		if (!(jwtUtils.isAdmin()))
			throw new ForbiddenException(
					messageSource.getMessage("error.Forbidden.trip.create", null, LocaleContextHolder.getLocale()));

		if (tripRepository.existsById(trip.getId()))
			throw new ValidationException(messageSource.getMessage("error.UserService.username.exists",
					new String[] { Long.toString(trip.getId()) }, LocaleContextHolder.getLocale()));

		return tripRepository.saveAndFlush(trip);
	}

	@Override
	public Action saveAction(@NotNull Action action) {
		if (!(jwtUtils.isAdmin()))
			throw new ForbiddenException(
					messageSource.getMessage("error.Forbidden.action.create", null, LocaleContextHolder.getLocale()));

		if (actionRepository.existsById(action.getId()))
			throw new ValidationException(messageSource.getMessage("error.Action.id.exists",
					new String[] { Long.toString(action.getId()) }, LocaleContextHolder.getLocale()));

		return actionRepository.saveAndFlush(action);
	}

	private static Action.Type getTypeFromStatus(Trip.Status status) {

		if (status.equals(Trip.Status.RESERVED))
			return Action.Type.BOOKING;

		else if (status.equals(Trip.Status.RESCHEDULED))
			return Action.Type.RESCHEDULING;

		else if (status.equals(Trip.Status.CANCELLED))
			return Action.Type.CANCELLATION;

		else if (status.equals(Trip.Status.DONE))
			return Action.Type.DONE;

		return null;
	}

}
