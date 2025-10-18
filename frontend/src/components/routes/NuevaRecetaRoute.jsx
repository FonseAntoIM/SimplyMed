import { useNavigate } from "react-router-dom";
import RecipeForm from "../RecipeForm.jsx";

export default function NuevaRecetaRoute() {
  const navigate = useNavigate();

  return (
    <div className="container mt-4">
      <h2>Nueva Receta (API)</h2>
      <RecipeForm onSaved={() => navigate("/recetas")} />
    </div>
  );
}
