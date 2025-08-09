/*
Qué hace cada opción (y cuándo usarla)
1. Importar JSON (web) y MERGE
    * Lee un archivo con formato { "recetas": [ ... ] } (como tu sample.json).
    * Convierte a objetos y fusiona por id con lo que ya haya en memoria: si el id ya existe, lo reemplaza; si no, lo agrega.

2. Listar recetas
    * Muestra en consola lo que está en memoria (se ordena por fecha gracias a Comparable).

3. Exportar JSON (estado actual) → recetas_local.json
    *Toma lo que está en memoria (lo que viste en la opción 2) y lo escribe a JSON con el wrapper { "recetas": [...] }.
    * Útil para “regresar” datos a la web o respaldar en formato de intercambio.
    * Con el JsonUtil debe escribir { "recetas": [ ... ] } (no null).

4. Exportar CSV → recetas.csv
    * Genera un CSV simple del estado en memoria, con una columna que “aplana” los medicamentos.
    * Es otro formato útil para abrir en Excel o importar en otros sistemas.

5. Guardar estado (bin) → recetas.dat
    * Serializa la lista en memoria a un binario.
    * Sirve para persistencia rápida local (sin JSON/CSV).
    * Nota: el programa, al arrancar, intenta cargar recetas.dat si existe

6. Cargar estado (bin) ← recetas.dat
    * Vuelve a leer recetas.dat y lo coloca en memoria.

7. Salir
    * Cierra el programa y vuelve a guardar recetas.dat (por conveniencia).

*/
package com.simplymed.client;

import com.simplymed.client.model.Recipe;
import com.simplymed.client.service.RecipeService;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths; // Java 8
import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner SC = new Scanner(System.in);
    private static final RecipeService SERVICE = new RecipeService();

    // Archivos por defecto (se crean en el working dir)
    private static final File BIN = new File("recetas.dat");
    private static final File CSV = new File("recetas.csv");
    private static final File JSON_LOCAL = new File("recetas_local.json");

    public static void main(String[] args) {
        banner();
        // Cargar estado bin si existe (persistencia rápida)
        SERVICE.loadBinaryIfExists(BIN);
        loop();
    }

    static void banner() {
        System.out.println("=== SimplyMed CLI ===");
        System.out.println("1) Importar JSON (exportado desde la Web) y MERGE");
        System.out.println("2) Listar recetas");
        System.out.println("3) Exportar JSON (estado actual)  -> recetas_local.json");
        System.out.println("4) Exportar CSV                   -> recetas.csv");
        System.out.println("5) Guardar estado (bin)           -> recetas.dat");
        System.out.println("6) Cargar estado (bin)            <- recetas.dat");
        System.out.println("7) Salir");
    }

    static void loop() {
        while (true) {
            System.out.print("\n> Opción: ");
            String op = SC.nextLine().trim();
            try {
                switch (op) {
                    case "1": importarJsonMerge(); break;
                    case "2": listar(); break;
                    case "3": exportarJson(); break;
                    case "4": exportarCsv(); break;
                    case "5": guardarBin(); break;
                    case "6": cargarBin(); break;
                    case "7": guardarBin(); System.out.println("¡Listo!"); return;
                    default: System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /** 1) Importar JSON exportado por la Web y hacer merge por id */
    static void importarJsonMerge() throws Exception {
        System.out.print("Ruta del JSON (ej: C:/.../recetas_123.json): ");
        Path p = Paths.get(SC.nextLine().trim()); // Java 8
        List<Recipe> imported = SERVICE.importFromJson(p);
        SERVICE.mergeById(imported);
        System.out.println("Importadas/mergeadas: " + imported.size());
    }

    /** 2) Listar recetas ordenadas por fecha (compareTo en Recipe) */
    static void listar() {
        List<Recipe> list = SERVICE.list();
        if (list.isEmpty()) {
            System.out.println("(sin recetas cargadas)");
            return;
        }
        list.stream().sorted().forEach(System.out::println);
        System.out.println("Total: " + list.size());
    }

    /** 3) Exportar JSON del estado actual (para “volver” a la Web si querés) */
    static void exportarJson() throws Exception {
        SERVICE.exportJson(JSON_LOCAL);
        System.out.println("JSON → " + JSON_LOCAL.getAbsolutePath());
    }

    /** 4) Exportar CSV legible */
    static void exportarCsv() throws Exception {
        SERVICE.exportCsv(CSV);
        System.out.println("CSV  → " + CSV.getAbsolutePath());
    }

    /** 5) Guardar estado binario (serializado) */
    static void guardarBin() {
        try {
            SERVICE.saveBinary(BIN);
            System.out.println("Estado guardado → " + BIN.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("No se pudo guardar: " + e.getMessage());
        }
    }

    /** 6) Cargar estado binario si existe */
    static void cargarBin() {
        SERVICE.loadBinaryIfExists(BIN);
        System.out.println("Estado cargado. Recetas: " + SERVICE.list().size());
    }
}
