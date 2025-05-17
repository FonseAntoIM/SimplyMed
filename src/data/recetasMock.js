const recetasMock = [
    {
      id: 1,
      paciente: "Mock Data - María Gómez",
      fecha: "2024-05-10",
      diagnostico: "Gripe",
      medicamentos: [
        { nombre: "Paracetamol", dosis: "500mg", frecuencia: "Cada 8 horas" },
        { nombre: "Ambroxol", dosis: "30mg", frecuencia: "Cada 12 horas" }
      ]
    },
    {
      id: 2,
      paciente: "Mock Data - Carlos Ruiz",
      fecha: "2025-05-14",
      diagnostico: "Infección Urinaria",
      medicamentos: [
        { nombre: "Nitrofurantoína", dosis: "100mg", frecuencia: "Cada 6 horas" }
      ]
    },
    {
        id: 3,
        paciente: "Mock Data - María Gómez",
        fecha: "2025-05-10",
        diagnostico: "Gripe 2",
        medicamentos: [
          { nombre: "Paracetamol", dosis: "500mg", frecuencia: "Cada 8 horas" },
          { nombre: "Ambroxol", dosis: "30mg", frecuencia: "Cada 12 horas" }
        ]
      },
  ];
  
  export default recetasMock;
  