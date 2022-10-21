package wlopera.lab.store.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "El n�mero de documento no puede ser vac�o")
	@Size(min = 0, max = 8, message = "El tama�o del n�mero de documento es 8")
	@Column(name = "number_id", unique = true, length = 8, nullable = false)
	private String numberID;
	
	@NotEmpty(message = "El nombre no puede ser vac�o")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@NotEmpty(message = "El apellido no puede ser vac�o")
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotEmpty(message = "El correo no puede ser vac�o")
	@Email(message = "No es una direcci�n de correo v�lida")
	@Column(unique = true, nullable = false)
	private String email;
	
	
	@Column(name = "photo_url")
	private String photoUrl;
	
	@NotNull(message = "La regi�n no puede estar vac�a")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Region region;
	
	private String state;
}
