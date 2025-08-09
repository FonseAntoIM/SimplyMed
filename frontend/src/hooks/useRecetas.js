import { useContext } from 'react';
import { RecetaContext } from '../context/RecetaContext';

function useRecetas() {
  const { recetas, agregarReceta } = useContext(RecetaContext);

  // Ejemplo: obtener todas las fechas de recetas
  const fechasRecetas = recetas.map((r) => r.fecha);

  // Ejemplo: recetas del paciente más activo
  const pacienteConMasRecetas = (() => {
    const count = {};
    recetas.forEach(r => count[r.paciente] = (count[r.paciente] || 0) + 1);
    const max = Object.entries(count).sort((a, b) => b[1] - a[1])[0];
    return max ? max[0] : null;
  })();

  return {
    recetas,
    agregarReceta,
    fechasRecetas,
    pacienteConMasRecetas
  };
}

export default useRecetas;
