# Rubrica Backend SimplyMed

## Criterio 1 - Configuracion y tooling
- [x] Gradle wrapper incluido (`gradlew` / `gradlew.bat`).
- [x] Dependencias organizadas (Spring Boot 3.5, Validation, Web, Data JPA, ModelMapper, H2, pruebas).
- [x] Java 17 definido en toolchain.

## Criterio 2 - Spring MVC / capas / DI
- [x] Capas separadas: web (mvc/rest) -> service -> repository.
- [x] Controladores delegan al servicio (sin logica en controllers).
- [x] DTOs REST con validaciones (`@NotBlank`, `@Valid`).
- [x] GlobalExceptionHandler uniforme (400/404/500).
- [x] Tests de servicio y REST (`@DataJpaTest`, `@SpringBootTest`).
=> Nivel alcanzado: **Avanzado**.

## Criterio 3 - Interfaces de usuario
- [x] Frontend React consume `/api/recipes` (CRUD + import/export).
- [x] Import/export JSON estable, deduplicacion en merge.
- [x] MVC Thymeleaf disponible.
=> Nivel actual: **Intermedio / Avanzado**.

## Criterio 4 - Conexion a base de datos (Spring Data JPA)
- [x] Recipe y Medication como entidades con relacion 1-N y `@GeneratedValue(IDENTITY)`.
- [x] Repositorios Spring Data (`RecipeRepository`, `MedicationRepository`) + query paciente/medico/fecha.
- [x] RecipeServiceImpl persiste/merge respetando la relacion; controladores delegan.
- [x] `data.sql` siembra datos; `/api/recipes/export` y consola H2 reflejan la BD.
- [x] Tests JPA (`RecipeRepositoryTest`, `RecipeServiceMergeRegressionTest`).
=> Nivel alcanzado: **Avanzado**.

Sugerencias de refuerzo:
- Verificar `cascade = CascadeType.ALL` y `orphanRemoval = true` para merges complejos.
- Mantener `@Transactional` en operaciones de escritura (ya presente a nivel clase).
- Evitar `equals/hashCode` personalizados en colecciones `@OneToMany` (usar identidad).
- Considerar `@EntityGraph` o fetch join si se manejan listados grandes (evitar N+1).

## Criterio 5 - Estabilidad del proyecto
- [x] `./gradlew clean build` y `./gradlew bootRun` funcionando; `npm run dev` levanta el front.
- [x] GlobalExceptionHandler, validaciones declarativas, CORS en `application.yml`.
- [x] Documentacion actualizada (README, ARQUITECTURA, esta rubrica).
- [x] Distribuible con `./gradlew bootJar`; front y back comunicando correctamente.
=> Nivel alcanzado: **Avanzado**.

Sugerencias de refuerzo:
- Fijar versiones de plugins/deps (Gradle, Spring Boot, React/Vite) para builds reproducibles.
- Anadir `README`/`env.example` con matriz de comandos y variables (`VITE_API_BASE`, puertos).
- Crear script de smoke tests (curl) y seccion de troubleshooting (CORS, acceso H2).

## Proximos pasos sugeridos
- Preparar perfiles `dev` (H2) y `prod` (RDBMS real).
- Anadir seguridad/autenticacion si se requiere.
- Automatizar CI/CD y documentar la API (OpenAPI/Swagger).