# Evidencias del modulo de imagenes de productos

| Archivo | Tipo | Descripcion | Seccion sugerida del informe | Estado |
| --- | --- | --- | --- | --- |
| 01-home-productos-imagenes.png | Funcional | Home con productos e imagenes | Resultados | Disponible |
| 02-detalle-producto-imagen.png | Funcional | Detalle de producto con imagen | Resultados | Disponible |
| 03-ruta-estatica-imagen.png | Tecnica | Imagen servida desde static | Arquitectura | Disponible |
| 04-carrito-imagen-producto.png | Funcional | Carrito con imagen de producto | Resultados | Disponible |
| 05-admin-productos-imagenes.png | Funcional | Panel admin con imagenes | Resultados | No disponible: el acceso requiere flujo de autenticacion y el HTML capturado del panel no expone imagenes de productos sin modificar vistas. |
| 06-productrepository-imagepath.png | Tecnica | ProductRepository usando imagePath | Implementacion | Disponible |
| 07-carpeta-static-images-products.png | Tecnica | Carpeta de imagenes estaticas | Arquitectura | Disponible |
| 08-build-success.png | Validacion | Compilacion exitosa | Validaciones | Disponible |
| 09-git-status.png | Validacion | Estado del repositorio despues de generar evidencias | Control de versiones | Disponible |
| 10-git-log.png | Validacion | Commits recientes | Control de versiones | Disponible |

Notas:

- La captura del carrito fue generada a partir de una sesion de cliente en memoria y un HTML temporal renderizado desde la respuesta real de `/cart`.
- La captura de admin no se fuerza para evitar modificar seguridad, controladores, templates o logica del proyecto.
- Al generar estas evidencias, `git status` muestra archivos nuevos bajo `docs/evidencias/`, como es esperado en esta fase.
