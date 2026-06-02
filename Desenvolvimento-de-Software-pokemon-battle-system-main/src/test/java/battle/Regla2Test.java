package battle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import pokemon.Pokemon;
import pokemon.PokemonFactory;

public class Regla2Test {

    private Pokemon pikachu;
    private Pokemon snorlax;

    @BeforeEach
    public void setUp() {
        pikachu = PokemonFactory.createPokemon(25);
        snorlax = PokemonFactory.createPokemon(143);
    }

    @Test
    public void testCalculoDano_ReducePuntosDeVidaCorrectamente() {
        int hpInicial = snorlax.getHp();
        int danoEntrante = 100;

        snorlax.receiveDamage(danoEntrante);

        int hpEsperado = hpInicial - (danoEntrante - snorlax.getDefense());
        assertEquals(hpEsperado, snorlax.getHp());
    }

    @Test
    public void testCalculoDano_VidaNuncaQuedaEnValoresNegativos() {
        pikachu.receiveDamage(500);
        assertEquals(0, pikachu.getHp());
        assertTrue(pikachu.isFainted());
    }

    @Test
    public void testCalculoDano_SeAplicaUnaSolaVezPorTurno() {
        snorlax.receiveDamage(100);
        int hpDespuesDeUnGolpe = snorlax.getHp();
        assertEquals(125, hpDespuesDeUnGolpe);
    }
}