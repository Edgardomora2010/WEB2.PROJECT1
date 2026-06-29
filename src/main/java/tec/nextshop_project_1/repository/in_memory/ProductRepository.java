// PAQUETES
package tec.nextshop_project_1.repository.in_memory;

// IMPORTACIÓN DE LIBRERIAS
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Product;
import tec.nextshop_project_1.data.Property;
import tec.nextshop_project_1.data.Category;
import tec.nextshop_project_1.repository.interfaces.ICategoryRepository;
import tec.nextshop_project_1.repository.interfaces.IProductRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio simulado para la gestión de productos.
 * Mantiene la información en memoria para el uso de la aplicación
 * hasta que, en una segunda fase, se implemente una base de datos
 * real en MySQL.
 */
@Repository
@Profile("in-memory")
public class ProductRepository implements IProductRepository {

    // OBJETOS Y VARIABLES GLOBALES
    private final List<Product> productList = new ArrayList<>();
    private final ICategoryRepository categoryRepository;
    private Category technology;
    private Category home;
    private Category sports;
    private Category books;
    private Category fashion;
    private Category beauty;
    private Category toys;
    private Category others;

    /**
     * Constructor de clase con inyección de dependencia: CategoryRepository
     * @param categoryRepository
     */
    public ProductRepository(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Precarga productos en memoria para uso de aplicación
     */
    @PostConstruct
    public void init() {

        // Categorías pre-existentes
        technology = categoryRepository.findByCategoryName("Tecnología").orElseThrow();
        home = categoryRepository.findByCategoryName("Hogar").orElseThrow();
        sports = categoryRepository.findByCategoryName("Deportes").orElseThrow();
        books = categoryRepository.findByCategoryName("Libros").orElseThrow();
        fashion = categoryRepository.findByCategoryName("Moda").orElseThrow();
        beauty = categoryRepository.findByCategoryName("Belleza").orElseThrow();
        toys = categoryRepository.findByCategoryName("Juguetes").orElseThrow();
        others = categoryRepository.findByCategoryName("Otros").orElseThrow();

        // ----------------------------------------------------
        // TECNOLOGÍA - NORMALES
        // ----------------------------------------------------
        this.productList.add(new Product(1L, "USB-001", "Memoria USB 64 GB", "Dispositivo portátil de almacenamiento.", 6500, "/images/products/memoria-usb-64gb.png", technology, null, true));
        this.productList.add(new Product(2L, "CAM-001", "Webcam HD", "Cámara web para videollamadas.", 14500, "/images/products/webcam-hd-usb.png", technology, null, true));
        this.productList.add(new Product(3L, "CHR-001", "Cargador USB", "Cargador rápido para dispositivos móviles.", 9500, "/images/products/cargador-usb-rapido.png", technology, null, true));
        this.productList.add(new Product(4L, "STD-001", "Base para monitor", "Soporte para monitor de escritorio.", 12500, "/images/products/base-para-monitor.png", technology, null, true));
        this.productList.add(new Product(5L, "PRN-001", "Impresora multifuncional", "Impresora para hogar y oficina.", 125000, "/images/products/impresora-multifuncional.png", technology, Arrays.asList(new Property("Conectividad", "WiFi"), new Property("Tecnología", "Inyección de tinta")), true));
        this.productList.add(new Product(6L, "RTR-001", "Router WiFi 6", "Router de alta velocidad.", 58000, "/images/products/router-wifi-6.png", technology, Arrays.asList(new Property("Velocidad", "1800 Mbps"), new Property("Bandas", "Dual Band")), true));

        // ----------------------------------------------------
        // TECNOLOGÍA - DESTACADOS
        // ----------------------------------------------------
        this.productList.add(new Product(7L, "MOU-001", "Mouse inalámbrico", "Mouse óptico para uso diario.", 8500, "/images/products/mouse-inalambrico.png", technology, null, true, 0, LocalDateTime.now().minusDays(90), true));
        this.productList.add(new Product(8L, "SPK-001", "Parlante Bluetooth", "Parlante inalámbrico portátil.", 22000, "/images/products/parlante-bluetooth.png", technology, null, true, 0, LocalDateTime.now().minusDays(60), true));
        this.productList.add(new Product(9L, "MON-001", "Monitor LG 27 pulgadas", "Monitor para productividad.", 185000, "/images/products/monitor-lg-27-pulgadas.png", technology, Arrays.asList(new Property("Resolución", "2560x1440"), new Property("Frecuencia", "144 Hz")), true, 0, LocalDateTime.now().minusDays(20), true));
        this.productList.add(new Product(10L, "SSD-001", "Disco SSD Samsung", "Unidad de almacenamiento.", 65000, "/images/products/disco-ssd-samsung.png", technology, Arrays.asList(new Property("Capacidad", "1 TB"), new Property("Formato", "M.2 NVMe")), true, 0, LocalDateTime.now().minusDays(12), true));
        this.productList.add(new Product(11L, "TAB-001", "Tablet Samsung Galaxy", "Tablet para entretenimiento.", 215000, "/images/products/tablet-samsung-galaxy.png", technology, Arrays.asList(new Property("Pantalla", "10.4 pulgadas"), new Property("Almacenamiento", "128 GB")), true, 0, LocalDateTime.now().minusDays(8), true));
        this.productList.add(new Product(12L, "SMW-001", "Smartwatch deportivo", "Reloj inteligente.", 85000, "/images/products/smartwatch-deportivo.png", technology, Arrays.asList(new Property("Pantalla", "AMOLED"), new Property("Resistencia", "5 ATM")), true, 0, LocalDateTime.now().minusDays(5), true));

        // ----------------------------------------------------
        // TECNOLOGÍA - OFERTAS
        // ----------------------------------------------------
        this.productList.add(new Product(13L, "KEY-001", "Teclado mecánico", "Teclado mecánico para oficina y gaming.", 28500, "/images/products/teclado-mecanico.png", technology, null, false, 15, LocalDateTime.now().minusDays(45), true));
        this.productList.add(new Product(14L, "AUD-001", "Audífonos inalámbricos", "Audífonos Bluetooth de uso diario.", 18000, "/images/products/audifonos-inalambricos.png", technology, null, false, 20, LocalDateTime.now().minusDays(25), true));

        // ----------------------------------------------------
        // TECNOLOGÍA - DESTACADOS + OFERTA
        // ----------------------------------------------------
        this.productList.add(new Product(15L, "LAP-001", "Laptop Lenovo IdeaPad 5", "Laptop para estudio y trabajo.", 425000, "/images/products/laptop-lenovo-ideapad-5.png", technology, Arrays.asList(new Property("RAM", "16 GB"), new Property("SSD", "512 GB"), new Property("Procesador", "Ryzen 7")), true, 10, LocalDateTime.now().minusDays(15), true));
        this.productList.add(new Product(16L, "GPU-001", "Tarjeta gráfica RTX 4060", "Tarjeta gráfica para gaming.", 325000, "/images/products/tarjeta-grafica-rtx-4060.png", technology, Arrays.asList(new Property("Memoria", "8 GB"), new Property("Interfaz", "PCIe 4.0")), true, 15, LocalDateTime.now().minusDays(10), true));

        // ----------------------------------------------------
        // HOGAR - NORMALES
        // ----------------------------------------------------
        this.productList.add(new Product(17L, "LIC-001", "Licuadora", "Licuadora para bebidas y recetas.", 27500, "/images/products/licuadora-domestica.png", home, null, true));
        this.productList.add(new Product(18L, "PAN-001", "Sartén antiadherente", "Sartén para cocina diaria.", 12000, "/images/products/sarten-antiadherente.png", home, null, true));
        this.productList.add(new Product(19L, "CUT-001", "Juego de cuchillos", "Juego básico para cocina.", 19500, "/images/products/juego-de-cuchillos.png", home, null, true));
        this.productList.add(new Product(20L, "ORG-001", "Organizador de escritorio", "Organizador para artículos de oficina.", 8500, "/images/products/organizador-de-escritorio.png", home, null, true));
        this.productList.add(new Product(21L, "BSK-001", "Canasta de ropa", "Canasta plástica para lavandería.", 7500, "/images/products/canasta-de-ropa.png", home, null, true));
        this.productList.add(new Product(22L, "MIC-001", "Microondas", "Horno microondas doméstico.", 85000, "/images/products/microondas-domestico.png", home, Arrays.asList(new Property("Potencia", "1000 W"), new Property("Capacidad", "30 L")), true));
        this.productList.add(new Product(23L, "DIN-001", "Juego de comedor", "Comedor para seis personas.", 285000, "/images/products/juego-de-comedor.png", home, Arrays.asList(new Property("Material", "Madera"), new Property("Capacidad", "6 personas")), true));
        this.productList.add(new Product(24L, "MAT-001", "Colchón ortopédico", "Colchón para descanso.", 175000, "/images/products/colchon-ortopedico.png", home, Arrays.asList(new Property("Tamaño", "Queen"), new Property("Firmeza", "Media")), true));

        // ----------------------------------------------------
        // HOGAR - DESTACADOS
        // ----------------------------------------------------
        this.productList.add(new Product(25L, "LMP-001", "Lámpara de pie", "Lámpara decorativa para sala.", 32000, "/images/products/lampara-de-pie.png", home, null, true, 0, LocalDateTime.now().minusDays(70), true));
        this.productList.add(new Product(26L, "DSK-001", "Escritorio ejecutivo", "Escritorio para oficina.", 95000, "/images/products/escritorio-ejecutivo.png", home, Arrays.asList(new Property("Material", "Melamina"), new Property("Dimensiones", "120 x 50 cm")), true, 0, LocalDateTime.now().minusDays(18), true));
        this.productList.add(new Product(27L, "REF-001", "Refrigeradora", "Electrodoméstico para conservación.", 485000, "/images/products/refrigeradora.png", home, Arrays.asList(new Property("Capacidad", "300 L"), new Property("Tecnología", "No Frost")), true, 0, LocalDateTime.now().minusDays(9), true));

        // ----------------------------------------------------
        // HOGAR - OFERTAS
        // ----------------------------------------------------
        this.productList.add(new Product(28L, "CAF-001", "Cafetera", "Cafetera para uso doméstico.", 35000, "/images/products/cafetera-domestica.png", home, null, false, 15, LocalDateTime.now().minusDays(50), true));
        this.productList.add(new Product(29L, "VAC-001", "Aspiradora vertical", "Aspiradora para el hogar.", 92000, "/images/products/aspiradora-vertical.png", home, Arrays.asList(new Property("Potencia", "1200 W"), new Property("Depósito", "2 L")), false, 20, LocalDateTime.now().minusDays(14), true));

        // ----------------------------------------------------
        // HOGAR - DESTACADOS + OFERTA
        // ----------------------------------------------------
        this.productList.add(new Product(30L, "CHR-002", "Silla ergonómica", "Silla para oficina.", 115000, "/images/products/silla-ergonomica.png", home, Arrays.asList(new Property("Material", "Malla"), new Property("Ajustable", "Sí")), true, 10, LocalDateTime.now().minusDays(12), true));

        // ----------------------------------------------------
        // DEPORTES - NORMALES
        // ----------------------------------------------------
        this.productList.add(new Product(31L, "BKB-001", "Balón de baloncesto", "Balón para uso recreativo.", 14500, "/images/products/balon-de-baloncesto.png", sports, null, true));
        this.productList.add(new Product(32L, "JRP-001", "Cuerda para saltar", "Accesorio para entrenamiento cardiovascular.", 4500, "/images/products/cuerda-para-saltar.png", sports, null, true));
        this.productList.add(new Product(33L, "BOT-001", "Botella deportiva", "Botella reutilizable para hidratación.", 5500, "/images/products/botella-deportiva.png", sports, null, true));
        this.productList.add(new Product(34L, "DMB-001", "Mancuernas ajustables", "Equipo para entrenamiento.", 65000, "/images/products/mancuernas-ajustables.png", sports, Arrays.asList(new Property("Peso máximo", "20 kg"), new Property("Material", "Hierro")), true));
        this.productList.add(new Product(35L, "TEN-001", "Raqueta de tenis", "Raqueta para competición.", 45000, "/images/products/raqueta-de-tenis.png", sports, Arrays.asList(new Property("Peso", "300 g"), new Property("Material", "Grafito")), true));
        this.productList.add(new Product(36L, "BEN-001", "Banco para pesas", "Banco para gimnasio.", 72000, "/images/products/banco-para-pesas.png", sports, Arrays.asList(new Property("Capacidad", "250 kg"), new Property("Ajustable", "Sí")), true));

        // ----------------------------------------------------
        // DEPORTES - DESTACADOS
        // ----------------------------------------------------
        this.productList.add(new Product(37L, "SOC-001", "Balón de fútbol", "Balón para entrenamiento y recreación.", 13500, "/images/products/balon-de-futbol.png", sports, null, true, 0, LocalDateTime.now().minusDays(80), true));
        this.productList.add(new Product(38L, "TRD-001", "Caminadora eléctrica", "Máquina para ejercicio.", 385000, "/images/products/caminadora-electrica.png", sports, Arrays.asList(new Property("Potencia", "2 HP"), new Property("Velocidad máxima", "16 km/h")), true, 0, LocalDateTime.now().minusDays(6), true));

        // ----------------------------------------------------
        // DEPORTES - OFERTAS
        // ----------------------------------------------------
        this.productList.add(new Product(39L, "YOG-001", "Mat para yoga", "Superficie cómoda para ejercicios.", 16500, "/images/products/mat-para-yoga.png", sports, null, false, 20, LocalDateTime.now().minusDays(40), true));
        this.productList.add(new Product(40L, "TEN-001", "Raqueta de tenis", "Raqueta para competición.", 45000, "/images/products/raqueta-de-tenis.png", sports, Arrays.asList(new Property("Peso", "300 g"), new Property("Material", "Grafito")), false, 15, LocalDateTime.now().minusDays(18), true));

        // ----------------------------------------------------
        // DEPORTES - DESTACADOS + OFERTA
        // ----------------------------------------------------
        this.productList.add(new Product(41L, "BIK-001", "Bicicleta de montaña", "Bicicleta para senderos.", 225000, "/images/products/bicicleta-de-montana.png", sports, Arrays.asList(new Property("Rodado", "29"), new Property("Velocidades", "21")), true, 10, LocalDateTime.now().minusDays(11), true));

        // ----------------------------------------------------
        // LIBROS - NORMALES
        // ----------------------------------------------------
        this.productList.add(new Product(42L, "BOK-001", "El Hobbit", "Novela clásica de fantasía.", 9500, "/images/products/el-hobbit.png", books, null, true));
        this.productList.add(new Product(43L, "BOK-002", "1984", "Novela distópica clásica.", 8900, "/images/products/libro-1984.png", books, null, true));
        this.productList.add(new Product(44L, "BOK-004", "Fundación", "Saga clásica de ciencia ficción.", 10900, "/images/products/fundacion-libro.png", books, null, true));
        this.productList.add(new Product(45L, "BOK-008", "Harry Potter y la piedra filosofal", "Novela juvenil.", 12500, "/images/products/harry-potter-piedra-filosofal.png", books, Arrays.asList(new Property("Autor", "J. K. Rowling"), new Property("Editorial", "Salamandra")), true));
        this.productList.add(new Product(46L, "BOK-009", "Introducción a Java", "Libro educativo.", 16500, "/images/products/introduccion-a-java.png", books, Arrays.asList(new Property("Autor", "Varios"), new Property("Nivel", "Intermedio")), true));

        // ----------------------------------------------------
        // LIBROS - DESTACADOS
        // ----------------------------------------------------
        this.productList.add(new Product(47L, "BOK-003", "Dune", "Novela de ciencia ficción.", 11500, "/images/products/dune-libro.png", books, null, true, 0, LocalDateTime.now().minusDays(75), true));
        this.productList.add(new Product(48L, "BOK-006", "El Señor de los Anillos", "Novela de fantasía.", 18500, "/images/products/el-senor-de-los-anillos.png", books, Arrays.asList(new Property("Autor", "J. R. R. Tolkien"), new Property("Páginas", "1200")), true, 0, LocalDateTime.now().minusDays(7), true));
        this.productList.add(new Product(49L, "BOK-010", "Enciclopedia universal", "Colección educativa.", 45000, "/images/products/enciclopedia-universal.png", books, Arrays.asList(new Property("Volúmenes", "10"), new Property("Idioma", "Español")), true, 0, LocalDateTime.now().minusDays(4), true));

        // ----------------------------------------------------
        // LIBROS - OFERTAS
        // ----------------------------------------------------
        this.productList.add(new Product(50L, "BOK-005", "Don Quijote de la Mancha", "Obra clásica de la literatura española.", 12500, "/images/products/don-quijote-de-la-mancha.png", books, null, false, 20, LocalDateTime.now().minusDays(35), true));

        // ----------------------------------------------------
        // LIBROS - DESTACADOS + OFERTA
        // ----------------------------------------------------
        this.productList.add(new Product(51L, "BOK-007", "Clean Code", "Libro de programación.", 24500, "/images/products/clean-code.png", books, Arrays.asList(new Property("Autor", "Robert C. Martin"), new Property("Idioma", "Inglés")), true, 15, LocalDateTime.now().minusDays(5), true));

        // ----------------------------------------------------
        // MODA - NORMALES
        // ----------------------------------------------------
        this.productList.add(new Product(52L, "TSH-001", "Camiseta casual", "Prenda cómoda para uso diario.", 9500, "/images/products/camiseta-casual.png", fashion, null, true));
        this.productList.add(new Product(53L, "JCK-001", "Chaqueta ligera", "Chaqueta para clima fresco.", 22500, "/images/products/chaqueta-ligera.png", fashion, null, true));
        this.productList.add(new Product(54L, "CAP-001", "Gorra ajustable", "Gorra casual para exteriores.", 6500, "/images/products/gorra-ajustable.png", fashion, null, true));
        this.productList.add(new Product(55L, "BLT-001", "Cinturón de cuero", "Accesorio para vestir.", 8500, "/images/products/cinturon-de-cuero.png", fashion, null, true));
        this.productList.add(new Product(56L, "SOC-002", "Calcetines deportivos", "Par de calcetines cómodos.", 3500, "/images/products/calcetines-deportivos.png", fashion, null, true));
        this.productList.add(new Product(57L, "DRS-001", "Vestido casual", "Vestido para uso diario.", 28500, "/images/products/vestido-casual.png", fashion, Arrays.asList(new Property("Talla", "M"), new Property("Color", "Azul")), true));

        // ----------------------------------------------------
        // MODA - DESTACADOS
        // ----------------------------------------------------
        this.productList.add(new Product(58L, "WAT-001", "Reloj analógico", "Accesorio de vestir.", 65000, "/images/products/reloj-analogico.png", fashion, Arrays.asList(new Property("Material", "Acero inoxidable"), new Property("Resistencia al agua", "Sí")), true, 0, LocalDateTime.now().minusDays(9), true));

        // ----------------------------------------------------
        // MODA - OFERTAS
        // ----------------------------------------------------
        this.productList.add(new Product(59L, "JEA-001", "Jeans clásicos", "Pantalón de mezclilla para uso diario.", 18500, "/images/products/jeans-clasicos.png", fashion, null, false, 15, LocalDateTime.now().minusDays(30), true));
        this.productList.add(new Product(60L, "BAG-001", "Bolso de mano", "Accesorio personal.", 22500, "/images/products/bolso-de-mano.png", fashion, Arrays.asList(new Property("Material", "Cuero sintético"), new Property("Color", "Marrón")), false, 20, LocalDateTime.now().minusDays(12), true));

        // ----------------------------------------------------
        // MODA - DESTACADOS + OFERTA
        // ----------------------------------------------------
        this.productList.add(new Product(61L, "JCK-002", "Chaqueta impermeable", "Prenda para exteriores.", 32500, "/images/products/chaqueta-impermeable.png", fashion, Arrays.asList(new Property("Talla", "L"), new Property("Color", "Negro")), true, 10, LocalDateTime.now().minusDays(6), true));
        this.productList.add(new Product(62L, "SHO-001", "Zapatos de cuero", "Calzado formal.", 48500, "/images/products/zapatos-de-cuero.png", fashion, Arrays.asList(new Property("Talla", "42"), new Property("Material", "Cuero")), true, 15, LocalDateTime.now().minusDays(4), true));

        // ----------------------------------------------------
        // MODA - NORMALES
        // ----------------------------------------------------
        this.productList.add(new Product(63L, "TSH-001", "Camiseta casual", "Prenda cómoda para uso diario.", 9500, "/images/products/camiseta-casual.png", fashion, null, true));
        this.productList.add(new Product(64L, "JCK-001", "Chaqueta ligera", "Chaqueta para clima fresco.", 22500, "/images/products/chaqueta-ligera.png", fashion, null, true));
        this.productList.add(new Product(65L, "CAP-001", "Gorra ajustable", "Gorra casual para exteriores.", 6500, "/images/products/gorra-ajustable.png", fashion, null, true));
        this.productList.add(new Product(66L, "BLT-001", "Cinturón de cuero", "Accesorio para vestir.", 8500, "/images/products/cinturon-de-cuero.png", fashion, null, true));
        this.productList.add(new Product(67L, "SOC-002", "Calcetines deportivos", "Par de calcetines cómodos.", 3500, "/images/products/calcetines-deportivos.png", fashion, null, true));
        this.productList.add(new Product(68L, "DRS-001", "Vestido casual", "Vestido para uso diario.", 28500, "/images/products/vestido-casual.png", fashion, Arrays.asList(new Property("Talla", "M"), new Property("Color", "Azul")), true));

        // ----------------------------------------------------
        // MODA - DESTACADOS
        // ----------------------------------------------------
        this.productList.add(new Product(69L, "WAT-001", "Reloj analógico", "Accesorio de vestir.", 65000, "/images/products/reloj-analogico.png", fashion, Arrays.asList(new Property("Material", "Acero inoxidable"), new Property("Resistencia al agua", "Sí")), true, 0, LocalDateTime.now().minusDays(9), true));

        // ----------------------------------------------------
        // MODA - OFERTAS
        // ----------------------------------------------------
        this.productList.add(new Product(70L, "JEA-001", "Jeans clásicos", "Pantalón de mezclilla para uso diario.", 18500, "/images/products/jeans-clasicos.png", fashion, null, false, 15, LocalDateTime.now().minusDays(30), true));
        this.productList.add(new Product(71L, "BAG-001", "Bolso de mano", "Accesorio personal.", 22500, "/images/products/bolso-de-mano.png", fashion, Arrays.asList(new Property("Material", "Cuero sintético"), new Property("Color", "Marrón")), false, 20, LocalDateTime.now().minusDays(12), true));

        // ----------------------------------------------------
        // MODA - DESTACADOS + OFERTA
        // ----------------------------------------------------
        this.productList.add(new Product(72L, "JCK-002", "Chaqueta impermeable", "Prenda para exteriores.", 32500, "/images/products/chaqueta-impermeable.png", fashion, Arrays.asList(new Property("Talla", "L"), new Property("Color", "Negro")), true, 10, LocalDateTime.now().minusDays(6), true));
        this.productList.add(new Product(73L, "SHO-001", "Zapatos de cuero", "Calzado formal.", 48500, "/images/products/zapatos-de-cuero.png", fashion, Arrays.asList(new Property("Talla", "42"), new Property("Material", "Cuero")), true, 15, LocalDateTime.now().minusDays(4), true));

        // ----------------------------------------------------
        // JUGUETES - NORMALES
        // ----------------------------------------------------
        this.productList.add(new Product(74L, "DOL-001", "Muñeca", "Juguete para entretenimiento infantil.", 9500, "/images/products/muneca.png", toys, null, true));
        this.productList.add(new Product(75L, "CAR-001", "Carro de juguete", "Vehículo de juguete para niños.", 5500, "/images/products/carro-de-juguete.png", toys, null, true));
        this.productList.add(new Product(76L, "PUZ-001", "Rompecabezas", "Juego de piezas para armar.", 7500, "/images/products/rompecabezas.png", toys, null, true));
        this.productList.add(new Product(77L, "PLS-001", "Peluche", "Muñeco de tela suave.", 8500, "/images/products/peluche.png", toys, null, true));
        this.productList.add(new Product(78L, "WAT-001", "Pistola de agua", "Juguete para exteriores.", 6500, "/images/products/pistola-de-agua.png", toys, null, true));

        // ----------------------------------------------------
        // JUGUETES - DESTACADOS
        // ----------------------------------------------------
        this.productList.add(new Product(79L, "BLK-001", "Bloques de construcción", "Juego creativo para niños.", 14500, "/images/products/bloques-de-construccion.png", toys, null, true, 0, LocalDateTime.now().minusDays(55), true));
        this.productList.add(new Product(80L, "LEG-001", "Lego City", "Juego de construcción.", 55000, "/images/products/lego-city.png", toys, Arrays.asList(new Property("Piezas", "790"), new Property("Edad recomendada", "8+")), true, 0, LocalDateTime.now().minusDays(7), true));
        this.productList.add(new Product(81L, "DOL-002", "Casa de muñecas", "Juguete infantil.", 65000, "/images/products/casa-de-munecas.png", toys, Arrays.asList(new Property("Material", "Madera"), new Property("Niveles", "3")), true, 0, LocalDateTime.now().minusDays(4), true));

        // ----------------------------------------------------
        // JUGUETES - OFERTAS
        // ----------------------------------------------------
        this.productList.add(new Product(82L, "BRD-001", "Juego de mesa", "Juego recreativo familiar.", 16500, "/images/products/juego-de-mesa.png", toys, null, false, 15, LocalDateTime.now().minusDays(20), true));
        this.productList.add(new Product(83L, "RCC-001", "Carro a control remoto", "Vehículo recreativo.", 35000, "/images/products/carro-control-remoto.png", toys, Arrays.asList(new Property("Frecuencia", "2.4 GHz"), new Property("Batería", "Recargable")), false, 20, LocalDateTime.now().minusDays(12), true));
        this.productList.add(new Product(84L, "TEL-001", "Telescopio educativo", "Instrumento para observación.", 85000, "/images/products/telescopio-educativo.png", toys, Arrays.asList(new Property("Aumento", "90x"), new Property("Trípode", "Incluido")), false, 10, LocalDateTime.now().minusDays(9), true));

        // ----------------------------------------------------
        // JUGUETES - DESTACADOS + OFERTA
        // ----------------------------------------------------
        this.productList.add(new Product(85L, "DRN-001", "Drone recreativo", "Drone para principiantes.", 95000, "/images/products/drone-recreativo.png", toys, Arrays.asList(new Property("Cámara", "HD"), new Property("Autonomía", "20 min")), true, 15, LocalDateTime.now().minusDays(5), true));

        // ----------------------------------------------------
        // OTROS - NORMALES
        // ----------------------------------------------------
        this.productList.add(new Product(86L, "NOT-001", "Cuaderno universitario", "Cuaderno para apuntes.", 2500, "/images/products/cuaderno-universitario.png", others, null, true));
        this.productList.add(new Product(87L, "PEN-001", "Bolígrafo azul", "Bolígrafo de tinta azul.", 500, "/images/products/boligrafo-azul.png", others, null, true));
        this.productList.add(new Product(88L, "PEN-002", "Bolígrafo negro", "Bolígrafo de tinta negra.", 500, "/images/products/boligrafo-negro.png", others, null, true));
        this.productList.add(new Product(89L, "FLD-001", "Carpeta plástica", "Carpeta para documentos.", 1200, "/images/products/carpeta-plastica.png", others, null, true));
        this.productList.add(new Product(90L, "MRK-001", "Marcadores de colores", "Set de marcadores.", 3500, "/images/products/marcadores-de-colores.png", others, null, true));
        this.productList.add(new Product(91L, "GLU-001", "Pegamento escolar", "Pegamento para manualidades.", 1500, "/images/products/pegamento-escolar.png", others, null, true));
        this.productList.add(new Product(92L, "SCS-001", "Tijera escolar", "Tijera para oficina y escuela.", 1800, "/images/products/tijera-escolar.png", others, null, true));
        this.productList.add(new Product(93L, "ENV-001", "Sobres manila", "Sobres para documentos.", 2200, "/images/products/sobres-manila.png", others, null, true));
        this.productList.add(new Product(94L, "CLP-001", "Sujetapapeles", "Caja de sujetapapeles.", 1200, "/images/products/sujetapapeles.png", others, null, true));
        this.productList.add(new Product(95L, "RUL-001", "Regla metálica", "Regla de precisión.", 2500, "/images/products/regla-metalica.png", others, null, true));

        // ----------------------------------------------------
        // OTROS - DESTACADOS
        // ----------------------------------------------------
        this.productList.add(new Product(96L, "CAL-001", "Calculadora científica", "Calculadora para estudiantes.", 12500, "/images/products/calculadora-cientifica.png", others, null, true, 0, LocalDateTime.now().minusDays(30), true));
        this.productList.add(new Product(97L, "BPK-001", "Mochila ejecutiva", "Mochila para trabajo y estudio.", 28500, "/images/products/mochila-ejecutiva.png", others, null, true, 0, LocalDateTime.now().minusDays(14), true));

        // ----------------------------------------------------
        // OTROS - OFERTAS
        // ----------------------------------------------------
        this.productList.add(new Product(98L, "TAP-001", "Cinta adhesiva", "Cinta para oficina.", 1200, "/images/products/cinta-adhesiva.png", others, null, false, 15, LocalDateTime.now().minusDays(20), true));

        // ----------------------------------------------------
        // OTROS - DESTACADOS + OFERTA
        // ----------------------------------------------------
        this.productList.add(new Product(99L, "CAL-002", "Calculadora financiera", "Calculadora para finanzas.", 18500, "/images/products/calculadora-financiera.png", others, null, true, 10, LocalDateTime.now().minusDays(8), true));

    }

    /**
     * @param productName
     * @return Un producto mediante la búsqueda por nombre de producto
     */
    @Override
    public Optional<Product> findByProductName(String productName) {

        if (productName == null || productName.trim().isEmpty()) {
            return Optional.empty();
        }

        return productList.stream()
                .filter(product ->
                        product.getName()
                                .equalsIgnoreCase(productName.trim()))
                .findFirst();
    }

    /**
     * @param productId
     * @return Un producto mediante la búsqueda por id de producto
     */
    @Override
    public Optional<Product> findByProductId(Long productId) {

        if (productId == null || productId <= 0) {
            return Optional.empty();
        }

        return productList.stream()
                .filter(product ->
                        product.getId().equals(productId))
                .findFirst();
    }

    /**
     * @param categoryName
     * @return Lista de productos pertenecientes a una categoría
     */
    @Override
    public List<Product> findByCategory(String categoryName) {

        if (categoryName == null || categoryName.trim().isEmpty()) {
            return List.of();
        }

        return productList.stream()
                .filter(product ->
                        product.getCategory()
                                .getName()
                                .equalsIgnoreCase(categoryName.trim()))
                .toList();
    }

    /**
     * @param productName
     * @return Lista de productos cuyo nombre contiene el texto indicado
     */
    @Override
    public List<Product> findByNameContaining(String productName) {

        if (productName == null || productName.trim().isEmpty()) {
            return List.of();
        }

        return productList.stream()
                .filter(product ->
                        product.getName()
                                .toLowerCase()
                                .contains(productName.trim().toLowerCase()))
                .toList();
    }

    /**
     * @param categoryName
     * @param productName
     * @return Lista de productos pertenecientes a una categoría y cuyo
     * nombre contiene el texto indicado
     */
    @Override
    public List<Product> findByCategoryAndNameContaining(
            String categoryName,
            String productName) {

        if (categoryName == null || categoryName.trim().isEmpty()
                || productName == null || productName.trim().isEmpty()) {
            return List.of();
        }

        return productList.stream()
                .filter(product ->
                        product.getCategory()
                                .getName()
                                .equalsIgnoreCase(categoryName.trim()))
                .filter(product ->
                        product.getName()
                                .toLowerCase()
                                .contains(productName.trim().toLowerCase()))
                .toList();
    }

    /**
     * @return Lista con todos los productos de la aplicación
     */
    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    /**
     * @param product
     * Agrega un producto a memoria para uso de la aplicación
     */
    @Override
    public void addProduct(Product product) {
        productList.add(product);
    }

    /**
     * Actualiza la información editable de un producto.
     * @param product producto actualizado.
     */
    @Override
    public void updateProduct(Product product) {

        Optional<Product> currentProduct =
                findByProductId(
                        product.getId()
                );

        if(currentProduct.isEmpty()) {
            return;
        }

        currentProduct.get().setSku(
                product.getSku()
        );

        currentProduct.get().setName(
                product.getName()
        );

        currentProduct.get().setDescription(
                product.getDescription()
        );

        currentProduct.get().setPrice(
                product.getPrice()
        );

        currentProduct.get().setImagePath(
                product.getImagePath()
        );

        currentProduct.get().setCategory(
                product.getCategory()
        );

        currentProduct.get().setProperties(
                product.getProperties()
        );

        currentProduct.get().setFeatured(
                product.isFeatured()
        );

        currentProduct.get().setDiscountPercentage(
                product.getDiscountPercentage()
        );

        currentProduct.get().setActive(
                product.isActive()
        );

    }

    /**
     * Cambia el estado activo/inactivo de un producto.
     * @param productId identificador del producto.
     * @return true si fue posible modificar el estado.
     */
    @Override
    public boolean switchProductStatus(Long productId) {

        Optional<Product> product =
                findByProductId(productId);

        if(product.isEmpty()) {
            return false;
        }

        product.get().setActive(
                !product.get().isActive()
        );

        return true;

    }

    /**
     * Busca productos según criterio indicado.
     * @param searchType tipo de búsqueda.
     * @param searchValue valor a buscar.
     * @return Lista de productos encontrados.
     */
    @Override
    public List<Product> searchProducts(
            String searchType,
            String searchValue) {

        if(searchType == null) {
            return getAllProducts();
        }

        String value =
                searchValue == null
                        ? ""
                        : searchValue.trim().toLowerCase();

        switch(searchType) {

            case "ID":

                return productList.stream()
                        .filter(product ->
                                String.valueOf(product.getId())
                                        .contains(value))
                        .toList();

            case "SKU":

                return productList.stream()
                        .filter(product ->
                                product.getSku()
                                        .toLowerCase()
                                        .contains(value))
                        .toList();

            case "NAME":

                return productList.stream()
                        .filter(product ->
                                product.getName()
                                        .toLowerCase()
                                        .contains(value))
                        .toList();

            case "CATEGORY":

                return productList.stream()
                        .filter(product ->
                                product.getCategory()
                                        .getName()
                                        .toLowerCase()
                                        .contains(value))
                        .toList();

            case "FEATURED":

                return productList.stream()
                        .filter(Product::isFeatured)
                        .toList();

            case "ON_SALE":

                return productList.stream()
                        .filter(product ->
                                product.getDiscountPercentage() > 0)
                        .toList();

            default:

                return getAllProducts();

        }

    }

    /**
     * Busca un producto mediante su SKU.
     * @param sku código SKU del producto.
     * @return Producto encontrado o Optional vacío.
     */
    @Override
    public Optional<Product> findBySku(String sku) {

        if(sku == null ||
                sku.trim().isEmpty()) {

            return Optional.empty();

        }

        return productList.stream()
                .filter(product ->
                        product.getSku()
                                .equalsIgnoreCase(
                                        sku.trim()
                                ))
                .findFirst();

    }

}
