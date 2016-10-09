package com.reactnativenavigation.views;

import android.content.Context;
import android.view.Menu;

import com.facebook.react.bridge.Callback;
import com.reactnativenavigation.events.ContextualMenuDismissed;
import com.reactnativenavigation.events.EventBus;
import com.reactnativenavigation.params.ContextualMenuButtonParams;
import com.reactnativenavigation.params.StyleParams;
import com.reactnativenavigation.params.TitleBarLeftButtonParams;

import java.util.List;

public class ContextualMenu extends TitleBar implements LeftButtonOnClickListener {
    private Callback onButtonClicked;

    public ContextualMenu(Context context, StyleParams styleParams, Callback onButtonClicked) {
        super(context);
        this.onButtonClicked = onButtonClicked;
        setStyle(styleParams);
    }

    @Override
    public void setStyle(StyleParams styleParams) {
        if (styleParams.contextualMenuBackgroundColor.hasColor()) {
            setBackgroundColor(styleParams.contextualMenuBackgroundColor.getColor());

        }
    }

    public void setButtons(List<ContextualMenuButtonParams> buttons, TitleBarLeftButtonParams leftButton,
                           StyleParams.Color buttonColor) {
        addButtons(buttons, leftButton, buttonColor, getMenu());
    }

    private void addButtons(List<ContextualMenuButtonParams> buttons, TitleBarLeftButtonParams leftButton, StyleParams.Color buttonColor, Menu menu) {
        setButtonsColor(buttons, leftButton, buttonColor);
        addButtonsToContextualMenu(buttons, menu);
        setBackButton(leftButton);
    }

    private void setButtonsColor(List<ContextualMenuButtonParams> buttons, TitleBarLeftButtonParams leftButton,
                                 StyleParams.Color buttonColor) {
        for (ContextualMenuButtonParams button : buttons) {
            button.setColorFromScreenStyle(buttonColor);
        }
        leftButton.setColorFromScreenStyle(buttonColor);
    }

    private void setBackButton(TitleBarLeftButtonParams leftButton) {
        setLeftButton(leftButton, this, null, false);
    }

    private void addButtonsToContextualMenu(List<ContextualMenuButtonParams> buttons, Menu menu) {
        for (int i = 0; i < buttons.size(); i++) {
            final TitleBarButton button = new TitleBarButton(menu, this, buttons.get(i));
            addButtonInReverseOrder(buttons, i, button);
        }
    }

    @Override
    public boolean onTitleBarBackButtonClick() {
        hide();
        EventBus.instance.post(new ContextualMenuDismissed());
        return true;
    }

    @Override
    public void onSideMenuButtonClick() {
        // nothing
    }
}
