package domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Preguntona extends Casilla{

    private ArrayList<String> preguntas;
    private ArrayList<Boolean> respuestas;
    public Preguntona(int n) {
        super(n);
        preguntas = new ArrayList<>();
        respuestas = new ArrayList<>();
        preguntas.add("Francia es el segundo país más grande de Europa");
        respuestas.add(true);
        preguntas.add("Los delfines son mamíferos.");
        respuestas.add(true);
        preguntas.add("China es el país más grande del mundo.");
        respuestas.add(false);
        preguntas.add("Machu Picchu está en Perú.");
        respuestas.add(true);
        preguntas.add("Melbourne es la capital de Australia.");
        respuestas.add(false);
        preguntas.add("En 'Buscando a Nemo' el protagonista es un pez globo.");
        respuestas.add(false);
        preguntas.add("Thomas Edison descubrió la gravedad.");
        respuestas.add(false);
        preguntas.add("Alexander Fleming descubrió la penicilina.");
        respuestas.add(true);
        preguntas.add("Hay cinco grupos sanguíneos diferentes.");
        respuestas.add(false);
        preguntas.add("Marrakech es la capital de Marruecos.");
        respuestas.add(false);
        preguntas.add("Colombia tiene 32 departamentos");
        respuestas.add(true);
        preguntas.add("La segunda Guerra Mundial terminó en 1945");
        respuestas.add(true);
        preguntas.add("El cine también es conocido como Noveno arte");
        respuestas.add(false);
        preguntas.add("Hay más telefonos y dispositivos en el mundo que personas");
        respuestas.add(true);
        preguntas.add("El gentilicio de alguien de Bucaramanga es bumangués");
        respuestas.add(true);
    }

    public boolean ask(){
        return true;
    }
}
