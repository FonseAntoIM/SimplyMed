/// <reference types="vitest" />
import { render, screen, fireEvent } from '@testing-library/react';
import { describe, test, expect, vi } from 'vitest';
import NuevaReceta from '../NuevaReceta.jsx';
import { RecetaContext } from '../../context/RecetaContext';
import { BrowserRouter } from 'react-router-dom';

describe('NuevaReceta', () => {
  test('envía una receta válida y llama a agregarReceta', () => {
    const mockAgregar = vi.fn();

    render(
      <BrowserRouter>
        <RecetaContext.Provider value={{ recetas: [], agregarReceta: mockAgregar }}>
          <NuevaReceta />
        </RecetaContext.Provider>
      </BrowserRouter>
    );

    fireEvent.change(screen.getByLabelText(/Paciente/i), {
      target: { value: 'Juan Pérez' },
    });
    fireEvent.change(screen.getByLabelText(/Fecha/i), {
      target: { value: '2025-05-17' },
    });
    fireEvent.change(screen.getByLabelText(/Diagnóstico/i), {
      target: { value: 'Dolor de cabeza' },
    });

    // Llenamos el primer medicamento usando el placeholder, no el índice
    fireEvent.change(screen.getByPlaceholderText('Nombre'), {
      target: { value: 'Paracetamol' },
    });
    fireEvent.change(screen.getByPlaceholderText('Dosis'), {
      target: { value: '500mg' },
    });
    fireEvent.change(screen.getByPlaceholderText('Frecuencia'), {
      target: { value: 'Cada 8 horas' },
    });

    fireEvent.click(screen.getByRole('button', { name: /Guardar Receta/i }));

    expect(mockAgregar).toHaveBeenCalledTimes(1);
    expect(mockAgregar.mock.calls[0][0]).toMatchObject({
      paciente: 'Juan Pérez',
      diagnostico: 'Dolor de cabeza',
    });
  });
});
