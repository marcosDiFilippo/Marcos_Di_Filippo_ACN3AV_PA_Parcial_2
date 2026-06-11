package constants;

import java.awt.Color;

public enum Colors {
    PRIMARY_COLOR(new Color(0, 51, 102)),
    SECONDARY_GRAY(new Color(108, 117, 125)),
    BACKGROUND_COLOR(new Color(245, 247, 250)),
    ACTION_ACCENT(new Color(13, 110, 253)),
    SUCCESS_GREEN(new Color(40, 167, 69)),
    WHITE_COLOR(Color.WHITE),
    INPUT_BORDER_COLOR(new Color(206, 212, 218));

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
