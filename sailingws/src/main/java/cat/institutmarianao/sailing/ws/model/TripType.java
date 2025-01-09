package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/* Lombok */
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "trip_types")
@DiscriminatorValue(value = "Category")
//@DiscriminatorColumn(name = "category")
public class TripType implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MIN_DEPARTURE_HOUR = 6;
	public static final int MAX_DEPARTURE_HOUR = 14;
	public static final String GROUP = "GROUP";
	public static final String PRIVATE = "PRIVATE";

	public enum Category {
		GROUP, PRIVATE
	}

	/* Lombok */
	@EqualsAndHashCode.Include

	@Id
	private Long id;

	@Enumerated(EnumType.STRING)
	private Category category;

	// @ManyToOne
	// @JoinColumn(name = "departures", nullable = false)
	private Departure departures; // Comma-separated values: 9:30;11:30;13:30

	private String description;

	private int duration;

	@Positive
	private int maxPlaces;

	@Positive
	private double price;

	private String title;
}
