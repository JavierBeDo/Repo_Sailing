package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

/* Swagger */
@Schema(oneOf = { Booking.class, Rescheduling.class, Cancellation.class, Done.class }, discriminatorProperty = "type")
/* Lombok */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "actions")
@DiscriminatorValue(value = "Type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorColumn(name = "type")g
public abstract class Action implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Values for type - MUST be constants */
	public static final String BOOKING = "BOOKING";
	public static final String RESCHEDULING = "RESCHEDULING";
	public static final String CANCELLATION = "CANCELLATION";
	public static final String DONE = "DONE";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	public enum Type {
		BOOKING, RESCHEDULING, CANCELLATION, DONE
	}

	@EqualsAndHashCode.Include

	/* Lombok */
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(insertable = false, updatable = false)
	protected Type type;

	@OneToMany(mappedBy = "username")
	protected List<User> performer;

	@Column(name = "date", columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date date = new Date();

	@Column(name = "new_date", columnDefinition = "DATE")
	@Temporal(TemporalType.DATE)
	protected Date newDate = new Date();

	@Column(name = "new_departure", columnDefinition = "TIME")
	@Temporal(TemporalType.TIME)
	protected Date newDeparture = new Date();

	@Column(name = "old_date", columnDefinition = "DATE")
	@Temporal(TemporalType.DATE)
	protected Date oldDate = new Date();

	@Column(name = "old_departure", columnDefinition = "TIME")
	@Temporal(TemporalType.TIME)
	protected Date oldDeparture = new Date();

	@ManyToOne
	@JoinColumn(name = "trip_id", nullable = false, referencedColumnName = "id")
	protected Trip trip;

	// @JoinColumn
//	protected Long idTrip;

	@JsonIgnore
	public String getInfo() {
		return "";
	}
}
