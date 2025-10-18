import { api } from "./client.js";

/** @returns {Promise<import('../dto.js').RecipesEnvelope>} */
export const listRecipes = () => api.get("/api/recipes/export");

/** @param {number} id @returns {Promise<import('../dto.js').RecipeDTO>} */
export const getRecipe = (id) => api.get(`/api/recipes/${id}`);

/** @param {import('../dto.js').RecipeDTO} dto */
export const createRecipe = (dto) => api.post("/api/recipes", dto);

/** @param {number} id @param {import('../dto.js').RecipeDTO} dto */
export const updateRecipe = (id, dto) => api.put(`/api/recipes/${id}`, dto);

/** @param {number} id */
export const deleteRecipe = (id) => api.del(`/api/recipes/${id}`);

/** @param {import('../dto.js').RecipeDTO[]} recipes */
export const importRecipes = (recipes) => api.post("/api/recipes/import", { recipes });
