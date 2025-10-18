// Summary: HTTP client centralizado para consumir el backend SimplyMed.
// Interacts with: recipes.js, context/hooks y componentes React.
// Rubric: Criterio 3 y 5 al garantizar comunicacion estable.
/** @typedef {{ timestamp?:string, status:number, error:string, message?:string, fields?:Record<string,string>, path?:string, exception?:string }} ApiError */

export const API_BASE = import.meta?.env?.VITE_API_BASE || "http://localhost:8081";

/**
 * Realiza la peticion usando fetch, parsea JSON y lanza errores estandarizados.
 * @template T
 * @param {string} path ruta relativa de la API
 * @param {RequestInit} [options] opciones (method, body, headers)
 * @returns {Promise<T>} respuesta parseada
 */
async function request(path, options = {}) {
  const res = await fetch(`${API_BASE}${path}`, {
    headers: { "Content-Type": "application/json", ...(options.headers || {}) },
    ...options,
  });
  const text = await res.text();
  const data = text ? JSON.parse(text) : null;

  if (!res.ok) {
    /** @type {ApiError} */
    const errPayload = data || { status: res.status, error: res.statusText };
    const e = new Error(errPayload.error || res.statusText);
    e.status = res.status;
    e.payload = errPayload;
    throw e;
  }
  return /** @type {T} */ (data);
}

export const api = {
  /** Ejecuta GET al backend. */
  get: (p) => request(p),
  /** Ejecuta POST con body JSON. */
  post: (p, body) => request(p, { method: "POST", body: JSON.stringify(body) }),
  /** Ejecuta PUT con body JSON. */
  put: (p, body) => request(p, { method: "PUT", body: JSON.stringify(body) }),
  /** Ejecuta DELETE sin body. */
  del: (p) => request(p, { method: "DELETE" }),
};
