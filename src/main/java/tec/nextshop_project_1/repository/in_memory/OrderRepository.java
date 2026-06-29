// PAQUETES
package tec.nextshop_project_1.repository.in_memory;

// IMPORTACIÓN DE LIBRERÍAS
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.repository.interfaces.IOrderRepository;

@Repository
@Profile("in-memory")
public class OrderRepository implements IOrderRepository {

}
