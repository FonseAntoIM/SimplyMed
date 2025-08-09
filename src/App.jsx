import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Navbar from './components/Navbar';

import Inicio from './pages/app/Inicio';
import Recetas from './pages/app/Recetas';
import Agenda from './pages/app/Agenda';
import Expediente from './pages/app/Expediente';
import NuevaReceta from './pages/app/NuevaReceta';
import NotFound from './pages/app/NotFound';

import Landing from './pages/landing/Landing';
import SignIn from './pages/landing/SignIn';
import Register from './pages/landing/Register';

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        {/* Rutas del landing */}
        <Route path="/landing" element={<Landing />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/register" element={<Register />} />

        {/* Rutas de la aplicación principal */}
        <Route path="/inicio" element={<Inicio />} />
        <Route path="/recetas" element={<Recetas />} />
        <Route path="/agenda" element={<Agenda />} />
        <Route path="/expediente" element={<Expediente />} />
        <Route path="/nueva" element={<NuevaReceta />} />

        {/* Ruta para manejar páginas no encontradas */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
