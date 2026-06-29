# Inventario de productos para catálogo de imágenes - NextShop

## Alcance de auditoría

Fuentes revisadas: `ProductRepository`, `ProductService`, `ProductController`/controladores de producto, `Product.java`, `CategoryRepository`, recursos SQL y recursos JSON.

No se encontraron archivos `data.sql`, `schema.sql` ni JSON de productos en `src`. Los productos realmente precargados por el sistema están en `ProductRepository.init()`.

El modelo `Product` no define un campo de marca. Todos los productos precargados tienen estado activo.

## Inventario

| ID | Producto | Categoría | imagePath actual | Imagen requerida |
| -- | -------- | --------- | ---------------- | ---------------- |
| 1 | Memoria USB 64 GB | Tecnología | /images/products/productIcon.png | Pendiente |
| 2 | Webcam HD | Tecnología | /images/products/productIcon.png | Pendiente |
| 3 | Cargador USB | Tecnología | /images/products/productIcon.png | Pendiente |
| 4 | Base para monitor | Tecnología | /images/products/productIcon.png | Pendiente |
| 5 | Impresora multifuncional | Tecnología | /images/products/productIcon.png | Pendiente |
| 6 | Router WiFi 6 | Tecnología | /images/products/productIcon.png | Pendiente |
| 7 | Mouse inalámbrico | Tecnología | /images/products/productIcon.png | Pendiente |
| 8 | Parlante Bluetooth | Tecnología | /images/products/productIcon.png | Pendiente |
| 9 | Monitor LG 27 pulgadas | Tecnología | /images/products/productIcon.png | Pendiente |
| 10 | Disco SSD Samsung | Tecnología | /images/products/productIcon.png | Pendiente |
| 11 | Tablet Samsung Galaxy | Tecnología | /images/products/productIcon.png | Pendiente |
| 12 | Smartwatch deportivo | Tecnología | /images/products/productIcon.png | Pendiente |
| 13 | Teclado mecánico | Tecnología | /images/products/productIcon.png | Pendiente |
| 14 | Audífonos inalámbricos | Tecnología | /images/products/productIcon.png | Pendiente |
| 15 | Laptop Lenovo IdeaPad 5 | Tecnología | /images/products/productIcon.png | Pendiente |
| 16 | Tarjeta gráfica RTX 4060 | Tecnología | /images/products/productIcon.png | Pendiente |
| 17 | Licuadora | Hogar | /images/products/productIcon.png | Pendiente |
| 18 | Sartén antiadherente | Hogar | /images/products/productIcon.png | Pendiente |
| 19 | Juego de cuchillos | Hogar | /images/products/productIcon.png | Pendiente |
| 20 | Organizador de escritorio | Hogar | /images/products/productIcon.png | Pendiente |
| 21 | Canasta de ropa | Hogar | /images/products/productIcon.png | Pendiente |
| 22 | Microondas | Hogar | /images/products/productIcon.png | Pendiente |
| 23 | Juego de comedor | Hogar | /images/products/productIcon.png | Pendiente |
| 24 | Colchón ortopédico | Hogar | /images/products/productIcon.png | Pendiente |
| 25 | Lámpara de pie | Hogar | /images/products/productIcon.png | Pendiente |
| 26 | Escritorio ejecutivo | Hogar | /images/products/productIcon.png | Pendiente |
| 27 | Refrigeradora | Hogar | /images/products/productIcon.png | Pendiente |
| 28 | Cafetera | Hogar | /images/products/productIcon.png | Pendiente |
| 29 | Aspiradora vertical | Hogar | /images/products/productIcon.png | Pendiente |
| 30 | Silla ergonómica | Hogar | /images/products/productIcon.png | Pendiente |
| 31 | Balón de baloncesto | Deportes | /images/products/productIcon.png | Pendiente |
| 32 | Cuerda para saltar | Deportes | /images/products/productIcon.png | Pendiente |
| 33 | Botella deportiva | Deportes | /images/products/productIcon.png | Pendiente |
| 34 | Mancuernas ajustables | Deportes | /images/products/productIcon.png | Pendiente |
| 35 | Raqueta de tenis | Deportes | /images/products/productIcon.png | Pendiente |
| 36 | Banco para pesas | Deportes | /images/products/productIcon.png | Pendiente |
| 37 | Balón de fútbol | Deportes | /images/products/productIcon.png | Pendiente |
| 38 | Caminadora eléctrica | Deportes | /images/products/productIcon.png | Pendiente |
| 39 | Mat para yoga | Deportes | /images/products/productIcon.png | Pendiente |
| 40 | Raqueta de tenis | Deportes | /images/products/productIcon.png | Pendiente |
| 41 | Bicicleta de montaña | Deportes | /images/products/productIcon.png | Pendiente |
| 42 | El Hobbit | Libros | /images/products/productIcon.png | Pendiente |
| 43 | 1984 | Libros | /images/products/productIcon.png | Pendiente |
| 44 | Fundación | Libros | /images/products/productIcon.png | Pendiente |
| 45 | Harry Potter y la piedra filosofal | Libros | /images/products/productIcon.png | Pendiente |
| 46 | Introducción a Java | Libros | /images/products/productIcon.png | Pendiente |
| 47 | Dune | Libros | /images/products/productIcon.png | Pendiente |
| 48 | El Señor de los Anillos | Libros | /images/products/productIcon.png | Pendiente |
| 49 | Enciclopedia universal | Libros | /images/products/productIcon.png | Pendiente |
| 50 | Don Quijote de la Mancha | Libros | /images/products/productIcon.png | Pendiente |
| 51 | Clean Code | Libros | /images/products/productIcon.png | Pendiente |
| 52 | Camiseta casual | Moda | /images/products/productIcon.png | Pendiente |
| 53 | Chaqueta ligera | Moda | /images/products/productIcon.png | Pendiente |
| 54 | Gorra ajustable | Moda | /images/products/productIcon.png | Pendiente |
| 55 | Cinturón de cuero | Moda | /images/products/productIcon.png | Pendiente |
| 56 | Calcetines deportivos | Moda | /images/products/productIcon.png | Pendiente |
| 57 | Vestido casual | Moda | /images/products/productIcon.png | Pendiente |
| 58 | Reloj analógico | Moda | /images/products/productIcon.png | Pendiente |
| 59 | Jeans clásicos | Moda | /images/products/productIcon.png | Pendiente |
| 60 | Bolso de mano | Moda | /images/products/productIcon.png | Pendiente |
| 61 | Chaqueta impermeable | Moda | /images/products/productIcon.png | Pendiente |
| 62 | Zapatos de cuero | Moda | /images/products/productIcon.png | Pendiente |
| 63 | Camiseta casual | Moda | /images/products/productIcon.png | Pendiente |
| 64 | Chaqueta ligera | Moda | /images/products/productIcon.png | Pendiente |
| 65 | Gorra ajustable | Moda | /images/products/productIcon.png | Pendiente |
| 66 | Cinturón de cuero | Moda | /images/products/productIcon.png | Pendiente |
| 67 | Calcetines deportivos | Moda | /images/products/productIcon.png | Pendiente |
| 68 | Vestido casual | Moda | /images/products/productIcon.png | Pendiente |
| 69 | Reloj analógico | Moda | /images/products/productIcon.png | Pendiente |
| 70 | Jeans clásicos | Moda | /images/products/productIcon.png | Pendiente |
| 71 | Bolso de mano | Moda | /images/products/productIcon.png | Pendiente |
| 72 | Chaqueta impermeable | Moda | /images/products/productIcon.png | Pendiente |
| 73 | Zapatos de cuero | Moda | /images/products/productIcon.png | Pendiente |
| 74 | Muñeca | Juguetes | /images/products/productIcon.png | Pendiente |
| 75 | Carro de juguete | Juguetes | /images/products/productIcon.png | Pendiente |
| 76 | Rompecabezas | Juguetes | /images/products/productIcon.png | Pendiente |
| 77 | Peluche | Juguetes | /images/products/productIcon.png | Pendiente |
| 78 | Pistola de agua | Juguetes | /images/products/productIcon.png | Pendiente |
| 79 | Bloques de construcción | Juguetes | /images/products/productIcon.png | Pendiente |
| 80 | Lego City | Juguetes | /images/products/productIcon.png | Pendiente |
| 81 | Casa de muñecas | Juguetes | /images/products/productIcon.png | Pendiente |
| 82 | Juego de mesa | Juguetes | /images/products/productIcon.png | Pendiente |
| 83 | Carro a control remoto | Juguetes | /images/products/productIcon.png | Pendiente |
| 84 | Telescopio educativo | Juguetes | /images/products/productIcon.png | Pendiente |
| 85 | Drone recreativo | Juguetes | /images/products/productIcon.png | Pendiente |
| 86 | Cuaderno universitario | Otros | /images/products/productIcon.png | Pendiente |
| 87 | Bolígrafo azul | Otros | /images/products/productIcon.png | Pendiente |
| 88 | Bolígrafo negro | Otros | /images/products/productIcon.png | Pendiente |
| 89 | Carpeta plástica | Otros | /images/products/productIcon.png | Pendiente |
| 90 | Marcadores de colores | Otros | /images/products/productIcon.png | Pendiente |
| 91 | Pegamento escolar | Otros | /images/products/productIcon.png | Pendiente |
| 92 | Tijera escolar | Otros | /images/products/productIcon.png | Pendiente |
| 93 | Sobres manila | Otros | /images/products/productIcon.png | Pendiente |
| 94 | Sujetapapeles | Otros | /images/products/productIcon.png | Pendiente |
| 95 | Regla metálica | Otros | /images/products/productIcon.png | Pendiente |
| 96 | Calculadora científica | Otros | /images/products/productIcon.png | Pendiente |
| 97 | Mochila ejecutiva | Otros | /images/products/productIcon.png | Pendiente |
| 98 | Cinta adhesiva | Otros | /images/products/productIcon.png | Pendiente |
| 99 | Calculadora financiera | Otros | /images/products/productIcon.png | Pendiente |

## Auditoría

Total registros: 99

Productos únicos: 87

Duplicados: 12 registros duplicados por nombre de producto. Productos afectados: Raqueta de tenis; Camiseta casual; Chaqueta ligera; Gorra ajustable; Cinturón de cuero; Calcetines deportivos; Vestido casual; Reloj analógico; Jeans clásicos; Bolso de mano; Chaqueta impermeable; Zapatos de cuero.

Categorías: Tecnología, Hogar, Deportes, Libros, Moda, Juguetes, Otros. La categoría Belleza existe en `CategoryRepository`, pero no tiene productos asignados.

Productos sin categoría: 0

Productos con imagePath genérico: 99

Productos con imagePath personalizado: 0

Productos que necesitan imagen nueva: 99

Productos que ya poseen imagen propia: 0
