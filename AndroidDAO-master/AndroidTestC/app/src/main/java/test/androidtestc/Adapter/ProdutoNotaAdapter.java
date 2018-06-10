package test.androidtestc.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import test.androidtestc.DAO.Cliente;
import test.androidtestc.DAO.ProdutoNota;
import test.androidtestc.R;

/**
 * Created by User on 05/06/2018.
 */

public class ProdutoNotaAdapter extends ArrayAdapter<ProdutoNota> {

    private int resource;
    private List<ProdutoNota> produtoNotas;

    public ProdutoNotaAdapter( Context context,  int resource, List<ProdutoNota> objects) {
        super(context, resource, objects);
        this.resource = resource;
        produtoNotas = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        ProdutoNota produtoNota = produtoNotas.get(position);

        TextView textIDProdutoNota = (TextView) mView.findViewById(R.id.textIDProdutoNota);
        TextView textNomeProduto = (TextView) mView.findViewById(R.id.textNomeProduto);
        TextView textNumeroNota = (TextView) mView.findViewById(R.id.textNumeroNota);
        TextView textQuantidade = (TextView) mView.findViewById(R.id.textQuantidade);
        TextView textValor = (TextView) mView.findViewById(R.id.textValor);

        if(textIDProdutoNota != null){
            textIDProdutoNota.setText(String.valueOf(produtoNota.getID()));
        }
        if(textNomeProduto != null){
            textNomeProduto.setText(produtoNota.getProduto());
        }
        if(textNumeroNota != null){
            textNumeroNota.setText(String.valueOf(produtoNota.getID_Nota()));
        }
        if(textQuantidade != null){
            textQuantidade.setText(String.valueOf(produtoNota.getQuantidade()));
        }
        if(textValor != null){
            textValor.setText(String.valueOf(produtoNota.getPreco()));
        }
        return mView;
    }
}
