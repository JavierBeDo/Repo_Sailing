package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

@Entity
@Table(name = "trips")
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
	@Column(name = "client_username", length = 255, nullable = false)
	private String client;

	private int places;

	@Column(name = "departure_id", length = 20)
	@JoinColumn(name = "departure_id", nullable = false, referencedColumnName = "id")
	private Long departure;

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
