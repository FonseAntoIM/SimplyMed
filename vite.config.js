/// <reference types="vitest" />
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vitejs.dev/config/
export default defineConfig({
  base: '/landing',  // <- importante: mismo nombre del repo. Cambio de /SymplyMed/ a /landing
  plugins: [react()],
  server: {
    historyApiFallback: true, // <- Esto asegura que las rutas sean manejadas correctamente
  },
  test: {
    environment: 'jsdom',
    setupFiles: './vitest.setup.js',
  },
});
