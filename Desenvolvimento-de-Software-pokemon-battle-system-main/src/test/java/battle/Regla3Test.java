package battle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import types.TypeChart;
import moves.MoveType;

public class Regla3Test {

    @Test
    public void testEfectividad_AtaqueSuperEfectivoCausaMasDano() {
        // Caso 1: Ataques efectivos causan más daño (Multiplicador = 2.0)
        double multiplicador = TypeChart.getMultiplier(MoveType.FIRE, MoveType.GRASS);
        assertEquals(2.0, multiplicador, 0.01, "Fuego contra Planta debería ser súper efectivo (2.0).");
    }

    @Test
    public void testEfectividad_AtaquePocoEfectivoCausaMenosDano() {
        // Caso 2: Ataques poco efectivos causan menos daño (Multiplicador = 0.5)
        double multiplicador = TypeChart.getMultiplier(MoveType.WATER, MoveType.GRASS);
        assertEquals(0.5, multiplicador, 0.01, "Agua contra Planta debería ser poco efectivo (0.5).");
    }

    @Test
    public void testEfectividad_AtaqueNeutroMantieneDanoBase() {
        // Caso 3: Ataques neutros mantienen el daño base (Multiplicador = 1.0)
        double multiplicador = TypeChart.getMultiplier(MoveType.NORMAL, MoveType.FIRE);
        assertEquals(1.0, multiplicador, 0.01, "Normal contra Fuego debería ser neutro (1.0).");
    }

    @Test
    public void testEfectividad_AtaqueInmuneNoCausaDano() {
        // Caso 4: Caso de borde / Inmunidades (Multiplicador = 0.0)
        double multiplicador = TypeChart.getMultiplier(MoveType.ELECTRIC, MoveType.GROUND);
        assertEquals(0.0, multiplicador, 0.01, "Eléctrico contra Tierra debería ser inmune (0.0).");
    }
}