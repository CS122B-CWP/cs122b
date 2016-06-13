package com.example.pengyuanfan.fablix.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by pengyuanfan on 6/9/2016.
 */
public class DontPressWithParentButton extends Button {
    public DontPressWithParentButton(Context context) {
        super(context);
    }

    public DontPressWithParentButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DontPressWithParentButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setPressed(boolean pressed) {
        if (pressed && getParent() instanceof View && ((View) getParent()).isPressed()) {
            return;
        }
        super.setPressed(pressed);
    }
}
