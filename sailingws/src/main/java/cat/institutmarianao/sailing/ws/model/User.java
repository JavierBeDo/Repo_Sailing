package cat.institutmarianao.sailing.ws.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/* Swagger */
@Schema(oneOf = { Client.class, Admin.class }, discriminatorProperty = "role")
/* Lombok */
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "user")
@DiscriminatorValue(value = "Role")
@DiscriminatorColumn(name = "role")
public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/* Values for role - MUST be constants (can not be enums) */
	public static final String ADMIN = "ADMIN";
	public static final String CLIENT = "CLIENT";

	public enum Role {
		ADMIN, CLIENT
	}

	public static final int MIN_USERNAME = 2;
	public static final int MAX_USERNAME = 25;
	public static final int MIN_PASSWORD = 10;

	/* Lombok */
	@EqualsAndHashCode.Include
	@Id
	protected String username;

	protected String password;

	@Enumerated(EnumType.STRING)
	protected Role role;

	public abstract String getInfo();

	public boolean isAdmin() {
		return false;
	}

}
