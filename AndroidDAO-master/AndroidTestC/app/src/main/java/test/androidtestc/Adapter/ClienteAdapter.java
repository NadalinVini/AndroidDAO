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
import test.androidtestc.R;

/**
 * Created by User on 05/06/2018.
 */

public class ClienteAdapter extends ArrayAdapter<Cliente> {

    private int resource;
    private List<Cliente> clientes;

    public ClienteAdapter( Context context,  int resource, List<Cliente> objects) {
        super(context, resource, objects);
        this.resource = resource;
        clientes = objects;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Cliente cliente = clientes.get(position);

        TextView textID = (TextView) mView.findViewById(R.id.textID);
        TextView textNome = (TextView) mView.findViewById(R.id.textNome);
        TextView textEstado = (TextView) mView.findViewById(R.id.textEstado);
        TextView textCidade = (TextView) mView.findViewById(R.id.textCidade);
        TextView textCEP = (TextView) mView.findViewById(R.id.textCEP);
        TextView textCPF = (TextView) mView.findViewById(R.id.textCPF);

        if(textID != null){
            textID.setText(String.valueOf(cliente.getID()));
        }
        if(textNome != null){
            textNome.setText(cliente.getNome());
        }
        if(textEstado != null){
            textEstado.setText(cliente.getEstado());
        }
        if(textCidade != null){
            textCidade.setText(String.valueOf(cliente.getCidade()));
        }
        if(textCEP != null){
            textCEP.setText(String.valueOf(cliente.getCEP()));
        }
        if(textCPF != null){
            textCPF.setText(String.valueOf(cliente.getCPF()));
        }
        return mView;
    }
}
