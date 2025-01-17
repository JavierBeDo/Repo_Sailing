package cat.institutmarianao.sailing.ws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.sailing.ws.model.TripType;
import cat.institutmarianao.sailing.ws.model.TripType.Category;
import cat.institutmarianao.sailing.ws.repository.TripTypeRepository;
import cat.institutmarianao.sailing.ws.service.TripTypeService;
import jakarta.validation.constraints.NotBlank;

@Validated
@Service
public class TripTypeServiceImpl implements TripTypeService {

	@Autowired
	private TripTypeRepository tripTypeRepository;

	@Override
	public List<TripType> findAll(Category category, Double priceFrom, Double priceTo, Integer maxPlacesFrom,
			Integer maxPlacesTo, Integer durationFrom, Integer durationTo) {
		return tripTypeRepository.findAllByFilters(category, priceFrom, priceTo, maxPlacesFrom, maxPlacesTo,
				durationFrom, durationTo);
	}

	@Override
	public TripType findById(@NotBlank Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
