# Arquitectura Backend SimplyMed

## Visión general
Aplicación Spring Boot monocapa con división lógica:
`
UI (Thymeleaf/REST) → web/ (mvc, rest)
Service Layer        → service/ (interfaces + impl @Transactional)
Persistence          → repository/ (Spring Data JPA)
Domain Model         → domain/ (Entidades JPA)
`

## Flujo REST
1. RecipeRestController recibe JSON (RecipeDTO).
2. DTO validado (@Validated / @Valid) y mapeado a entidad vía ModelMapper.
3. RecipeServiceImpl.merge aplica reglas (actualiza por id o por clave natural paciente/médico/fecha, reemplaza medicamentos).
4. RecipeRepository realiza persistencia (save, deleteById), JPA se encarga de cascadas Medication.
5. Respuesta vuelve en DTO (sin exponer entidad). Errores se centralizan en GlobalExceptionHandler.

## Flujo MVC (/recipes)
- RecipeController → Thymeleaf (ecipe-list, ecipe-form), usa la misma capa de servicio.
- Formularios se validan (@Valid) y se guardan vía RecipeService.save.

## Persistencia
- H2 en memoria (jdbc:h2:mem:simplymed) para desarrollo/testing.
- Seed determinista en data.sql con recetas iniciales.
- En pruebas unitarias se desactiva el seed (src/test/resources/application.properties).
- Entidades usan @GeneratedValue(strategy = GenerationType.IDENTITY).

## Componentes destacados
- GlobalExceptionHandler: respuestas uniformes para 400/404/500.
- ModelMapperConfig: registro de bean para mapear DTO ↔ entidad.
- CorsConfig: habilita orígenes front (5173, 3000).
- RecipeServiceImpl.merge: lógica de negocio (merge/import) + normalización de claves naturales.

## Integración con frontend
- SPA React consume /api/recipes (listado, CRUD, import/export).
- JSON de import: { "recipes": [ RecipeDTO... ] } → permite importar desde el front (jsonIO).
- Export produce mismo formato para CLI/React.

## Tests clave
- RecipeServiceMergeRegressionTest: asegura que merge reemplaza medicamentos y crea registros nuevos.
- RecipeRestIntegrationTest: valida import/export end-to-end.
- TransactionRollbackTest: garantiza rollback en validaciones fallidas.

## Consideraciones futuras
- Preparar perfiles dev (H2) y prod (RDBMS real).
- Añadir seguridad (Spring Security) si se requiere autenticación.
- Documentar API (OpenAPI/Swagger) si el proyecto evoluciona.
