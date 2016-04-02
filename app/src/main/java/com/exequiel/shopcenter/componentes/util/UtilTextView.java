package com.exequiel.shopcenter.componentes.util;

import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;

/**
 * Created by exequiel on 31/03/2016.
 */
public class UtilTextView {

    public static void tacharTexto(TextView txt,String texto){
        // Use a SpannableStringBuilder so that both the text and the spans are mutable
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        // Create a span that will strikethrough the text
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        // Add the secondWord and apply the strikethrough span to only the second word
        ssb.append(texto);
        ssb.setSpan(
                strikethroughSpan,
                ssb.length() - texto.length(),
                ssb.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the TextView text and denote that it is Editable
        // since it's a SpannableStringBuilder
        txt.setText(ssb, TextView.BufferType.EDITABLE);
    }

}
