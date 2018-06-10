package test.androidtestc.Activity_ProdutoNota;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import test.androidtestc.Activity.MainActivity;
import test.androidtestc.Adapter.ProdutoNotaAdapter;
import test.androidtestc.DAO.ProdutoNota;
import test.androidtestc.DAO.ProdutoNotaDAO;
import test.androidtestc.R;

public class ListarProdutoNotasActivity extends Activity {


    private ListView listaProdutosNotas;
    private ProdutoNotaAdapter myAdapter;
    ProdutoNotaDAO produtoNotaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtonota);

        carregarElementos();
    }

    public void carregarElementos(){
        listaProdutosNotas = (ListView) findViewById(R.id.listaProdutoNota);
        produtoNotaDAO = new ProdutoNotaDAO(this);
        List<ProdutoNota> produtoNotas = produtoNotaDAO.carregaDadosLista(ProdutoNotaDAO.PRODUTOS_TOTAL,MainActivity.usuarioLogado.getID());
        myAdapter = new ProdutoNotaAdapter(this,R.layout.item_produto_nota,produtoNotas);
        listaProdutosNotas.setAdapter(myAdapter);
        listaProdutosNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProdutoNota produtoNota = (ProdutoNota)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ListarProdutoNotasActivity.this,MainActivity.class);
                atualizarIntent.putExtra("ID_PRODUTONOTA",produtoNota.getID());
                startActivity(atualizarIntent);
            }
        });
    }
}

