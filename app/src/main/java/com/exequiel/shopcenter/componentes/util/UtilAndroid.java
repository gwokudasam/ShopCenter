package com.exequiel.shopcenter.componentes.util;

import android.app.Activity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by exequiel on 01/09/2015.
 */
public class UtilAndroid {

    public static SpannableString textoSubrayado(String texto){
        SpannableString content = new SpannableString(texto);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        return  content;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

}
