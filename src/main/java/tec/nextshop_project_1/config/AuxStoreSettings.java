// PAQUETES
package tec.nextshop_project_1.config;

// IMPORTACIÓN DE LIBRERÍAS
import lombok.Getter;
import org.springframework.stereotype.Service;

/**
 * Clase auxiliar para configuración general de la tienda,
 * como el porcentaje de impuesto y los costos de envío.
 * En una implementación real, estos valores normalmente formarían
 * parte del modelo de datos y serían administrados mediante entidades,
 * servicios y repositorios. Sin embargo, debido al alcance y limitantes
 * académicas y de tiempo del proyecto, se opta por representarlos
 * mediante variable/constantes de configuración.
 */
@Service
public class AuxStoreSettings {

        @Getter
        private double tax = 0;
        @Getter
        private double shippingCost = 0;

        /**
         * Constructor de clase
         */
        public AuxStoreSettings(){
            this.tax = 0.13;
            this.shippingCost = 0;
        }

}
