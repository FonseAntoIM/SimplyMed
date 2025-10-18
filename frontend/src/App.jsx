import { BrowserRouter, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";

import Inicio from "./pages/app/Inicio";
import Recetas from "./pages/app/Recetas";
import Agenda from "./pages/app/Agenda";
import Expediente from "./pages/app/Expediente";
import NotFound from "./pages/app/NotFound";

import Landing from "./pages/landing/Landing";
import SignIn from "./pages/landing/SignIn";
import Register from "./pages/landing/Register";

import RecetasPage from "./pages/RecetasPage";
import NuevaRecetaRoute from "./components/routes/NuevaRecetaRoute.jsx";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Landing />} />
        <Route path="/landing" element={<Landing />} />

        <Route path="/signin" element={<SignIn />} />
        <Route path="/register" element={<Register />} />

        <Route path="/inicio" element={<Inicio />} />
        <Route path="/recetas" element={<Recetas />} />
        <Route path="/recetas/api" element={<RecetasPage />} />
        <Route path="/agenda" element={<Agenda />} />
        <Route path="/expediente" element={<Expediente />} />
        <Route path="/nueva" element={<NuevaRecetaRoute />} />

        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
