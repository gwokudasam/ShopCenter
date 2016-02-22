package com.exequiel.shopcenter.framework.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author exe
 */
public class ImageViewRepeatBackground extends ImageView
{
    public ImageViewRepeatBackground(Context context)
    {
        super(context);
    }

    public ImageViewRepeatBackground(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ImageViewRepeatBackground(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Drawable bg = this.getBackground();
        if (bg != null) {
            if (bg instanceof BitmapDrawable) {
                BitmapDrawable bmp = (BitmapDrawable) bg;
                bmp.mutate(); // make sure that we aren't sharing state anymore
                bmp.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
            }
        }
    }
}