package battle;

import pokemon.Pokemon;
import trainer.Trainer;

import java.util.Random;
import java.util.Scanner;

public class BattleSystem {

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    public void startBattle(Trainer playerTrainer, Trainer enemyTrainer) {

        Pokemon player = playerTrainer.getFirstAlivePokemon();
        Pokemon enemy = enemyTrainer.getFirstAlivePokemon();

        System.out.println("\n===== BATTLE START =====");

        while (player != null && enemy != null) {

            System.out.println("\n====================");
            System.out.println(player.getName() + " VS " + enemy.getName());
            System.out.println("====================");
            System.out.println(player.getName() + " HP: " + player.getHp());
            System.out.println(enemy.getName() + " HP: " + enemy.getHp());

            System.out.println("\n1 - Attack");
            System.out.println("2 - Switch Pokemon");

            int option = readOption(1, 2);

            if (option == 2) {
                Pokemon newPokemon = playerTrainer.choosePokemon(scanner);
                if (newPokemon != null && !newPokemon.isFainted()) {
                    player = newPokemon;
                    System.out.println("Go " + player.getName() + "!");
                }
                continue;
            }

            player.showMoves();
            int playerMove = readOption(0, 3);

            // REFACTORIZACIÓN COMPATIBLE CON TEST: Llamamos al método consistente
            boolean playerFirst = determinarPrimerTurnoConsistente(player, enemy);

            if (playerFirst) {
                player.useMove(playerMove, enemy);
                if (!enemy.isFainted()) {
                    int enemyMove = random.nextInt(4);
                    enemy.useMove(enemyMove, player);
                }
            }
            else {
                int enemyMove = random.nextInt(4);
                enemy.useMove(enemyMove, player);
                if (!player.isFainted()) {
                    player.useMove(playerMove, enemy);
                }
            }

            if (player.isFainted()) {
                System.out.println(player.getName() + " fainted!");
                player = playerTrainer.getFirstAlivePokemon();
                if (player != null) {
                    System.out.println("Go " + player.getName() + "!");
                }
            }

            if (enemy.isFainted()) {
                System.out.println(enemy.getName() + " fainted!");
                enemy = enemyTrainer.getFirstAlivePokemon();
                if (enemy != null) {
                    System.out.println("Enemy sent " + enemy.getName() + "!");
                }
            }
        }

        if (player == null) {
            System.out.println("\nYou lost the battle!");
        } else {
            System.out.println("\nYou won the battle!");
        }
    }

    /**
     * MÉTODO EXTRAÍDO PARA TESTING (Garantiza consistencia en Regla 1)
     */
    public boolean determinarPrimerTurnoConsistente(Pokemon player, Pokemon enemy) {
        if (player.getSpeed() > enemy.getSpeed()) {
            return true;
        }
        else if (enemy.getSpeed() > player.getSpeed()) {
            return false;
        }
        else {
            // Desempate determinista basado en orden alfabético para evitar aleatoriedad en el test
            return player.getName().compareTo(enemy.getName()) <= 0;
        }
    }

    private int readOption(int min, int max) {
        int option;
        while (true) {
            System.out.print("> ");
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                if (option >= min && option <= max) {
                    return option;
                }
            } else {
                scanner.next();
            }
            System.out.println("Invalid option.");
        }
    }
}
