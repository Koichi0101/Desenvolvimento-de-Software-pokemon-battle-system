package moves;

public class Move {

    private String name;
    private MoveType type;
    private MoveCategory category;
    private int power;

    public Move(String name, MoveType type, MoveCategory category, int power) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.power = power;
    }

    public String getName() {
        return name;
    }
    public MoveType getType() {
        return type;
    }
    public MoveCategory getCategory() {
        return category;
    }
    public int getPower() {
        return power;
    }
}
