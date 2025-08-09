/// <reference types="vitest" />
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

// https://vitejs.dev/config/
export default defineConfig({
  root: './frontend', // <- Asegúrate de que este sea el directorio correcto
  base: '/',  // <- importante: mismo nombre del repo. Cambio de /SymplyMed/ a /landing
  plugins: [react()],
  build: {
    outDir: 'dist', // Salida de compilación
  },
  publicDir: 'public', // Carpeta de archivos públicos
  server: {
    historyApiFallback: true, // <- Esto asegura que las rutas sean manejadas correctamente
  },
  test: {
    environment: 'jsdom',
    setupFiles: './vitest.setup.js',
  },
});
