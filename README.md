# WEB2.PROJECT1

Programacion Web 2 (Framework: Java Spring Boot) - Proyecto No. 01

## Levantar NextShop con MySQL local

El proyecto incluye persistencia con Spring Data JPA y MySQL por Docker. No es necesario copiar una base de datos manualmente: Hibernate crea o actualiza las tablas y `DataInitializer` carga los datos iniciales cuando la base esta vacia.

Pasos:

```bash
git pull
docker compose up -d
./mvnw spring-boot:run
```

En Windows tambien se puede usar:

```bash
mvnw.cmd spring-boot:run
```

Configuracion local:

| Elemento | Valor |
| --- | --- |
| Servicio Docker | `mysql` |
| Contenedor | `nextshop-mysql` |
| Base de datos | `nextshopdb` |
| Puerto local | `3307` |
| Usuario | `nextshop` |
| Contrasena | `nextshop123` |
| URL JDBC | `jdbc:mysql://localhost:3307/nextshopdb` |

La aplicacion usa los valores definidos en `src/main/resources/application.properties`. Las imagenes de productos se sirven desde `src/main/resources/static/images/products/`.
