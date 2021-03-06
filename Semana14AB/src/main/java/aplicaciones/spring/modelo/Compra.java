package aplicaciones.spring.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.sun.istack.NotNull;
@Entity
@Table(name = "CONPRAS")
public class Compra implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@NotNull
	private String producto;
	@NotNull
	private String proveedor;
	@NotNull
	private Integer cantidad;
	private Double total;	
	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}			
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setCliente(String proveedor) {
		this.proveedor = proveedor;
	}
	@Override
	public String toString() {
		return "Venta [id=" + id + ", fecha=" + fecha + ", producto_id=" + producto + ", proveedor_id=" + proveedor
				+ ", cantidad=" + cantidad + ", total=" + total + "]";
	}

}
