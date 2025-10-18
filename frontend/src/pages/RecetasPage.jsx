import React from "react";
import { useRecipes } from "../hooks/useRecipes.js";

export default function RecetasPage() {
  const { items, loading, error, remove, refresh } = useRecipes();

  if (loading) return <p>Cargando…</p>;
  if (error)   return <pre>Error: {String(error?.payload?.error || error?.message)}</pre>;

  return (
    <div>
      <h1>Recetas</h1>
      <button onClick={refresh}>Refrescar</button>
      <table>
        <thead><tr><th>ID</th><th>Paciente</th><th>Médico</th><th>Fecha</th><th/></tr></thead>
        <tbody>
          {items.map(r => (
            <tr key={r.id}>
              <td>{r.id}</td>
              <td>{r.patientName}</td>
              <td>{r.doctorName}</td>
              <td>{r.date}</td>
              <td><button onClick={() => remove(r.id)}>Eliminar</button></td>
            </tr>
          ))}
          {!items.length && <tr><td colSpan={5}>Sin recetas</td></tr>}
        </tbody>
      </table>
    </div>
  );
}
