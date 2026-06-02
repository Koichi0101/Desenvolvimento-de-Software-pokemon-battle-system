package battle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pokemon.Pokemon;
import pokemon.PokemonFactory;

public class Regla1Test {

    private BattleSystem battleEngine;
    private Pokemon pikachu;   // ID 25 (Velocidad base alta)
    private Pokemon snorlax;   // ID 143 (Velocidad base baja)

    @BeforeEach
    public void setUp() {
        // Inicializamos el motor de batalla y los Pokémon usando tu fábrica real
        battleEngine = new BattleSystem();
        pikachu = PokemonFactory.createPokemon(25);
        snorlax = PokemonFactory.createPokemon(143);
    }

    @Test
    public void testOrdenTurnos_JugadorMasRapidoDebeIniciar() {
        // Caso 1a: El Pokémon del jugador (Pikachu) es más rápido que el enemigo (Snorlax) -> Debe atacar primero (true)
        boolean resultado = battleEngine.determinarPrimerTurnoConsistente(pikachu, snorlax);
        assertTrue(resultado, "Lógica incorrecta: Pikachu al ser más rápido debió iniciar el turno.");
    }


    @Test
    public void testOrdenTurnos_VelocidadesIgualesMantieneOrdenConsistente() {
        // Caso 2: Ambas instancias tienen la misma velocidad -> El sistema mantiene un orden consistente (no aleatorio)
        Pokemon bulbasaurA = PokemonFactory.createPokemon(1);
        Pokemon bulbasaurB = PokemonFactory.createPokemon(1);

        // Evaluamos dos ejecuciones seguidas con los mismos datos
        boolean primerIntento = battleEngine.determinarPrimerTurnoConsistente(bulbasaurA, bulbasaurB);
        boolean segundoIntento = battleEngine.determinarPrimerTurnoConsistente(bulbasaurA, bulbasaurB);

        // Comprobamos que el resultado sea determinista (idéntico en ambas llamadas)
        assertEquals(primerIntento, segundoIntento,
                "Lógica incorrecta: El criterio de desempate varió entre ejecuciones consecutivas (no es consistente).");
    }
}