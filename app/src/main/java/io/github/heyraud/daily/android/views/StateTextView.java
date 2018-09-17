package io.github.heyraud.daily.android.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.FloatRange;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * 描述
 *
 * @author aero.tang
 * @version 2018/9/17 23:04
 */
public class StateTextView extends AppCompatTextView {
    private float mStateAlpha = 0.3f;

    public StateTextView(Context context) {
        super(context);
    }

    public StateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if (isPressed() || isFocused() || !isEnabled()) {
            setAlpha(mStateAlpha);
        } else {
            setAlpha(1.0f);
        }
    }

    @Override
    public void setAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {
        final int alphaI = (int) (alpha * 255);
        Drawable bgDrawable = getBackground();
        if (null != bgDrawable) {
            bgDrawable = bgDrawable.mutate();
            bgDrawable.setAlpha(alphaI);
        }

        ColorStateList colorStateList = getTextColors();
        if (colorStateList != null) {
            setTextColor(colorStateList.withAlpha(alphaI));
        }
    }

    @Override
    public boolean hasOverlappingRendering() {
        return false; // super.hasOverlappingRendering();
    }
}
