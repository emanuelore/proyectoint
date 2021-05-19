package aplicaciones.spring.controlador;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import aplicaciones.spring.modelo.Producto;
import aplicaciones.spring.modelo.Compra;
import aplicaciones.spring.servicios.ProveedorService;
import aplicaciones.spring.servicios.ProductoService;
import aplicaciones.spring.servicios.CompraService;
@Controller
@RequestMapping("/compras")
@SessionAttributes("compra")
public class CompraController {
	@Autowired
	@Qualifier("compra")
	CompraService compraService;
	
	@Autowired
	@Qualifier("proveedor")
	ProveedorService proveedorService;
	
	@Autowired
	@Qualifier("producto")
	ProductoService productoService;
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		List<Compra> compras = compraService.listar();
		model.addAttribute("compras",compras);
		model.addAttribute("titulo","Lista de Compras");
		return "compraListar";
	}
	
	@RequestMapping("/form")
	public String formulario(Model model) {
		Compra compra= new Compra();
		model.addAttribute("compra", compra);
		model.addAttribute("productos", productoService.listar());
		model.addAttribute("proveedors", proveedorService.listar());
		model.addAttribute("btn", "Registrar compra");
		return "compraForm";
	}
	@RequestMapping(value="/insertar",method=RequestMethod.POST)
	public String guardar(@Validated Compra compra, Model model) {
		try {
			String id =compra.getProducto();
			Producto pro = productoService.buscar(id);

			if(compra.getCantidad() <= pro.getCantidad()) {
				int diferencia=pro.getCantidad()-compra.getCantidad();
				pro.setCantidad(diferencia);
				double total = pro.getPrecio() * compra.getCantidad();
				compra.setTotal(total);
				productoService.guardar(pro);
				compraService.guardar(compra);
			}else {
				model.addAttribute("ERROR", "No hay stock para este producto, solo tenemos un stock de: "+pro.getCantidad());
				compra= new Compra();
				model.addAttribute("venta", compra);
				model.addAttribute("productos", productoService.listar());
				model.addAttribute("proveedors", proveedorService.listar());
				model.addAttribute("btn", "Registrar Compra");
				return "compraForm";
			}
		} catch (Exception e) {
		}
		return "redirect:/compras/listar";
	}
	
	@RequestMapping("/form/{id}")
	public String actualizar (@PathVariable("id") Long id,Model model) {
		model.addAttribute("compra",compraService.buscar(id));
		model.addAttribute("productos", productoService.listar());
		model.addAttribute("proveedors", proveedorService.listar());
		model.addAttribute("btn","Actualiza Registro");
		return "compraForm";
	}

	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long id) {
		compraService.eliminar(id);
		return "redirect:/compras/listar";
	}
}