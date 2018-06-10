package test.androidtestc.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import test.androidtestc.Adapter.ClienteAdapter;
import test.androidtestc.DAO.ClienteDAO;
import test.androidtestc.DAO.Cliente;
import test.androidtestc.R;

public class ListarClientesActivity extends Activity {


    private ListView listaClientes;
    private ClienteAdapter myAdapter;
    ClienteDAO clienteDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_clientes);

        carregarElementos();
    }

    public void carregarElementos(){
        listaClientes = (ListView) findViewById(R.id.listClientes);
        clienteDAO = new ClienteDAO(this);
        List<Cliente> clientes = clienteDAO.carregaDadosLista(ClienteDAO.CLIENTES_TOTAL,MainActivity.usuarioLogado.getID());
        myAdapter = new ClienteAdapter(this,R.layout.item_cliente,clientes);
        listaClientes.setAdapter(myAdapter);
        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente cliente = (Cliente)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ListarClientesActivity.this,AtualizarClientesActivity.class);
                atualizarIntent.putExtra("ID_CLIENTE",cliente.getID());
                startActivity(atualizarIntent);
            }
        });
    }
}

