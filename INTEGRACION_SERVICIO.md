# Integración con moviecards-service

Este documento describe los cambios realizados para integrar la aplicación con el servicio externo `moviecards-service`.

## Cambios Realizados

### Sprint 3 - Integración con moviecards-service

#### Issue 3.1: Modificación del código en src/main

**Cambios implementados:**

1. **Configuración del servicio** (`src/main/java/com/lauracercas/moviecards/config/MovieCardsServiceConfig.java`)
   - Clase de configuración para el cliente REST
   - Bean de RestTemplate para realizar llamadas HTTP
   - Propiedades configurables para URL y timeout del servicio

2. **Cliente REST** (`src/main/java/com/lauracercas/moviecards/client/MovieCardsServiceClient.java`)
   - Cliente para comunicarse con el servicio externo
   - Métodos para operaciones CRUD de Movies y Actors
   - Método para registrar actores en películas

3. **Modificación de servicios:**
   - `MovieServiceImpl`: Ahora usa `MovieCardsServiceClient` en lugar de `MovieJPA`
   - `ActorServiceImpl`: Ahora usa `MovieCardsServiceClient` en lugar de `ActorJPA`
   - `CardServiceImpl`: Actualizado para usar el cliente REST para registrar actores en películas

4. **Configuración de propiedades** (`src/main/resources/application.properties`)
   - Eliminadas configuraciones de JPA y base de datos H2
   - Agregadas propiedades para configurar el servicio externo:
     - `moviecards.service.url`: URL base del servicio
     - `moviecards.service.timeout`: Timeout para las peticiones (opcional, por defecto 5000ms)

#### Issue 3.2: Modificación del código en src/test

**Cambios implementados:**

1. **Pruebas unitarias actualizadas:**
   - `MovieServiceImplTest`: Mockea `MovieCardsServiceClient` en lugar de `MovieJPA`
   - `ActorServiceImplTest`: Mockea `MovieCardsServiceClient` en lugar de `ActorJPA`
   - `CardServiceImplTest`: Agregado mock de `MovieCardsServiceClient` y prueba adicional para manejo de errores

2. **Pruebas de integración:**
   - `MovieJPAIT` y `ActorJPAIT`: Deshabilitadas ya que ya no se usan repositorios JPA locales
   - Nota: Estas pruebas deberían ser reemplazadas por pruebas de integración del cliente REST usando MockRestServiceServer o WireMock

## Configuración

### Variables de entorno

Para configurar la URL del servicio en producción (Azure), actualizar las variables de entorno o el archivo `application-prod.properties`:

```properties
moviecards.service.url=https://moviecards-service.azurewebsites.net/api
moviecards.service.timeout=10000
```

### Endpoints esperados del servicio moviecards-service

El cliente REST espera los siguientes endpoints:

- `GET /api/movies` - Obtener todas las películas
- `GET /api/movies/{id}` - Obtener una película por ID
- `POST /api/movies` - Crear una nueva película
- `PUT /api/movies/{id}` - Actualizar una película
- `GET /api/actors` - Obtener todos los actores
- `GET /api/actors/{id}` - Obtener un actor por ID
- `POST /api/actors` - Crear un nuevo actor
- `POST /api/movies/{movieId}/actors/{actorId}` - Registrar un actor en una película

## Crear Milestone e Issues en GitHub

Para crear el milestone Sprint 3 y los issues 3.1 y 3.2 en GitHub, ejecutar el script:

```powershell
.\create-milestone-issues.ps1
```

**Requisitos:**
- GitHub CLI (gh) instalado y autenticado
- Ajustar la variable `$repo` en el script con el nombre correcto del repositorio

## Notas Importantes

1. **Dependencias JPA**: Las dependencias de JPA aún están en el `pom.xml` pero ya no se usan en el código principal. Pueden ser removidas en una limpieza futura si se confirma que no se necesitan.

2. **Base de datos**: La aplicación ya no requiere una base de datos local. Todos los datos se gestionan a través del servicio externo.

3. **Despliegue**: Asegurarse de configurar la URL correcta del servicio en las variables de entorno de Azure antes del despliegue.

4. **Pruebas E2E**: Las pruebas end-to-end pueden necesitar ajustes si el servicio externo no está disponible durante las pruebas. Considerar usar un servicio mock o un servicio de prueba.
