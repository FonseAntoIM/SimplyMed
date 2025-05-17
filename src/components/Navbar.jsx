import { Link, NavLink } from 'react-router-dom';

function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary px-4">
      <Link className="navbar-brand" to="/">SimplyMed</Link>

      <div className="collapse navbar-collapse show">
        <ul className="navbar-nav ms-auto">
          <li className="nav-item">
          <a className="nav-link" href="/SimplyMed/landing.html">Inicio</a>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/recetas">Recetas</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/nueva">Nueva Receta</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/agenda">Agenda</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/expediente">Expediente</NavLink>
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;
