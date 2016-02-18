package com.exequiel.shopcenter.framework.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.exequiel.shopcenter.framework.data.entity.BaseDto;


/**
 * Created by exequiel on 02/06/2015.
 */
public abstract class ListFrameworkBaseActivity<T extends BaseDto> extends FrameworkBaseActivity implements AdapterView.OnItemClickListener {

    private ListView list;
    private TextView empty;

    protected ListView getListView(){
        if(list == null) {
            list = (ListView)findViewById(android.R.id.list);
            list.setOnItemClickListener(this);
            empty = (TextView)findViewById(android.R.id.empty);
        }
        return list;
    }

    private void verificaLista(ListAdapter adapter){
        if(empty != null && adapter.isEmpty()) {
            list.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }
        else {
            list.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
        }
    }

    protected void actualizaAdapter(ListAdapter adapter){
        verificaLista(adapter);
    }

    protected void setListAdapter(ListAdapter adapter)
    {
        getListView().setAdapter(adapter);
        verificaLista(adapter);
    }

    protected ListAdapter getListAdapter()
    {
        return getListView().getAdapter();
    }

}
