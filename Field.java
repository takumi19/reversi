public class Field {
    private Color color;
    public Field() {
        this.color = Color.NONE;
    }
    public Field(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}

enum Color {
    BLACK,
    WHITE,
    NONE
}