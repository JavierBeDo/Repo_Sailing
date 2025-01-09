package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
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
//@DiscriminatorColumn(name = "type")
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

	/* Lombok */
	@EqualsAndHashCode.Include

	/* Lombok */
	@NonNull
	@Enumerated(EnumType.STRING)
	protected Type type;

	@OneToMany(mappedBy = "username")
	protected List<User> performer;

	protected Date date = new Date();

	@ManyToOne
	protected Trip trip;

	@JoinColumn
	protected Long idTrip;

	public String getInfo() {
		return "";
	}
}
