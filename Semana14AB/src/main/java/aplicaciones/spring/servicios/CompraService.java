package aplicaciones.spring.servicios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aplicaciones.spring.modelo.Compra;
import aplicaciones.spring.repositorios.ICompra;
@Service("compra")
public class CompraService {
	@Autowired
	ICompra iCompra;	
	public void guardar(Compra compra) {		
		iCompra.save(compra);
	}
	
	public List<Compra> listar(){
		return iCompra.findAll();
	}
	
	public Compra buscar(Long id) {
		return iCompra.findById(id);
	}
	
	public boolean eliminar(Long id) {
		try {
			iCompra.delete(iCompra.findById(id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
