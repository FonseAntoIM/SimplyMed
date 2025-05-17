import { useContext, useState } from "react";
//import { RecetaContext } from "../context/RecetaContext";
import useRecetas from '../hooks/useRecetas';
import RecetaCard from "../components/RecetaCard";
import Modal from "../components/Modal";

function Recetas() {;
  //const { recetas } = useContext(RecetaContext);
  const { recetas } = useRecetas();

  const [recetaSeleccionada, setRecetaSeleccionada] = useState(null);

  return (
    <div className="container mt-4">
      <h2>Recetas Médicas</h2>
      {recetas.map((receta) => (
        <div key={receta.id} onClick={() => setRecetaSeleccionada(receta)}>
          <RecetaCard receta={receta} />
        </div>
      ))}

      {recetaSeleccionada && (
        <Modal onClose={() => setRecetaSeleccionada(null)}>
          <h4>{recetaSeleccionada.paciente}</h4>
          <p><strong>Fecha:</strong> {recetaSeleccionada.fecha}</p>
          <p><strong>Diagnóstico:</strong> {recetaSeleccionada.diagnostico}</p>
          <ul>
            {recetaSeleccionada.medicamentos.map((med, i) => (
              <li key={i}>
                {med.nombre} - {med.dosis} - {med.frecuencia}
              </li>
            ))}
          </ul>
        </Modal>
      )}
    </div>
  );
}

export default Recetas;
