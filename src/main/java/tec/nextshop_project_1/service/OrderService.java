// PAQUETES
package tec.nextshop_project_1.service;

// IMPORTACIÓN DE LIBRERÍAS
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tec.nextshop_project_1.data.*;
import tec.nextshop_project_1.repository.interfaces.IOrderRepository;
import tec.nextshop_project_1.repository.interfaces.IShoppingCartRepository;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Clase perteneciente a la capa de servicio, encargada de la lógica de negocio
 * para la gestión de pedidos de productos.
 */
@Slf4j
@Service
public class OrderService {

    // OBJETOS/VARIABLES GLOBALES
    private final IOrderRepository oderRepository;

    /**
     * Constructor de clase con inyección de dependencias.
     * @param oderRepository
     */
    public OrderService(IOrderRepository oderRepository) {
        this.oderRepository = oderRepository;
    }


}
