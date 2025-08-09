import { createContext, useReducer } from 'react';
import recetasMock from '../data/recetasMock';

export const RecetaContext = createContext();

const recetaReducer = (state, action) => {
  switch (action.type) {
    case 'AGREGAR_RECETA':
      return [...state, action.payload];
    default:
      return state;
  }
};

export const RecetaProvider = ({ children }) => {
  const [recetas, dispatch] = useReducer(recetaReducer, recetasMock);

  const agregarReceta = (nuevaReceta) => {
    dispatch({ type: 'AGREGAR_RECETA', payload: nuevaReceta });
  };

  return (
    <RecetaContext.Provider value={{ recetas, agregarReceta }}>
      {children}
    </RecetaContext.Provider>
  );
};
