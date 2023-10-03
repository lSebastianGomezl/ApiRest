package org.example;

public class Main {

    public static int aproximar(double numero, int valorDeseado) {
        int valorRedondeado = (int) (Math.floor(numero / valorDeseado) * valorDeseado);
        int valorSiguiente = valorRedondeado + valorDeseado;

        if (Math.abs(numero - valorRedondeado) < Math.abs(numero - valorSiguiente)) {
            return valorRedondeado;
        } else {
            return valorSiguiente;
        }
    }
    public static void main(String[] args) {

        double numero = 5950;
        int valorDeseado = 1000;
        int numeroAproximado = aproximar(numero, valorDeseado);

        System.out.println("Número original: " + numero);
        System.out.println("Número aproximado: " + numeroAproximado);

    }

}