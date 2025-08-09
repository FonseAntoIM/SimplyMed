import { useContext } from 'react';
import { RecetaContext } from '../context/RecetaContext';

export default function useRecetas() {
  const { recetas, agregarReceta, mergeRecetas } = useContext(RecetaContext);
  const total = recetas.length;
  return { recetas, total, agregarReceta, mergeRecetas };
}
