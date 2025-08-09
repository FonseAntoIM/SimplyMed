// src/pages/app/Recetas.jsx
import { useState } from 'react';
import useRecetas from '../../hooks/useRecetas';
import RecetaCard from '../../components/RecetaCard';
import Modal from '../../components/Modal';
import { exportRecetasJSON, importarRecetasDesdeArchivo } from '../../utils/jsonIO';

function Recetas() {
  const { recetas, mergeRecetas } = useRecetas();
  const [recetaSeleccionada, setRecetaSeleccionada] = useState(null);
  const [busy, setBusy] = useState(false);
  const fileInputId = 'import-json-input';

  const onExport = () => {
    exportRecetasJSON(recetas, 'recetas');
  };

  const onPickFile = () => {
    document.getElementById(fileInputId)?.click();
  };

  const onFileChange = async (e) => {
    const file = e.target.files?.[0];
    if (!file) return;
    try {
      setBusy(true);
      const nuevas = await importarRecetasDesdeArchivo(file);
      mergeRecetas(nuevas);
      alert(`✅ Importadas/mergeadas: ${nuevas.length}`);
    } catch (err) {
      alert(`❌ Error importando JSON: ${err.message}`);
    } finally {
      setBusy(false);
      e.target.value = ''; // resetea input para volver a seleccionar el mismo archivo si hace falta
    }
  };

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h2 className="m-0">Recetas Médicas</h2>
        <div className="d-flex gap-2">
          <button className="btn btn-outline-secondary" onClick={onPickFile} disabled={busy}>
            {busy ? 'Importando…' : 'Importar JSON'}
          </button>
          <input
            id={fileInputId}
            type="file"
            accept="application/json"
            onChange={onFileChange}
            style={{ display: 'none' }}
          />
          <button className="btn btn-primary" onClick={onExport}>
            Exportar JSON
          </button>
        </div>
      </div>

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
            {recetaSeleccionada.medicamentos.map((m, i) => (
              <li key={i}>{m.nombre} — {m.dosis} — {m.frecuencia}</li>
            ))}
          </ul>
        </Modal>
      )}
    </div>
  );
}

export default Recetas;
