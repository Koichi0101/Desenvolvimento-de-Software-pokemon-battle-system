package battle;

import pokemon.*;
import trainer.Trainer;

import java.util.Random;
import java.util.Scanner;

public class BattleMenu {

    private final Trainer player = new Trainer("Player");
    private final Trainer enemy = new Trainer("Computer");

    private final BattleSystem battleSystem = new BattleSystem();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Build Team");
            System.out.println("2 - Battle");
            System.out.println("3 - Show My Team");
            System.out.println("0 - Exit");

            int option = readOption(0, 3);

            switch (option) {
                case 1 -> buildPlayerTeam();
                case 2 -> chooseBattle();
                case 3 -> player.showTeam();
                case 0 -> {
                    System.out.println("Exiting game...");
                    return;
                }
            }
        }
    }

    private void buildPlayerTeam() {

        player.getTeam().clear();

        System.out.println("\n===== POKEMON DATABASE =====");
        showAllPokemons();

        for (int i = 0; i < 6; i++) {

            System.out.println("\nChoose pokemon " + (i + 1) + ":");

            int index = readOption(0, PokemonDatabase.POKEMONS.length - 1);

            Pokemon pokemon = PokemonFactory.createPokemon(index);

            player.addPokemon(pokemon);
        }

        System.out.println("\nTeam created successfully!");
    }

    private void chooseBattle() {

        if (player.getTeam().isEmpty()) {

            System.out.println("\nBuild your team first!");
            return;
        }

        prepareEnemyTeam();

        battleSystem.startBattle(player, enemy);
    }

    private void prepareEnemyTeam() {

        enemy.getTeam().clear();

        System.out.println("\n===== ENEMY TEAM MODE =====");
        System.out.println("1 - Choose manually");
        System.out.println("2 - Random team");

        int option = readOption(1, 2);

        if (option == 1) {

            System.out.println("\n===== POKEMON DATABASE =====");
            showAllPokemons();

            for (int i = 0; i < 6; i++) {

                System.out.println("\nChoose pokemon " + (i + 1) + " for enemy:");

                int index = readOption(0, PokemonDatabase.POKEMONS.length - 1);

                enemy.addPokemon(PokemonFactory.createPokemon(index));
            }
        }
        else {

            Random random = new Random();

            for (int i = 0; i < 6; i++) {

                int index = random.nextInt(PokemonDatabase.POKEMONS.length);

                enemy.addPokemon(PokemonFactory.createPokemon(index));
            }
            System.out.println("\nEnemy team generated randomly!");
        }
    }

    private void showAllPokemons() {

        for (int i = 0; i < PokemonDatabase.POKEMONS.length; i++) {

            Pokemon pokemon = PokemonFactory.createPokemon(i);
            pokemon.showSummary();
        }
    }

    private int readOption(int min, int max) {

        int option;

        while (true) {

            System.out.print("Escolha: ");

            if (scanner.hasNextInt()) {

                option = scanner.nextInt();

                if (option >= min && option <= max) {
                    return option;
                }
            }
            else {
                scanner.next();
            }

            System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
