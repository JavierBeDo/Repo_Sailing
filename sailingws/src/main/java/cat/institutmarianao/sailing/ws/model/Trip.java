package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

/* Lombok */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "trips")
@JsonPropertyOrder({ "id", "client", "places", "departure", "status", "tracking" })
@JsonIgnoreProperties({ "serialVersionUID" })
public class Trip implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_DESCRIPTION = 500;

	public static final String RESERVED = "RESERVED";
	public static final String RESCHEDULED = "RESCHEDULED";
	public static final String CANCELLED = "CANCELLED";
	public static final String DONE = "DONE";

	public enum Status {
		RESERVED, RESCHEDULED, CANCELLED, DONE
	}

	/* Lombok */
	@EqualsAndHashCode.Include
	@Id
	private Long id;

	// Cambiar este campo para que sea solo el username
	@ManyToOne
	// @Column(name = "client_username", length = 255, nullable = false)
	@JoinColumn(name = "client_username", referencedColumnName = "username")
	private Client client;

	private int places;

	// @Column(name = "departure_id", length = 20)
	@ManyToOne
	@JoinColumn(name = "departure_id", nullable = false, referencedColumnName = "id")
	private Departure departure;

	/* Lombok */
	@Singular("track")
	@OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Action> tracking;

	/* JPA */
	@Enumerated(EnumType.STRING) // Stored as string
	/* Hibernate */
	@Formula("(SELECT CASE a.type " + "  WHEN '" + Action.BOOKING + "' THEN '" + Trip.RESERVED + "' " + "  WHEN '"
			+ Action.RESCHEDULING + "' THEN '" + Trip.RESCHEDULED + "' " + "  WHEN '" + Action.CANCELLATION + "' THEN '"
			+ Trip.CANCELLED + "' " + "  WHEN '" + Action.DONE + "' THEN '" + Trip.DONE + "' " + "  ELSE NULL END "
			+ "FROM actions a WHERE a.trip_id = id AND a.date = " + "  (SELECT MAX(last.date) FROM actions last "
			+ "   WHERE last.trip_id = a.trip_id)" + ")")
	// Lombok
	@Setter(AccessLevel.NONE)
	private Status status;
}
