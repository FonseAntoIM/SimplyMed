import React, { useState } from "react";
import { createRecipe, updateRecipe } from "../lib/api/recipes.js";

/** @param {{ initial?: import('../lib/dto.js').RecipeDTO, onSaved?: (r:any)=>void }} props */
export default function RecipeForm({ initial, onSaved }) {
  const empty = { patientName:"", doctorName:"", date:"", notes:"", medications:[{ name:"", dosage:"", frequencyPerDay:1, instructions:"" }] };
  const [form, setForm] = useState(initial || empty);
  const [errors, setErrors] = useState({});
  const [submitting, setSubmitting] = useState(false);

  const set = (k, v) => setForm(prev => ({ ...prev, [k]: v }));

  async function onSubmit(e) {
    e.preventDefault();
    setSubmitting(true); setErrors({});
    try {
      const saved = form.id ? await updateRecipe(form.id, form) : await createRecipe(form);
      onSaved?.(saved);
    } catch (err) {
      if (err.status === 400 && err.payload?.fields) setErrors(err.payload.fields);
      else setErrors({ _global: err.payload?.error || err.message });
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <form onSubmit={onSubmit}>
      {errors._global && <div style={{color:'red'}}>{errors._global}</div>}

      <label>Paciente</label>
      <input value={form.patientName} onChange={e=>set("patientName", e.target.value)} />
      {errors.patientName && <small style={{color:'red'}}>{errors.patientName}</small>}

      <label>Médico</label>
      <input value={form.doctorName} onChange={e=>set("doctorName", e.target.value)} />
      {errors.doctorName && <small style={{color:'red'}}>{errors.doctorName}</small>}

      <label>Fecha</label>
      <input type="date" value={form.date} onChange={e=>set("date", e.target.value)} />
      {errors.date && <small style={{color:'red'}}>{errors.date}</small>}

      <h4>Medicamento</h4>
      <input placeholder="Nombre"
        value={form.medications?.[0]?.name ?? ""}
        onChange={e=>{
          const meds = [...(form.medications ?? [{}])];
          meds[0] = { ...(meds[0]||{}), name: e.target.value };
          set("medications", meds);
        }}/>
      {errors["medications[0].name"] && <small style={{color:'red'}}>{errors["medications[0].name"]}</small>}

      <input placeholder="Dosis"
        value={form.medications?.[0]?.dosage ?? ""}
        onChange={e=>{
          const meds = [...(form.medications ?? [{}])];
          meds[0] = { ...(meds[0]||{}), dosage: e.target.value };
          set("medications", meds);
        }}/>
      {errors["medications[0].dosage"] && <small style={{color:'red'}}>{errors["medications[0].dosage"]}</small>}

      <input placeholder="Veces/día" type="number"
        value={form.medications?.[0]?.frequencyPerDay ?? 1}
        onChange={e=>{
          const meds = [...(form.medications ?? [{}])];
          meds[0] = { ...(meds[0]||{}), frequencyPerDay: Number(e.target.value) };
          set("medications", meds);
        }}/>
      {errors["medications[0].frequencyPerDay"] && <small style={{color:'red'}}>{errors["medications[0].frequencyPerDay"]}</small>}

      <button disabled={submitting}>{form.id ? "Actualizar" : "Crear"}</button>
    </form>
  );
}
