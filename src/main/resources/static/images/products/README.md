# Imágenes de productos — NextShop

Esta carpeta almacena las imágenes individuales de los productos de la tienda.

## Convenciones de uso

- Las rutas usadas en `imagePath` deben tener el formato `/images/products/nombre-archivo.png`.
- Los nombres de archivo deben ir en **minúscula**, sin espacios, usando **guiones** como separador.
  - Ejemplo correcto: `laptop-lenovo-ideapad-5.png`
  - Ejemplo incorrecto: `Laptop Lenovo IdeaPad 5.png`
- El archivo `productIcon.png` se usa como imagen por defecto cuando un producto no tiene imagen asignada.

## Referencia en código

El valor de `imagePath` en `ProductRepository` debe apuntar a esta carpeta:

```
/images/products/nombre-archivo.png
```

Spring Boot sirve automáticamente todos los archivos ubicados bajo
`src/main/resources/static/` como recursos estáticos accesibles desde la raíz del contexto.
