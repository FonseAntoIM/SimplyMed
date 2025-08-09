// src/services/recetasService.js (agrega esta helper)
export const exportJson = () => {
    const data = list(); // las recetas del localStorage
    return JSON.stringify({ recetas: data }, null, 2);
  };
  