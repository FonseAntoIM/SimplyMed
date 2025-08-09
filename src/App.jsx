import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Inicio from './pages/Inicio';
import Recetas from './pages/Recetas';
import Agenda from './pages/Agenda';
import Expediente from './pages/Expediente';
import NuevaReceta from './pages/NuevaReceta';
import NotFound from './pages/NotFound';

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/inicio" element={<Inicio />} />
        <Route path="/recetas" element={<Recetas />} />
        <Route path="/agenda" element={<Agenda />} />
        <Route path="/expediente" element={<Expediente />} />
        <Route path="/nueva" element={<NuevaReceta />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
