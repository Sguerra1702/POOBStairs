package domain;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SnakesAndLaddersTest {
    private static SnakesAndLadders escalerasSerpientes;
    private static HashMap<String, Color> jugadores;

    @BeforeAll
    public static void setUp(){
        jugadores = new HashMap<>();
        jugadores.put("Máquina", Color.BLUE);
        jugadores.put("Prueba1", Color.black);
        escalerasSerpientes = new SnakesAndLadders(0,0,true, 60, jugadores);

    }
    @Test
    public void numberOfEndsShouldBeOdd(){
        ArrayList<Integer> start = escalerasSerpientes.getTablero().getItemsStart();
        ArrayList<Integer> end = escalerasSerpientes.getTablero().getItemsEnd();
        assertTrue((start.size() + end.size())%2 == 0);
    }

    @Test
    public void checkersShouldStartAtBottom(){
        HashMap<String, Jugador> jugadores = escalerasSerpientes.getJugadores();
        boolean value = false;
        for(String key: jugadores.keySet()){
            if(jugadores.get(key).getFichaJug().getPosX() == escalerasSerpientes.getTablero().getSIZE()-1 && jugadores.get(key).getFichaJug().getPosY() ==0){
                value = true;
            }
        }
        assertTrue(value);
    }
    @Test
    public void shouldMovewhenSpecial(){
        Saltarina casilla = new Saltarina(3);
        casilla.assignPositions(9,2);
        escalerasSerpientes.getTablero().getBoard()[9][2] = casilla;
        int[] positions = escalerasSerpientes.move(2);
        assertTrue(escalerasSerpientes.getJugadorNotEnTurno().getFichaJug().isThroughSpecial());
    }
    @Test
    public void shouldBeBackAtStartWhenMortal(){
        Mortal casilla = new Mortal(3);
        casilla.assignPositions(9,2);
        escalerasSerpientes.getTablero().getBoard()[9][2] = casilla;
        int[] positions = escalerasSerpientes.move(2);
        assertArrayEquals(new int[]{9,0}, positions);
    }

    @After
    public void tearDown()
    {

    }
}