package battle;

import org.junit.jupiter.api.Test;
import pokemon.Pokemon;
import pokemon.PokemonFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Regla5Test {

    @Test
    public void testAtributosPermanecenConstantesTrasAtaque() {
        Pokemon p = PokemonFactory.createPokemon(25);
        int atkOriginal = p.getAttack();
        int defOriginal = p.getDefense();

        p.receiveDamage(50); // Daño suficiente para que el HP baje

        assertEquals(atkOriginal, p.getAttack(), "El ataque NO debe cambiar");
        assertEquals(defOriginal, p.getDefense(), "La defensa NO debe cambiar");
    }
    @Test
    public void testSinEfectosColateralesEntreTurnos() {
        Pokemon p1 = PokemonFactory.createPokemon(25); // Pikachu
        Pokemon p2 = PokemonFactory.createPokemon(4);  // Charmander

        int hpP2Inicial = p2.getHp();

        // Acción sobre p1, debería ser independiente de p2
        p1.receiveDamage(10);

        assertEquals(hpP2Inicial, p2.getHp(), "El HP de p2 NO debería cambiar si solo p1 recibió daño");
    }
} // <--- ¡Asegúrate de que esta llave esté aquí!