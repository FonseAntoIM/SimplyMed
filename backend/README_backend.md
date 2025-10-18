# SimplyMed Backend

## Resumen
Servicio Spring Boot 3.5 que expone la API REST y la aplicación MVC para SimplyMed.
- Java 17, Gradle 8.
- Persistencia H2 en memoria con seed inicial (data.sql).
- MVC + REST sobre la misma capa de servicio (RecipeServiceImpl).
- DTOs validados para REST (RecipeDTO, MedicationDTO), mapeados con ModelMapper.
- Tests unitarios/integración con JUnit 5, Mockito, @DataJpaTest, @SpringBootTest.

## Requisitos
- Java 17 (java -version).
- Gradle Wrapper incluido (gradlew).

## Ejecución
`ash
./gradlew clean build   # compila y ejecuta pruebas
./gradlew bootRun       # arranca API en http://localhost:8081
`
H2 Console disponible en http://localhost:8081/h2-console (driver JDBC org.h2.Driver, URL jdbc:h2:mem:simplymed).

## API principal
| Método | Endpoint                 | Descripción                     |
|--------|--------------------------|----------------------------------|
| GET    | /api/recipes             | Lista recetas (DTO)              |
| GET    | /api/recipes/{id}        | Obtiene receta por id            |
| POST   | /api/recipes             | Crea receta (JSON DTO)           |
| PUT    | /api/recipes/{id}        | Actualiza receta                 |
| DELETE | /api/recipes/{id}        | Elimina receta                   |
| POST   | /api/recipes/import      | Merge/import masivo {recipes}  |
| GET    | /api/recipes/export      | Exporta { "recipes": [...] } |

Errores se normalizan vía GlobalExceptionHandler (400, 404, 500).

## Estructura
`
src/main/java/com/simplymed
 ├── config/          # CORS, ModelMapper
 ├── domain/          # Entidades JPA (Recipe, Medication)
 ├── dto/             # DTOs REST + validaciones
 ├── repository/      # JpaRepository interfaces
 ├── service/         # RecipeService + impl transactional
 └── web/
     ├── mvc/         # Controlador Thymeleaf (/recipes)
     └── rest/        # API REST (/api/recipes)
`
src/main/resources/data.sql carga recetas demo al iniciar (solo en runtime regular; en tests se deshabilita).

## Tests
- RecipeRepositoryTest (@DataJpaTest)
- RecipeServiceMergeRegressionTest (@DataJpaTest + servicio real)
- TransactionRollbackTest (validación + rollback)
- RecipeRestIntegrationTest (@SpringBootTest con TestRestTemplate)

Ejecutar: ./gradlew test --info.

## Frontend
El frontend React (../frontend) consume la API. Variables de entorno:
`env
VITE_API_BASE=http://localhost:8081
`

## Notas
- CORS permite http://localhost:5173 y http://localhost:3000.
- spring.sql.init.mode=always + spring.jpa.defer-datasource-initialization=true aseguran seed en runtime; en pruebas se desactiva vía src/test/resources/application.properties.
- Para producción se puede cambiar la URL JDBC en pplication.yml y empaquetar con ./gradlew bootJar.
