package com.reactnativenavigation.params.parsers;

import android.os.Bundle;

import com.facebook.react.bridge.Callback;
import com.reactnativenavigation.params.ContextualMenuParams;

public class ContextualMenuParamsParser extends Parser {
    public ContextualMenuParams parse(Bundle bundle, Callback onButtonClicked) {
        ContextualMenuParams result = new ContextualMenuParams();
        result.buttons = new ContextualMenuButtonParamsParser().parseContextualMenuButtons(bundle.getBundle("buttons"));
        result.leftButton = new TitleBarLeftButtonParamsParser().parseSingleButton(bundle.getBundle("backButton"));
        return result;
    }
}
