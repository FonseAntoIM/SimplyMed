-- Recetas
INSERT INTO recipe (id, patient_name, doctor_name, date, notes) VALUES
(1001, 'Juan Perez', 'Dra. Ana Ruiz', DATE '2025-10-16', 'Demo A'),
(1002, 'Maria Lopez', 'Dr. Luis Vega', DATE '2025-10-17', 'Demo B');

-- Medicamentos
INSERT INTO medication (id, name, dosage, frequency_per_day, instructions, recipe_id) VALUES
(2001, 'Paracetamol', '500 mg', 2, 'Despues de comer', 1001),
(2002, 'Ibuprofeno', '400 mg', 3, 'Cada 8 horas', 1001),
(2003, 'Amoxicilina', '500 mg', 3, '7 dias', 1002);
