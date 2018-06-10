package test.androidtestc.Activity_ProdutoNota;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import test.androidtestc.Activity.MainActivity;
import test.androidtestc.DAO.ProdutoNota;
import test.androidtestc.DAO.ProdutoNotaDAO;
import test.androidtestc.R;

public class CadastrarProdutoNotaActivity extends Activity {


    private Spinner spinnerNomeProduto;
    private EditText editNumeroNota;
    private EditText editQuantidade;
    private EditText editValor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_produtonota);

        editNumeroNota = (EditText) findViewById(R.id.editNumeroNota);
        spinnerNomeProduto = (Spinner) findViewById(R.id.spinnerNomeProduto);
        editQuantidade = (EditText) findViewById(R.id.editQuantidade);
        editValor = (EditText) findViewById(R.id.editValor);

    }

    public void salvarProdutoNota(View v){
        ProdutoNotaDAO produtoNotaDAO = new ProdutoNotaDAO(this);
        ProdutoNota produtoNota = new ProdutoNota();

        produtoNota.setID_Nota(Long.parseLong(editNumeroNota.getText().toString()));
        produtoNota.setProduto(spinnerNomeProduto.getSelectedItem().toString());
        produtoNota.setQuantidade(Long.parseLong(editQuantidade.getText().toString()));
        produtoNota.setPreco(Double.parseDouble(editValor.getText().toString()));


        long resultado = produtoNotaDAO.insereDado(produtoNota);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarClientes = new Intent(CadastrarProdutoNotaActivity.this,MainActivity.class);
            startActivity(listarClientes);
            finish();
        }
        else{
            exibirMensagem("Erro ao cadastrar o item.");
        }
    }

    private void exibirMensagem(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}
