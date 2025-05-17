function RecetaCard({ receta }) {
    return (
      <div className="card mb-3">
        <div className="card-header">
          <strong>{receta.paciente}</strong> - {receta.fecha}
        </div>
        <div className="card-body">
          <p><strong>Diagnóstico:</strong> {receta.diagnostico}</p>
          <ul>
            {receta.medicamentos.map((med, index) => (
              <li key={index}>
                {med.nombre} - {med.dosis} - {med.frecuencia}
              </li>
            ))}
          </ul>
        </div>
      </div>
    );
  }
  
  export default RecetaCard;
  