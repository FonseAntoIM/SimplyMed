/** @typedef {{ timestamp?:string, status:number, error:string, message?:string, fields?:Record<string,string>, path?:string }} ApiError */

export const API_BASE = import.meta?.env?.VITE_API_BASE || "http://localhost:8081";

/** @template T */
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
  get: (p) => request(p),
  post: (p, body) => request(p, { method: "POST", body: JSON.stringify(body) }),
  put:  (p, body) => request(p, { method: "PUT",  body: JSON.stringify(body) }),
  del:  (p)      => request(p, { method: "DELETE" }),
};
