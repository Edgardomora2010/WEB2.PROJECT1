# Catálogo oficial de imágenes de productos — NextShop

Este documento constituye el **catálogo oficial de imágenes del proyecto NextShop**.  
Es la referencia única y definitiva para la asignación de imágenes a productos.

## Propósito

- Cada producto tendrá una única imagen oficial asociada.
- El nombre de archivo definido en la columna **Imagen definitiva** será el valor que se utilice en el campo `imagePath` de cada producto.
- El mismo nombre será utilizado durante la futura migración a **Spring Data JPA** para poblar la columna `image_path` en la base de datos MySQL.

---

## Convenciones de nombres de imagen

Todas las imágenes del catálogo deben cumplir las siguientes reglas:

| Regla | Detalle |
|-------|---------|
| Formato | PNG únicamente |
| Capitalización | Todo en **minúsculas** |
| Separador | **Guiones** (`-`), sin espacios ni guiones bajos |
| Ubicación | `src/main/resources/static/images/products/` |
| Ruta en `imagePath` | `/images/products/nombre-archivo.png` |

**Ejemplos válidos:**
```
webcam-hd.png
router-wifi-6.png
laptop-lenovo-ideapad-5.png
mouse-inalambrico.png
```

---

## Catálogo de productos

### Tecnología

| ID | SKU | Producto | Categoría | imagePath actual | Imagen definitiva | Estado |
|----|-----|----------|-----------|-----------------|-------------------|--------|
| 1 | USB-001 | Memoria USB 64 GB | Tecnología | /images/products/productIcon.png | | Pendiente |
| 2 | CAM-001 | Webcam HD | Tecnología | /images/products/productIcon.png | | Pendiente |
| 3 | CHR-001 | Cargador USB | Tecnología | /images/products/productIcon.png | | Pendiente |
| 4 | STD-001 | Base para monitor | Tecnología | /images/products/productIcon.png | | Pendiente |
| 5 | PRN-001 | Impresora multifuncional | Tecnología | /images/products/productIcon.png | | Pendiente |
| 6 | RTR-001 | Router WiFi 6 | Tecnología | /images/products/productIcon.png | | Pendiente |
| 7 | MOU-001 | Mouse inalámbrico | Tecnología | /images/products/productIcon.png | | Pendiente |
| 8 | SPK-001 | Parlante Bluetooth | Tecnología | /images/products/productIcon.png | | Pendiente |
| 9 | MON-001 | Monitor LG 27 pulgadas | Tecnología | /images/products/productIcon.png | | Pendiente |
| 10 | SSD-001 | Disco SSD Samsung | Tecnología | /images/products/productIcon.png | | Pendiente |
| 11 | TAB-001 | Tablet Samsung Galaxy | Tecnología | /images/products/productIcon.png | | Pendiente |
| 12 | SMW-001 | Smartwatch deportivo | Tecnología | /images/products/productIcon.png | | Pendiente |
| 13 | KEY-001 | Teclado mecánico | Tecnología | /images/products/productIcon.png | | Pendiente |
| 14 | AUD-001 | Audífonos inalámbricos | Tecnología | /images/products/productIcon.png | | Pendiente |
| 15 | LAP-001 | Laptop Lenovo IdeaPad 5 | Tecnología | /images/products/productIcon.png | | Pendiente |
| 16 | GPU-001 | Tarjeta gráfica RTX 4060 | Tecnología | /images/products/productIcon.png | | Pendiente |

### Hogar

| ID | SKU | Producto | Categoría | imagePath actual | Imagen definitiva | Estado |
|----|-----|----------|-----------|-----------------|-------------------|--------|
| 17 | LIC-001 | Licuadora | Hogar | /images/products/productIcon.png | | Pendiente |
| 18 | PAN-001 | Sartén antiadherente | Hogar | /images/products/productIcon.png | | Pendiente |
| 19 | CUT-001 | Juego de cuchillos | Hogar | /images/products/productIcon.png | | Pendiente |
| 20 | ORG-001 | Organizador de escritorio | Hogar | /images/products/productIcon.png | | Pendiente |
| 21 | BSK-001 | Canasta de ropa | Hogar | /images/products/productIcon.png | | Pendiente |
| 22 | MIC-001 | Microondas | Hogar | /images/products/productIcon.png | | Pendiente |
| 23 | DIN-001 | Juego de comedor | Hogar | /images/products/productIcon.png | | Pendiente |
| 24 | MAT-001 | Colchón ortopédico | Hogar | /images/products/productIcon.png | | Pendiente |
| 25 | LMP-001 | Lámpara de pie | Hogar | /images/products/productIcon.png | | Pendiente |
| 26 | DSK-001 | Escritorio ejecutivo | Hogar | /images/products/productIcon.png | | Pendiente |
| 27 | REF-001 | Refrigeradora | Hogar | /images/products/productIcon.png | | Pendiente |
| 28 | CAF-001 | Cafetera | Hogar | /images/products/productIcon.png | | Pendiente |
| 29 | VAC-001 | Aspiradora vertical | Hogar | /images/products/productIcon.png | | Pendiente |
| 30 | CHR-002 | Silla ergonómica | Hogar | /images/products/productIcon.png | | Pendiente |

### Deportes

| ID | SKU | Producto | Categoría | imagePath actual | Imagen definitiva | Estado |
|----|-----|----------|-----------|-----------------|-------------------|--------|
| 31 | BKB-001 | Balón de baloncesto | Deportes | /images/products/productIcon.png | | Pendiente |
| 32 | JRP-001 | Cuerda para saltar | Deportes | /images/products/productIcon.png | | Pendiente |
| 33 | BOT-001 | Botella deportiva | Deportes | /images/products/productIcon.png | | Pendiente |
| 34 | DMB-001 | Mancuernas ajustables | Deportes | /images/products/productIcon.png | | Pendiente |
| 35 | TEN-001 | Raqueta de tenis | Deportes | /images/products/productIcon.png | | Pendiente |
| 36 | BEN-001 | Banco para pesas | Deportes | /images/products/productIcon.png | | Pendiente |
| 37 | SOC-001 | Balón de fútbol | Deportes | /images/products/productIcon.png | | Pendiente |
| 38 | TRD-001 | Caminadora eléctrica | Deportes | /images/products/productIcon.png | | Pendiente |
| 39 | YOG-001 | Mat para yoga | Deportes | /images/products/productIcon.png | | Pendiente |
| 40 | TEN-001 | Raqueta de tenis ⚠️ duplicado de ID 35 | Deportes | /images/products/productIcon.png | | Pendiente |
| 41 | BIK-001 | Bicicleta de montaña | Deportes | /images/products/productIcon.png | | Pendiente |

### Libros

| ID | SKU | Producto | Categoría | imagePath actual | Imagen definitiva | Estado |
|----|-----|----------|-----------|-----------------|-------------------|--------|
| 42 | BOK-001 | El Hobbit | Libros | /images/products/productIcon.png | | Pendiente |
| 43 | BOK-002 | 1984 | Libros | /images/products/productIcon.png | | Pendiente |
| 44 | BOK-004 | Fundación | Libros | /images/products/productIcon.png | | Pendiente |
| 45 | BOK-008 | Harry Potter y la piedra filosofal | Libros | /images/products/productIcon.png | | Pendiente |
| 46 | BOK-009 | Introducción a Java | Libros | /images/products/productIcon.png | | Pendiente |
| 47 | BOK-003 | Dune | Libros | /images/products/productIcon.png | | Pendiente |
| 48 | BOK-006 | El Señor de los Anillos | Libros | /images/products/productIcon.png | | Pendiente |
| 49 | BOK-010 | Enciclopedia universal | Libros | /images/products/productIcon.png | | Pendiente |
| 50 | BOK-005 | Don Quijote de la Mancha | Libros | /images/products/productIcon.png | | Pendiente |
| 51 | BOK-007 | Clean Code | Libros | /images/products/productIcon.png | | Pendiente |

### Moda

> ⚠️ **Alerta de duplicados:** Los IDs 63–73 son copias exactas de los IDs 52–62.
> El bloque completo de Moda fue añadido dos veces en `ProductRepository.init()`.
> Todos comparten la misma imagen ya que son el mismo producto.

| ID | SKU | Producto | Categoría | imagePath actual | Imagen definitiva | Estado |
|----|-----|----------|-----------|-----------------|-------------------|--------|
| 52 | TSH-001 | Camiseta casual | Moda | /images/products/productIcon.png | | Pendiente |
| 53 | JCK-001 | Chaqueta ligera | Moda | /images/products/productIcon.png | | Pendiente |
| 54 | CAP-001 | Gorra ajustable | Moda | /images/products/productIcon.png | | Pendiente |
| 55 | BLT-001 | Cinturón de cuero | Moda | /images/products/productIcon.png | | Pendiente |
| 56 | SOC-002 | Calcetines deportivos | Moda | /images/products/productIcon.png | | Pendiente |
| 57 | DRS-001 | Vestido casual | Moda | /images/products/productIcon.png | | Pendiente |
| 58 | WAT-001 | Reloj analógico | Moda | /images/products/productIcon.png | | Pendiente |
| 59 | JEA-001 | Jeans clásicos | Moda | /images/products/productIcon.png | | Pendiente |
| 60 | BAG-001 | Bolso de mano | Moda | /images/products/productIcon.png | | Pendiente |
| 61 | JCK-002 | Chaqueta impermeable | Moda | /images/products/productIcon.png | | Pendiente |
| 62 | SHO-001 | Zapatos de cuero | Moda | /images/products/productIcon.png | | Pendiente |
| 63 | TSH-001 | Camiseta casual ⚠️ duplicado de ID 52 | Moda | /images/products/productIcon.png | | Pendiente |
| 64 | JCK-001 | Chaqueta ligera ⚠️ duplicado de ID 53 | Moda | /images/products/productIcon.png | | Pendiente |
| 65 | CAP-001 | Gorra ajustable ⚠️ duplicado de ID 54 | Moda | /images/products/productIcon.png | | Pendiente |
| 66 | BLT-001 | Cinturón de cuero ⚠️ duplicado de ID 55 | Moda | /images/products/productIcon.png | | Pendiente |
| 67 | SOC-002 | Calcetines deportivos ⚠️ duplicado de ID 56 | Moda | /images/products/productIcon.png | | Pendiente |
| 68 | DRS-001 | Vestido casual ⚠️ duplicado de ID 57 | Moda | /images/products/productIcon.png | | Pendiente |
| 69 | WAT-001 | Reloj analógico ⚠️ duplicado de ID 58 | Moda | /images/products/productIcon.png | | Pendiente |
| 70 | JEA-001 | Jeans clásicos ⚠️ duplicado de ID 59 | Moda | /images/products/productIcon.png | | Pendiente |
| 71 | BAG-001 | Bolso de mano ⚠️ duplicado de ID 60 | Moda | /images/products/productIcon.png | | Pendiente |
| 72 | JCK-002 | Chaqueta impermeable ⚠️ duplicado de ID 61 | Moda | /images/products/productIcon.png | | Pendiente |
| 73 | SHO-001 | Zapatos de cuero ⚠️ duplicado de ID 62 | Moda | /images/products/productIcon.png | | Pendiente |

### Juguetes

> ⚠️ **Alerta de SKU:** El SKU `WAT-001` está asignado tanto a "Pistola de agua" (ID 78, Juguetes)
> como a "Reloj analógico" (IDs 58/69, Moda). Son productos distintos. Debe corregirse el SKU en el futuro.

| ID | SKU | Producto | Categoría | imagePath actual | Imagen definitiva | Estado |
|----|-----|----------|-----------|-----------------|-------------------|--------|
| 74 | DOL-001 | Muñeca | Juguetes | /images/products/productIcon.png | | Pendiente |
| 75 | CAR-001 | Carro de juguete | Juguetes | /images/products/productIcon.png | | Pendiente |
| 76 | PUZ-001 | Rompecabezas | Juguetes | /images/products/productIcon.png | | Pendiente |
| 77 | PLS-001 | Peluche | Juguetes | /images/products/productIcon.png | | Pendiente |
| 78 | WAT-001 | Pistola de agua | Juguetes | /images/products/productIcon.png | | Pendiente |
| 79 | BLK-001 | Bloques de construcción | Juguetes | /images/products/productIcon.png | | Pendiente |
| 80 | LEG-001 | Lego City | Juguetes | /images/products/productIcon.png | | Pendiente |
| 81 | DOL-002 | Casa de muñecas | Juguetes | /images/products/productIcon.png | | Pendiente |
| 82 | BRD-001 | Juego de mesa | Juguetes | /images/products/productIcon.png | | Pendiente |
| 83 | RCC-001 | Carro a control remoto | Juguetes | /images/products/productIcon.png | | Pendiente |
| 84 | TEL-001 | Telescopio educativo | Juguetes | /images/products/productIcon.png | | Pendiente |
| 85 | DRN-001 | Drone recreativo | Juguetes | /images/products/productIcon.png | | Pendiente |

### Otros

| ID | SKU | Producto | Categoría | imagePath actual | Imagen definitiva | Estado |
|----|-----|----------|-----------|-----------------|-------------------|--------|
| 86 | NOT-001 | Cuaderno universitario | Otros | /images/products/productIcon.png | | Pendiente |
| 87 | PEN-001 | Bolígrafo azul | Otros | /images/products/productIcon.png | | Pendiente |
| 88 | PEN-002 | Bolígrafo negro | Otros | /images/products/productIcon.png | | Pendiente |
| 89 | FLD-001 | Carpeta plástica | Otros | /images/products/productIcon.png | | Pendiente |
| 90 | MRK-001 | Marcadores de colores | Otros | /images/products/productIcon.png | | Pendiente |
| 91 | GLU-001 | Pegamento escolar | Otros | /images/products/productIcon.png | | Pendiente |
| 92 | SCS-001 | Tijera escolar | Otros | /images/products/productIcon.png | | Pendiente |
| 93 | ENV-001 | Sobres manila | Otros | /images/products/productIcon.png | | Pendiente |
| 94 | CLP-001 | Sujetapapeles | Otros | /images/products/productIcon.png | | Pendiente |
| 95 | RUL-001 | Regla metálica | Otros | /images/products/productIcon.png | | Pendiente |
| 96 | CAL-001 | Calculadora científica | Otros | /images/products/productIcon.png | | Pendiente |
| 97 | BPK-001 | Mochila ejecutiva | Otros | /images/products/productIcon.png | | Pendiente |
| 98 | TAP-001 | Cinta adhesiva | Otros | /images/products/productIcon.png | | Pendiente |
| 99 | CAL-002 | Calculadora financiera | Otros | /images/products/productIcon.png | | Pendiente |

### Belleza

> ℹ️ La categoría **Belleza** está registrada en `CategoryRepository` pero no tiene productos asignados en esta versión.
> No requiere imágenes en la fase actual.

---

## Resumen del catálogo

| Métrica | Valor |
|---------|-------|
| Productos registrados (total de registros) | 99 |
| Productos únicos (por nombre) | 87 |
| Duplicados exactos (`CONSERVAR_UNO`) | 11 pares — Moda (IDs 63–73 duplican IDs 52–62) |
| Duplicados con diferencia (`REVISAR_MANUALMENTE`) | 1 par — Raqueta de tenis (IDs 35 y 40, distinto descuento) |
| Colisión de SKU (no duplicado de nombre) | 1 — SKU `WAT-001` en Moda y Juguetes |
| Categorías sin productos | 1 — Belleza |
| Imágenes pendientes de asignar | 99 |
| Imágenes únicas necesarias | 87 |

---

## Notas para pasos futuros

1. **Eliminar duplicados de Moda:** Los IDs 63–73 deben removerse de `ProductRepository.init()` antes de la migración a JPA.
2. **Resolver Raqueta de tenis:** Decidir si IDs 35 y 40 son el mismo producto con descuento temporal o dos registros legítimos.
3. **Corregir SKU `WAT-001`:** Asignar un SKU único a "Pistola de agua" (ID 78, Juguetes).
4. **Agregar productos de Belleza:** La categoría existe pero está vacía.
5. **Columna Imagen definitiva:** Completar este catálogo con los nombres de archivo PNG definitivos en el paso de asignación de imágenes.
