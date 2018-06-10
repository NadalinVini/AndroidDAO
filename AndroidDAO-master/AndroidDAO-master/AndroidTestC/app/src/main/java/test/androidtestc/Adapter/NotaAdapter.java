package test.androidtestc.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import test.androidtestc.DAO.Nota;
import test.androidtestc.R;

/**
 * Created by User on 05/06/2018.
 */

public class NotaAdapter extends ArrayAdapter<Nota> {

    private int resource;
    private List<Nota> notas;

    public NotaAdapter( Context context,  int resource, List<Nota> objects) {
        super(context, resource, objects);
        this.resource = resource;
        notas = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Nota nota = notas.get(position);

        TextView textID = (TextView) mView.findViewById(R.id.textIDNota);
        TextView textPagamento = (TextView) mView.findViewById(R.id.textTipoPagamento);
        TextView textIDCliente = (TextView) mView.findViewById(R.id.textIdCliente);


        if(textID != null){
            textID.setText(String.valueOf(nota.getID()));
        }
        if(textPagamento != null){
            textPagamento.setText(nota.getTipo_Pagamento());
        }
        if(textIDCliente != null){
            textIDCliente.setText(String.valueOf(nota.getIdCliente()));
        }
        return mView;
    }
}
