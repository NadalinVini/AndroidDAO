package test.androidtestc.Activity_Nota;

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

import test.androidtestc.Activity.MainActivity;
import test.androidtestc.Adapter.NotaAdapter;
import test.androidtestc.DAO.Nota;
import test.androidtestc.DAO.NotaDAO;
import test.androidtestc.R;

public class ListarNotasActivity extends Activity {


    private ListView listaNotas;
    private NotaAdapter myAdapter;
    NotaDAO notaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_notas);

        carregarElementos();
    }

    public void carregarElementos(){
        listaNotas = (ListView) findViewById(R.id.ListNotas);
        notaDAO = new NotaDAO(this);
        List<Nota> notas = notaDAO.carregaDadosLista(NotaDAO.NOTAS_TOTAL, MainActivity.usuarioLogado.getID());
        myAdapter = new NotaAdapter(this,R.layout.item_nota,notas);
        listaNotas.setAdapter(myAdapter);
        listaNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Nota nota = (Nota) parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ListarNotasActivity.this,AtualizarNotasActivity.class);
                atualizarIntent.putExtra("ID_NOTA",nota.getID());
                startActivity(atualizarIntent);
            }
        });
    }
}

