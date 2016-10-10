package com.reactnativenavigation.params;

import java.util.List;

public class ContextualMenuParams {
    public List<ContextualMenuButtonParams> buttons;
    public TitleBarLeftButtonParams leftButton;

    public void setButtonsColor(StyleParams.Color buttonColor) {
        for (ContextualMenuButtonParams button : buttons) {
            button.setColorFromScreenStyle(buttonColor);
        }
        leftButton.setColorFromScreenStyle(buttonColor);
    }
}
