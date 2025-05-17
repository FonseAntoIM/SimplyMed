//import { useContext } from 'react';
//import { RecetaContext } from '../context/RecetaContext';
import useRecetas from '../hooks/useRecetas';

function Expediente() {
  //const { recetas } = useContext(RecetaContext);
  const { recetas } = useRecetas();

  // Agrupar por paciente
  const porPaciente = recetas.reduce((acc, receta) => {
    const { paciente, diagnostico, fecha, medicamentos } = receta;
    if (!acc[paciente]) {
      acc[paciente] = {
        total: 0,
        diagnosticos: new Set(),
        medicamentos: {},
        ultimaFecha: fecha
      };
    }
    acc[paciente].total += 1;
    acc[paciente].diagnosticos.add(diagnostico);
    if (fecha > acc[paciente].ultimaFecha) acc[paciente].ultimaFecha = fecha;

    medicamentos.forEach((med) => {
      const nombre = med.nombre;
      acc[paciente].medicamentos[nombre] = (acc[paciente].medicamentos[nombre] || 0) + 1;
    });

    return acc;
  }, {});

  return (
    <div className="container mt-4">
      <h2>Expediente Clínico</h2>

      {Object.keys(porPaciente).length === 0 ? (
        <p>No hay información aún.</p>
      ) : (
        Object.entries(porPaciente).map(([nombre, data], i) => (
          <div key={i} className="card mb-3">
            <div className="card-header bg-secondary text-white">
              <strong>{nombre}</strong> — Total de recetas: {data.total}
            </div>
            <div className="card-body">
              <p><strong>Última consulta:</strong> {data.ultimaFecha}</p>
              <p><strong>Diagnósticos registrados:</strong> {[...data.diagnosticos].join(', ')}</p>
              <p><strong>Medicamentos frecuentes:</strong></p>
              <ul>
                {Object.entries(data.medicamentos).map(([med, count], j) => (
                  <li key={j}>{med}: {count} veces</li>
                ))}
              </ul>
            </div>
          </div>
        ))
      )}
    </div>
  );
}

export default Expediente;
