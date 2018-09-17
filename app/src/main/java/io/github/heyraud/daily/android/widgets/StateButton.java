package io.github.heyraud.daily.android.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/9/17 23:04
 */
public class StateButton extends AppCompatButton {

    public StateButton(Context context) {
        super(context);
    }

    public StateButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
    }
}
