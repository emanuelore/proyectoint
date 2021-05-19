package aplicaciones.spring.repositorios;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import aplicaciones.spring.modelo.Compra;
@Repository
public interface ICompra extends JpaRepository<Compra, Serializable>{
	public abstract Compra findById(Long id);
}
