// Summary: Funciones especificas para consumir la API de recetas.
// Interacts with: RecetaContext (hooks) y componentes como Recetas.jsx.
// Rubric: Criterio 3 al integrar frontend con backend REST.
import { api } from "./client.js";

/** Obtiene { recipes: [...] } para hidratar el estado global. */
export const listRecipes = () => api.get("/api/recipes/export");

/** Recupera una receta individual por id. */
export const getRecipe = (id) => api.get(`/api/recipes/${id}`);

/** Crea una receta nueva enviando RecipeDTO. */
export const createRecipe = (dto) => api.post("/api/recipes", dto);

/** Actualiza una receta existente mediante PUT. */
export const updateRecipe = (id, dto) => api.put(`/api/recipes/${id}`, dto);

/** Elimina una receta por id. */
export const deleteRecipe = (id) => api.del(`/api/recipes/${id}`);

/** Realiza import/merge masivo (envoltura { recipes: [...] }). */
export const importRecipes = (recipes) => api.post("/api/recipes/import", { recipes });
