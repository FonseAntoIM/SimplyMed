//import { useContext } from 'react';
//import { RecetaContext } from '../context/RecetaContext';
import useRecetas from '../../hooks/useRecetas';

function Agenda() {
  //const { recetas } = useContext(RecetaContext);
  const { recetas } = useRecetas();
  const todasLasTomas = recetas.flatMap((receta) =>
    receta.medicamentos.map((med) => ({
      paciente: receta.paciente,
      fecha: receta.fecha,
      nombre: med.nombre,
      frecuencia: med.frecuencia,
      dosis: med.dosis,
    }))
  );

  return (
    <div className="container mt-4">
      <h2>Agenda de Medicamentos</h2>
      {todasLasTomas.length === 0 ? (
        <p>No hay medicamentos registrados aún.</p>
      ) : (
        <table className="table table-bordered table-hover">
          <thead className="table-dark">
            <tr>
              <th>Paciente</th>
              <th>Fecha</th>
              <th>Medicamento</th>
              <th>Dosis</th>
              <th>Frecuencia</th>
            </tr>
          </thead>
          <tbody>
            {todasLasTomas.map((item, i) => (
              <tr key={i}>
                <td>{item.paciente}</td>
                <td>{item.fecha}</td>
                <td>{item.nombre}</td>
                <td>{item.dosis}</td>
                <td>{item.frecuencia}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default Agenda;
