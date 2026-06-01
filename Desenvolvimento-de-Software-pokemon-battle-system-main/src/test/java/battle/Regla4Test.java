package battle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pokemon.Pokemon;
import pokemon.PokemonFactory;
import trainer.Trainer;

public class Regla4Test {

    private Pokemon pikachu;   // ID 25
    private Pokemon snorlax;   // ID 143
    private Trainer jugador;
    private Trainer enemigo;

    @BeforeEach
    public void setUp() {
        pikachu = PokemonFactory.createPokemon(25);
        snorlax = PokemonFactory.createPokemon(143);
        jugador = new Trainer("Ash");
        enemigo = new Trainer("Gary");
    }

    @Test
    public void testDerrota_PokemonCambiaAEstadoDebilitadoAlLlegarACero() {
        // Caso 1: Un Pokémon queda derrotado cuando su vida llega a cero.
        assertFalse(pikachu.isFainted(), "El Pokémon no debería iniciar debilitado.");

        // Aplicamos daño letal
        pikachu.receiveDamage(999);

        assertEquals(0, pikachu.getHp(), "El HP debió quedar exactamente en 0.");
        assertTrue(pikachu.isFainted(), "El estado isFainted() debe retornar true tras llegar a cero.");
    }

    @Test
    public void testDerrota_PokemonDerrotadoNoVuelveAAtacar() {
        // Caso 2: Un Pokémon derrotado no vuelve a atacar.
        // Simulamos que agregamos un Pokémon al equipo y este cae en combate
        jugador.addPokemon(pikachu);
        pikachu.receiveDamage(999); // Pikachu queda fuera de combate

        // El entrenador ya no debe reportar a este Pokémon como su primera opción viva
        assertNull(jugador.getFirstAlivePokemon(), "Un Pokémon derrotado no puede ser seleccionado para combatir.");
    }

    @Test
    public void testDerrota_IdentificaCorrectamenteSiElEntrenadorTienePokemonsVivos() {
        // Caso 3: El sistema identifica correctamente al ganador / condición de parada
        enemigo.addPokemon(snorlax);
        assertTrue(enemigo.hasAlivePokemons(), "El entrenador debería tener Pokémon vivos inicialmente.");

        // Debilitamos a todo su equipo (Snorlax)
        snorlax.receiveDamage(999);

        // El sistema de QA verifica que el entrenador ha perdido la condición de seguir combatiendo
        assertFalse(enemigo.hasAlivePokemons(), "El sistema falló al identificar que el entrenador ya no tiene Pokémon disponibles (Derrota).");
    }
}