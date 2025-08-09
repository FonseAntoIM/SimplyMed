import React from 'react';
import { Link } from 'react-router-dom';

const Landing = () => {
    return (

    <div class="hero container">
        <h1 class="display-4">SimplyMed</h1>
        <p class="lead">Tu historial médico, siempre disponible.</p>
    
        <p>
          🧑‍⚕️ Médicos pueden acceder a tu expediente para darte mejor atención.<br/>
          💊 Recibí recordatorios para tomar tus medicamentos.<br/>
          📊 Consultá enfermedades previas, tratamientos y diagnósticos pasados.<br/>
        </p>

        <Link to="/inicio" className="btn btn-primary btn-lg btn-ingresar">
                Ingresar a la App
        </Link>

    </div>
    );
};

export default Landing;