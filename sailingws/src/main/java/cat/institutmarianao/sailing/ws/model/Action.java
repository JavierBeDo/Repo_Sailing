package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "actions", indexes = { @Index(name = "idx_actions_performer", columnList = "performer_username"),
		@Index(name = "idx_actions_trip", columnList = "trip_id") })
//@DiscriminatorValue(value = "Type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorColumn(name = "type")

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")

@JsonSubTypes({ @JsonSubTypes.Type(value = Booking.class, name = "BOOKING"),
		@JsonSubTypes.Type(value = Rescheduling.class, name = "RESCHEDULING"),
		@JsonSubTypes.Type(value = Cancellation.class, name = "CANCELLATION"),
		@JsonSubTypes.Type(value = Done.class, name = "DONE") })

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
	@Column(insertable = false, updatable = false, nullable = false)
	protected Type type;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "performer_username", referencedColumnName = "username", nullable = false)
	protected User performer;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	// @Temporal(TemporalType.TIMESTAMP)
	protected Date date = new Date();

	@Column(name = "new_date", columnDefinition = "DATE")
	// @Temporal(TemporalType.DATE)
	protected Date newDate;

	@Column(name = "new_departure", columnDefinition = "TIME")
	// @Temporal(TemporalType.TIME)
	protected Date newDeparture;

	@Column(name = "old_date", columnDefinition = "DATE")
	// @Temporal(TemporalType.DATE)
	protected Date oldDate;

	@Column(name = "old_departure", columnDefinition = "TIME")
	// @Temporal(TemporalType.TIME)
	protected Date oldDeparture;

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
