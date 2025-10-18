import { useState } from "react";
import useRecetas from "../../hooks/useRecetas";

import RecetaCard from "../../components/RecetaCard";
import Modal from "../../components/Modal";
import { exportRecetasJSON, importarRecetasDesdeArchivo } from "../../utils/jsonIO";

function Recetas() {
  const { recetas, mergeRecetas, eliminarReceta, loading, error, refresh } = useRecetas();
  const [recetaSeleccionada, setRecetaSeleccionada] = useState(null);
  const [busy, setBusy] = useState(false);
  const fileInputId = "import-json-input";

  const onExport = () => {
    exportRecetasJSON(recetas, "recetas");
  };

  const onPickFile = () => {
    document.getElementById(fileInputId)?.click();
  };

  const onFileChange = async (event) => {
    const file = event.target.files?.[0];
    if (!file) return;
    try {
      setBusy(true);
      const nuevas = await importarRecetasDesdeArchivo(file);
      await mergeRecetas(nuevas);
      alert(`Importadas o actualizadas ${nuevas.length} recetas.`);
    } catch (err) {
      alert(`Error importando JSON: ${err.message}`);
    } finally {
      setBusy(false);
      event.target.value = "";
    }
  };

  const handleDelete = async (receta, event) => {
    event.stopPropagation();
    if (!receta?.id) return;
    if (!window.confirm(`Eliminar la receta de ${receta.paciente}?`)) return;
    try {
      await eliminarReceta(receta.id);
    } catch (err) {
      alert(`Error eliminando receta: ${String(err?.payload?.error || err?.message)}`);
    }
  };

  if (loading) {
    return (
      <div className="container mt-4">
        <p>Cargando recetas...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="container mt-4">
        <div className="alert alert-danger" role="alert">
          Error al cargar recetas: {String(error?.payload?.error || error?.message)}
        </div>
        <button className="btn btn-outline-secondary" onClick={refresh}>
          Reintentar
        </button>
      </div>
    );
  }

  return (
    <div className="container mt-4">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h2 className="m-0">Recetas Medicas</h2>
        <div className="d-flex gap-2">
          <button className="btn btn-outline-secondary" onClick={onPickFile} disabled={busy}>
            {busy ? "Importando..." : "Importar JSON"}
          </button>
          <input
            id={fileInputId}
            type="file"
            accept="application/json"
            onChange={onFileChange}
            style={{ display: "none" }}
          />
          <button className="btn btn-primary" onClick={onExport} disabled={!recetas.length}>
            Exportar JSON
          </button>
          <button className="btn btn-outline-primary" onClick={refresh}>
            Refrescar
          </button>
        </div>
      </div>

      {!recetas.length && <p>No hay recetas registradas todavia.</p>}

      {recetas.map((receta) => (
        <div key={receta.id} className="position-relative" onClick={() => setRecetaSeleccionada(receta)}>
          <div className="position-absolute top-0 end-0 mt-2 me-2">
            <button className="btn btn-sm btn-danger" onClick={(event) => handleDelete(receta, event)}>
              Eliminar
            </button>
          </div>
          <RecetaCard receta={receta} />
        </div>
      ))}

      {recetaSeleccionada && (
        <Modal onClose={() => setRecetaSeleccionada(null)}>
          <h4>{recetaSeleccionada.paciente}</h4>
          <p>
            <strong>Fecha:</strong> {recetaSeleccionada.fecha}
          </p>
          <p>
            <strong>Diagnostico:</strong> {recetaSeleccionada.diagnostico}
          </p>
          <ul>
            {recetaSeleccionada.medicamentos.map((medicamento, index) => (
              <li key={index}>
                {medicamento.nombre} - {medicamento.dosis} - {medicamento.frecuencia}
              </li>
            ))}
          </ul>
        </Modal>
      )}
    </div>
  );
}

export default Recetas;
