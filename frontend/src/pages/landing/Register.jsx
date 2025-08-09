import React from 'react';
import { Link } from 'react-router-dom';

const Register = () => {
    return (
        <div>
            <h1>Bienvenido a Registrar usuario</h1>
            <Link to="/inicio" className="btn btn-primary btn-lg btn-ingresar">
                Ingresar a la App
            </Link>
        </div>
    );
};

export default Register;