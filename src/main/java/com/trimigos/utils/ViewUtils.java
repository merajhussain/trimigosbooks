package com.trimigos.utils;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class ViewUtils {


    public static Button createSyledButton(String buttonText, FontAwesomeIcon icon, Color fillfolor, String size, String cssStyle) {
        Button button = new Button(buttonText);
        FontAwesomeIconView iconView = new FontAwesomeIconView(icon);
        iconView.setSize(size); // Set icon size
        iconView.setFill(fillfolor); // Set icon color
        button.setGraphic(iconView);
        if (!cssStyle.isEmpty()) {
            button.getStyleClass().add(cssStyle);
        }

        return button;

    }


}
