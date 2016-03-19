package com.exequiel.shopcenter.app.presentacion.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.presentacion.adapters.CustomLinearLayoutManager;
import com.exequiel.shopcenter.app.presentacion.adapters.DetalleComentarioComercioAdapter;
import com.exequiel.shopcenter.app.presentacion.adapters.ItemComercioNuevo;
import com.exequiel.shopcenter.app.presentacion.adapters.WizardInicialAdapter;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by exequiel on 19/03/2016.
 */
public class DetalleProductoFragment extends FrameworkBaseFragment {

    private static final String TAG_FRAGMENT = "DETALLE_PRODUCTO_FRAGMENT";

    //ViewPager
    CirclePageIndicator titlePageIndicator;
    ViewPager viewPager;
    WizardInicialAdapter wizardInicialAdapter;
    TextView txAntes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateView(inflater, container, savedInstanceState, R.layout.fragment_detalle_producto);
        //ViewPager
        titlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        txAntes = (TextView) view.findViewById(R.id.textView18);
        wizardInicialAdapter = new WizardInicialAdapter(getFragmentManager());
        viewPager.setAdapter(wizardInicialAdapter);
        titlePageIndicator.setViewPager(viewPager);
        //ViewPager
        DetalleComentarioComercioAdapter recyclerViewAdapter3 = new DetalleComentarioComercioAdapter();
        RecyclerView recyclerView3 = (RecyclerView) view.findViewById(R.id.recyclerview4);
        final CustomLinearLayoutManager layoutManager3 = new CustomLinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView3.setLayoutManager(layoutManager3);
        recyclerView3.setAdapter(recyclerViewAdapter3);
        recyclerView3.setNestedScrollingEnabled(false);
        tacharTexto(txAntes," $ 670 ");
        return view;
    }

    private void tacharTexto(TextView txt,String texto){
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


    @Override
    public String getTagFragment() {
        return null;
    }
}
