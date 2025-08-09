import { createContext, useReducer } from 'react';
import recetasMock from '../data/recetasMock';

export const RecetaContext = createContext();

const recetaReducer = (state, action) => {
  switch (action.type) {
    case 'AGREGAR_RECETA':
      return [...state, action.payload];

    case 'MERGE_RECETAS':
      // Fusiona sin duplicar (usa id como clave)
      const map = new Map(state.map(r => [r.id, r]));
      for (const r of action.payload) {
        map.set(r.id, r); // reemplaza si existe, agrega si no
      }
      return Array.from(map.values());

    default:
      return state;
  }
};

export const RecetaProvider = ({ children }) => {
  const [recetas, dispatch] = useReducer(recetaReducer, recetasMock);

  const agregarReceta = (nuevaReceta) => {
    dispatch({ type: 'AGREGAR_RECETA', payload: nuevaReceta });
  };

  const mergeRecetas = (nuevasRecetas) => {
    dispatch({ type: 'MERGE_RECETAS', payload: nuevasRecetas });
  };

  return (
    <RecetaContext.Provider value={{ recetas, agregarReceta, mergeRecetas }}>
      {children}
    </RecetaContext.Provider>
  );
};
