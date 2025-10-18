import React from 'react';
import { Link } from 'react-router-dom';

const Landing = () => (
  <div className="hero container">
    <h1 className="display-4">SimplyMed</h1>
    <p className="lead">Tu historial médico, siempre disponible.</p>
    <p>
      Médicos autorizados pueden acceder a tu expediente para brindarte mejor atención.<br />
      Recibe recordatorios para tomar tus medicamentos a tiempo.<br />
      Consulta enfermedades previas, tratamientos y diagnósticos cuando lo necesites.
    </p>
    <Link to="/inicio" className="btn btn-primary btn-lg btn-ingresar">
      Ingresar a la App
    </Link>
  </div>
);

export default Landing;
