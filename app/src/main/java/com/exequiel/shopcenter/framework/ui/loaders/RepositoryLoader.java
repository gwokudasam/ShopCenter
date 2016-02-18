package com.exequiel.shopcenter.framework.ui.loaders;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Cargador personalizado para utilizar contra
 * el Repository, este cargador escucha la actualización
 * del Repository mediante un BroadcastReceiver con lo cual
 * una depende de la otra, para el correcto funcionamiento
 * de esta funcionalidad.
 * Created by exequiel on 23/06/2015.
 */
public class RepositoryLoader<D> extends AsyncTaskLoader<D> {

    public static final String ACTION_BROADCAST_REPOSITORY_UPDATE="com.exet.educateria.REPOSITORY_UPDATED";
    public static final String EXTRA_HASH_CODE_CLASS_NAME_OBSERVER="classNameHashCode";

    private IQuery<D> mQuery;
    private RepositoryLoaderReceiver mReceiver;
    private int mHashCodeNameClass;
    private D mData;
    private boolean registered;

    public RepositoryLoader(Context context, IQuery<D> query, int hashCodeNameClass) {
        super(context);
        this.mQuery=query;
        mReceiver = new RepositoryLoaderReceiver();
        this.mHashCodeNameClass = hashCodeNameClass;
        registered = false;
    }

    @Override
    public D loadInBackground() {
        return mQuery.execute(getContext());
    }

    @Override
    public void deliverResult(D data) {
        if (isReset()) {
            return;
        }
        if (isStarted()) {
            // Si el Loader está en estado iniciado entregamos los resultados.
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(mData);
        }
        if(!registered){
            registered = true;
            registerReceiver();
        }
        if (takeContentChanged() || mData == null) {
            forceLoad();
        }
    }

    private void registerReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_BROADCAST_REPOSITORY_UPDATE);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mReceiver,intentFilter);
    }

    private void unregisterReceiver(){
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mReceiver);
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
        if (mData != null) {
            mData = null;
        }
        if(registered){
            registered = false;
            unregisterReceiver();
        }
    }

    public IQuery<D> getQuery() {
        return mQuery;
    }

    public void setQuery(IQuery<D> query) {
        this.mQuery = query;
        onContentChanged();
    }

    private class RepositoryLoaderReceiver extends BroadcastReceiver {

        private static final int COD_ERROR_DAFAULT=-1;

        @Override
        public void onReceive(Context context, Intent intent) {
            int hashCodeNameClassObservado = intent.getIntExtra(EXTRA_HASH_CODE_CLASS_NAME_OBSERVER,COD_ERROR_DAFAULT);
            if (hashCodeNameClassObservado != COD_ERROR_DAFAULT && hashCodeNameClassObservado == RepositoryLoader.this.mHashCodeNameClass){
                RepositoryLoader.this.onContentChanged();
            }
        }
    }

}
