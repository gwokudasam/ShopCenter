package com.exequiel.shopcenter.componentes.util;

	import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FontHelper {

	private static final String FONT_BOLD="fonts/CenturyGothicBold.ttf";
	private static final String FONT_NORMAL="fonts/CenturyGothic.ttf";

	/**
	 * Apply specified font for all text views (including nested ones) in the specified
	 * root view.
	 *
	 * @param context
	 *            Context to fetch font asset.
	 * @param root
	 *            Root view that should have specified font for all it's nested text
	 *            views.
	 */
	public static void applyFont(final Context context, final View root) {
		try {
			if (root instanceof ViewGroup) {
				ViewGroup viewGroup = (ViewGroup) root;
				int childCount = viewGroup.getChildCount();
				for (int i = 0; i < childCount; i++)
					applyFont(context, viewGroup.getChildAt(i));
			} else if (root instanceof TextView){
				TextView view =(TextView) root;
				Typeface faceOfView = view.getTypeface();
				int styleOfView= Typeface.NORMAL;
				if(faceOfView!=null)
					styleOfView = faceOfView.getStyle();
				if(styleOfView == Typeface.BOLD){
					view.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_BOLD));
				}else{
					view.setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_NORMAL));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
