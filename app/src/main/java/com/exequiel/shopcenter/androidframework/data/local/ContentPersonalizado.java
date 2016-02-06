package com.exequiel.shopcenter.androidframework.data.local;

import java.util.ArrayList;

/**
 * Created by exequiel on 03/06/2015.
 */
public class ContentPersonalizado {

    private ArrayList<ValuePair> lista;

    public ContentPersonalizado(){
        lista = new ArrayList<ValuePair>();
    }

    public void add(String nombColum, Object ob){
        lista.add(new ValuePair(nombColum,ob));
    }

    public ValuePair get(int pos){
        return lista.get(pos);
    }

    public int size(){
        return lista.size();
    }

    public class ValuePair{
        private final String nombColum;
        private final Object data;

        public ValuePair(String nombColum, Object data) {
            this.nombColum = nombColum;
            this.data = data;
        }

        public String getNombColum() { return nombColum; }
        public Object getData() { return data; }

        @Override
        public int hashCode() { return nombColum.hashCode() ^ data.hashCode(); }
    }
}
