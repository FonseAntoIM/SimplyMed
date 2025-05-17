import { useState,useContext } from 'react';
//import { RecetaContext } from '../context/RecetaContext';
import useRecetas from '../hooks/useRecetas';
import { useNavigate } from 'react-router-dom';
import './NuevaReceta.css';

function NuevaReceta() {
  const [paciente, setPaciente] = useState('');
  const [fecha, setFecha] = useState('');
  const [diagnostico, setDiagnostico] = useState('');
  const [medicamentos, setMedicamentos] = useState([{ nombre: '', dosis: '', frecuencia: '' }]);
  const [enviado, setEnviado] = useState(false);

  const handleMedicamentoChange = (index, field, value) => {
    const nuevos = [...medicamentos];
    nuevos[index][field] = value;
    setMedicamentos(nuevos);
  };

  const agregarMedicamento = () => {
    setMedicamentos([...medicamentos, { nombre: '', dosis: '', frecuencia: '' }]);
  };

  //const { agregarReceta } = useContext(RecetaContext);
  const { agregarReceta } = useRecetas();
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!paciente || !fecha || !diagnostico) return;
    console.log({ paciente, fecha, diagnostico, medicamentos });

    const nuevaReceta = {
        id: Date.now(),
        paciente,
        fecha,
        diagnostico,
        medicamentos,
      };
    
      agregarReceta(nuevaReceta);
      navigate('/recetas');

    setEnviado(true);
  };

  return (
    <div className="container mt-4">
      <h2>Nueva Receta Médica</h2>
      <form onSubmit={handleSubmit} className="p-3 border rounded bg-light">

        <div className="mb-3">
            <label htmlFor="paciente" className="form-label">Paciente</label>
            <input
                id="paciente"
                className={`form-control ${!paciente && enviado ? 'is-invalid' : ''}`}
                value={paciente}
                onChange={(e) => setPaciente(e.target.value)}
            />
        </div>

        <div className="mb-3">
            <label htmlFor="fecha" className="form-label">Fecha</label>
            <input
                id="fecha"
                type="date"
                className={`form-control ${!fecha && enviado ? 'is-invalid' : ''}`}
                value={fecha}
                onChange={(e) => setFecha(e.target.value)}
            />
        </div>


        <div className="mb-3">
            <label htmlFor="diagnostico" className="form-label">Diagnóstico</label>
            <input
                id="diagnostico"
                className={`form-control ${!diagnostico && enviado ? 'is-invalid' : ''}`}
                value={diagnostico}
                onChange={(e) => setDiagnostico(e.target.value)}
            />
        </div>


        <h5>Medicamentos</h5>
        {medicamentos.map((med, i) => (
          <div key={i} className="row mb-2">
            <div className="col">
              <input
                className="form-control"
                placeholder="Nombre"
                value={med.nombre}
                onChange={(e) => handleMedicamentoChange(i, 'nombre', e.target.value)}
              />
            </div>
            <div className="col">
              <input
                className="form-control"
                placeholder="Dosis"
                value={med.dosis}
                onChange={(e) => handleMedicamentoChange(i, 'dosis', e.target.value)}
              />
            </div>
            <div className="col">
              <input
                className="form-control"
                placeholder="Frecuencia"
                value={med.frecuencia}
                onChange={(e) => handleMedicamentoChange(i, 'frecuencia', e.target.value)}
              />
            </div>
          </div>
        ))}

        <button type="button" className="btn btn-secondary mb-3" onClick={agregarMedicamento}>
          + Agregar Medicamento
        </button>

        <button className="btn btn-primary w-100">Guardar Receta</button>

        {enviado && paciente && fecha && diagnostico && (
          <div className="alert alert-success mt-3">✅ Receta registrada (ver consola)</div>
        )}
      </form>
    </div>
  );
}

export default NuevaReceta;
