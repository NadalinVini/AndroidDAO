package test.androidtestc.Activity_Nota;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import test.androidtestc.Activity.MainActivity;
import test.androidtestc.DAO.Nota;
import test.androidtestc.DAO.NotaDAO;
import test.androidtestc.R;

public class CadastrarNotaActivity extends Activity {
    
    private Spinner spinnerTipoPagamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_nota);

        spinnerTipoPagamento = (Spinner) findViewById(R.id.spinnerTipoPagamento);

    }

    public void salvarNota(View v){
        NotaDAO notaDAO = new NotaDAO(this);
        Nota nota = new Nota();
        nota.setTipo_Pagamento(spinnerTipoPagamento.getSelectedItem().toString());
        nota.setIdCliente(MainActivity.usuarioLogado.getID());

        long resultado = notaDAO.insereDado(nota);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarNotas = new Intent(CadastrarNotaActivity.this,MainActivity.class);
            startActivity(listarNotas);
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
