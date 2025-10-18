import { useEffect, useState } from "react";
import { listRecipes, deleteRecipe } from "../lib/api/recipes.js";

export function useRecipes() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  async function refresh() {
    try {
      setLoading(true);
      const res = await listRecipes();
      setItems(res?.recipes ?? []);
      setError(null);
    } catch (e) {
      setError(e);
    } finally {
      setLoading(false);
    }
  }

  async function remove(id) {
    await deleteRecipe(id);
    await refresh();
  }

  useEffect(() => { refresh(); }, []);
  return { items, loading, error, refresh, remove };
}
