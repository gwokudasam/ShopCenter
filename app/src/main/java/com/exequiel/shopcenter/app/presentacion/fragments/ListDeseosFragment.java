package com.exequiel.shopcenter.app.presentacion.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.view.BodyTextView;
import com.arlib.floatingsearchview.util.view.IconImageView;
import com.exequiel.shopcenter.R;
import com.exequiel.shopcenter.app.data.data.ColorSuggestion;
import com.exequiel.shopcenter.app.data.data.DataHelper;
import com.exequiel.shopcenter.app.presentacion.adapters.CustomLinearLayoutManager;
import com.exequiel.shopcenter.app.presentacion.adapters.DetalleComercioAdapter;
import com.exequiel.shopcenter.framework.ui.fragment.FrameworkBaseFragment;

import java.util.List;

/**
 * Created by exequiel on 21/03/2016.
 */
public class ListDeseosFragment extends FrameworkBaseFragment {

    private static final String TAG_FRAGMENT = "DESEOS_FRAGMENT";

    private FloatingSearchView mSearchView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateView(inflater, container, savedInstanceState, R.layout.list_productos);
        mSearchView = (FloatingSearchView)view.findViewById(R.id.floating_search_view);

        DetalleComercioAdapter recyclerViewAdapter1 = new DetalleComercioAdapter();
        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.recycler_products);
        final CustomLinearLayoutManager layoutManager1 = new CustomLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        //GRID LAYOUT recyclerView1.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(recyclerViewAdapter1);
        recyclerView1.setNestedScrollingEnabled(false);
        initSearch();
        return view;
    }

    @Override
    public String getTagFragment() {
        return TAG_FRAGMENT;
    }

    private void initSearch(){
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {

            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mSearchView.clearSuggestions();
                } else {

                    //this shows the top left circular progress
                    //you can call it where ever you want, but
                    //it makes sense to do it when loading something in
                    //the background.
                    mSearchView.showProgress();

                    //simulates a query call to a data source
                    //with a new query.
                    DataHelper.find(getContext(), newQuery, new DataHelper.OnFindResultsListener() {

                        @Override
                        public void onResults(List<ColorSuggestion> results) {

                            //this will swap the data and
                            //render the collapse/expand animations as necessary
                            mSearchView.swapSuggestions(results);

                            //let the users know that the background
                            //process has completed
                            mSearchView.hideProgress();
                        }
                    });
                }

                Log.d(TAG_FRAGMENT, "onSearchTextChanged()");
            }
        });

        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

                ColorSuggestion colorSuggestion = (ColorSuggestion) searchSuggestion;
                refreshBackgroundColor(colorSuggestion.getColor().getName(), colorSuggestion.getColor().getHex());

                Log.d(TAG_FRAGMENT, "onSuggestionClicked()");

            }

            @Override
            public void onSearchAction() {

                Log.d(TAG_FRAGMENT, "onSearchAction()");
            }
        });

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {

                //show suggestions when search bar gains focus (typically history suggestions)
                mSearchView.swapSuggestions(DataHelper.getHistory(getContext(), 3));

                Log.d(TAG_FRAGMENT, "onFocus()");
            }

            @Override
            public void onFocusCleared() {

                Log.d(TAG_FRAGMENT, "onFocusCleared()");
            }
        });

        //handle menu clicks the same way as you would
        //in a regular activity
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
/*
                if (item.getItemId() == R.id.action_change_colors) {

                    //demonstrate setting colors for items
                    mSearchView.setBackgroundColor(Color.parseColor("#ECE7D5"));
                    mSearchView.setViewTextColor(Color.parseColor("#cccccc"));
                    mSearchView.setHintTextColor(Color.parseColor("#596D73"));
                    mSearchView.setActionMenuOverflowColor(Color.parseColor("#B58900"));
                    mSearchView.setMenuItemIconColor(Color.parseColor("#2AA198"));
                    mSearchView.setLeftActionIconColor(Color.parseColor("#657A81"));
                    mSearchView.setClearBtnColor(Color.parseColor("#D30102"));
                    mSearchView.setSuggestionRightIconColor(Color.parseColor("#BCADAD"));
                    mSearchView.setDividerColor(Color.parseColor("#dfd7b9"));

                } else {

                    //just print action
                    Toast.makeText(getContext(), item.getTitle(),
                            Toast.LENGTH_SHORT).show();
                }*/

            }
        });

        //use this listener to listen to menu clicks when app:floatingSearch_leftAction="showHamburger"
        mSearchView.setOnLeftMenuClickListener(new FloatingSearchView.OnLeftMenuClickListener() {
            @Override
            public void onMenuOpened() {
                Log.d(TAG_FRAGMENT, "onMenuOpened()");

            }

            @Override
            public void onMenuClosed() {
                Log.d(TAG_FRAGMENT, "onMenuClosed()");
            }
        });

        //use this listener to listen to menu clicks when app:floatingSearch_leftAction="showHome"
        mSearchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {

                Log.d(TAG_FRAGMENT, "onHomeClicked()");
            }
        });

        /*
         * Here you have access to the left icon and the text of a given suggestion
         * item when as it is bound to the suggestion list. You can utilize this
         * callback to change some properties of the left icon and the text. For example, you
         * can load left icon images using your favorite image loading library, or change text color.
         *
         * Some restrictions:
         * 1. You can modify the height, eidth, margin, or padding of the text and left icon.
         * 2. You can't modify the text's size.
         *
         * Modifications to these properties will be ignored silently.
         */
        mSearchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(IconImageView leftIcon, BodyTextView bodyText, SearchSuggestion item, int itemPosition) {

                ColorSuggestion colorSuggestion = (ColorSuggestion) item;

                if (colorSuggestion.getIsHistory()) {
                    leftIcon.setImageDrawable(leftIcon.getResources().getDrawable(R.drawable.ic_history_black_24dp));
                    leftIcon.setBackgroundColor(Color.parseColor("#ffffff"));
                    leftIcon.setAlpha(.36f);
                } else
                    leftIcon.setImageDrawable(new ColorDrawable(Color.parseColor(colorSuggestion.getColor().getHex())));
            }

        });
    }


    private void refreshBackgroundColor(String colorName, String colorValue){

        int color = Color.parseColor(colorValue);
        Palette.Swatch swatch = new Palette.Swatch(color, 0);
/*
        mColorNameText.setTextColor(swatch.getTitleTextColor());
        mColorNameText.setText(colorName);

        mColorValueText.setTextColor(swatch.getBodyTextColor());
        mColorValueText.setText(colorValue);

        mParentView.setBackgroundColor(color);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
            getWindow().setStatusBarColor(getDarkerColor(color, .8f));*/

    }


}
