/** @typedef {{ id?:number, name:string, dosage:string, frequencyPerDay:number, instructions?:string }} MedicationDTO */
/** @typedef {{ id?:number, patientName:string, doctorName:string, date:string, notes?:string, medications: MedicationDTO[] }} RecipeDTO */
/** @typedef {{ recipes: RecipeDTO[] }} RecipesEnvelope */
