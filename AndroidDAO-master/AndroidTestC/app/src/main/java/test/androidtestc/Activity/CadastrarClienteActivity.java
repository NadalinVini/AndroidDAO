package test.androidtestc.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import test.androidtestc.DAO.Cliente;
import test.androidtestc.DAO.ClienteDAO;
import test.androidtestc.R;

import static java.lang.Long.parseLong;

public class CadastrarClienteActivity extends Activity {

    private EditText editNome;
    private EditText editEstado;
    private Spinner spinnerCidade;
    private EditText editCEP;
    private EditText editCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        editNome = (EditText) findViewById(R.id.editNome);
        editEstado = (EditText) findViewById(R.id.editEstado);
        spinnerCidade = (Spinner) findViewById(R.id.spinnerCidade);
        editCEP = (EditText) findViewById(R.id.editCEP);
        editCPF = (EditText) findViewById(R.id.editCPF);

    }

    public void salvarCliente(View v){
        ClienteDAO clienteDAO = new ClienteDAO(this);
        Cliente cliente = new Cliente();
        cliente.setNome(editNome.getText().toString());
        cliente.setEstado(editEstado.getText().toString());
        cliente.setCidade(spinnerCidade.getSelectedItem().toString());
        cliente.setCEP(Long.parseLong(editCEP.getText().toString()));
        cliente.setCPF(Long.parseLong(editCPF.getText().toString()));
        cliente.setId_usuario(MainActivity.usuarioLogado.getID());

        long resultado = clienteDAO.insereDado(cliente);

        if(resultado > 0){
            exibirMensagem("Cadastro realizado com sucesso!");
            Intent listarClientes = new Intent(CadastrarClienteActivity.this,MainActivity.class);
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
