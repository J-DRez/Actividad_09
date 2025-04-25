package ej09;

import java.util.ArrayList;
import java.util.Scanner;

public class ColeccionesDinamicas {
    public static void main(String[] args){
        ArrayList<String> ciudades = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n\u001B[34mGESTIÓN DE CIUDADES\u001B[0m\n");
            System.out.println("1. Agregar ciudad");
            System.out.println("2. Mostrar todas las ciudades");
            System.out.println("3. Buscar una ciudad");
            System.out.println("4. Eliminar una ciudad");
            System.out.println("5. Salir");
            System.out.print("\nSeleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Por favor, introduzca un número válido: ");
                scanner.next();
            }

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la ciudad a agregar: ");
                    String nuevaCiudad = scanner.nextLine().trim();

                    nuevaCiudad = normalizarCiudad(nuevaCiudad);
                    if (!ciudadValida(nuevaCiudad)) {
                        System.out.println("\u001B[31mNombre inválido.\u001B[0m Solo se permiten letras y espacios.");
                        break;
                    }
                    if (ciudades.contains(nuevaCiudad)) {
                        System.out.println("\u001B[31mX\u001B[0m "+"La ciudad ya existe en la lista.");
                    } else {
                        ciudades.add(nuevaCiudad);
                        System.out.println("\u001B[32mO\u001B[0m "+"Ciudad agregada correctamente.");
                    }
                    break;

                case 2:
                    if (ciudades.isEmpty()) {
                        System.out.println("\u001B[31mX\u001B[0m "+"No hay ciudades registradas.");
                    } else {
                        System.out.println("\nLista de ciudades:");
                        for (String ciudad : ciudades) {
                            System.out.println("- " + ciudad);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el nombre de la ciudad a buscar: ");
                    String buscarCiudad = scanner.nextLine().trim();
                    buscarCiudad = normalizarCiudad(buscarCiudad);
                    if (ciudades.contains(buscarCiudad)) {
                        System.out.println("\u001B[32mO\u001B[0m "+"La ciudad existe en la lista.");
                    } else {
                        System.out.println("\u001B[31mX\u001B[0m "+"La ciudad no se encuentra en la lista.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el nombre de la ciudad a eliminar: ");
                    String eliminarCiudad = scanner.nextLine().trim();
                    eliminarCiudad = normalizarCiudad(eliminarCiudad);
                    if (ciudades.remove(eliminarCiudad)) {
                        System.out.println("\u001B[32mO\u001B[0m "+"Ciudad eliminada correctamente.");
                    } else {
                        System.out.println("\u001B[31mX\u001B[0m "+"No se ha encontrado la ciudad en la lista.");
                    }
                    break;

                case 5:
                    System.out.println("\n\u001B[33mSaliendo del programa...\u001B[0m");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 5);

        scanner.close();
    }


    //En esta parte busqué por internet y usé chatGPT para saber como normalizar los nombres, ya que no me acordaba de como hacerlo,
    //pero aun con esas, no he conseguido que funcione bien, ya que no me detecta los acentos y ciudades como 'Málaga' no me las reconoce.
    public static String normalizarCiudad(String ciudad) {
        ciudad = ciudad.trim().toLowerCase();
        if (ciudad.isEmpty()) return ciudad;

        String[] palabras = ciudad.split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                resultado.append(Character.toUpperCase(palabra.charAt(0)));
                if (palabra.length() > 1) {
                    resultado.append(palabra.substring(1));
                }
                resultado.append(" ");
            }
        }

        return resultado.toString().trim();
    }

    public static boolean ciudadValida(String ciudad) {
        return ciudad.matches("^[\\p{L}\\s]+$");
    }

}

