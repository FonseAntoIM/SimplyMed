/* Prueba basica de render */
/// <reference types="vitest" />
import { test, expect } from 'vitest';

import { render, screen } from '@testing-library/react';
import RecetaCard from '../RecetaCard';

const recetaMock = {
  paciente: 'Carlos Ruiz',
  fecha: '2025-05-14',
  diagnostico: 'Infección Urinaria',
  medicamentos: [
    { nombre: 'Nitrofurantoína', dosis: '100mg', frecuencia: 'Cada 6 horas' }
  ]
};

test('renderiza receta con datos', () => {
  render(<RecetaCard receta={recetaMock} />);

  expect(screen.getByText(/Carlos Ruiz/i)).toBeInTheDocument();
  expect(screen.getByText(/Infección Urinaria/i)).toBeInTheDocument();
  expect(screen.getByText(/Nitrofurantoína/i)).toBeInTheDocument();
});
