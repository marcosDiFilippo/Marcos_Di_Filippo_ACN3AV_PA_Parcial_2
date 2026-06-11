package constants;

import java.awt.Color;

public enum Colors {
    PRIMARY_COLOR(new Color(0, 51, 102)),        // Azul
    SECONDARY_GRAY(new Color(108, 117, 125)),    // Gris
    BACKGROUND_COLOR(new Color(245, 247, 250)),  // Fondo
    ACTION_ACCENT(new Color(13, 110, 253)),      // Celeste 
    SUCCESS_GREEN(new Color(40, 167, 69)),       // Verde 
    WHITE_COLOR(Color.WHITE),                    // Blanco
    INPUT_BORDER_COLOR(new Color(206, 212, 218)); // Borde de los inputs

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
