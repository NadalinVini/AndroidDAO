package test.androidtestc.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import test.androidtestc.DAO.Cliente;
import test.androidtestc.DAO.ClienteDAO;
import test.androidtestc.R;

public class AtualizarClientesActivity extends Activity {

    private int ID_CLIENTE;
    private ClienteDAO clienteDAO;
    private Cliente cliente;

    private EditText editNome;
    private EditText editEstado;
    private Spinner spinnerCidade;
    private EditText editCEP;
    private EditText editCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_clientes);

        Intent intent = getIntent();
        if(intent.hasExtra("ID_CLIENTE")){
            ID_CLIENTE = intent.getIntExtra("ID_CLIENTE",0);
        }
        clienteDAO = new ClienteDAO(this);
        cliente = clienteDAO.carregaClientePorID(ID_CLIENTE);

        editNome = (EditText) findViewById(R.id.editNomeUpdate);
        editEstado = (EditText) findViewById(R.id.editEstadoUpdate);
        spinnerCidade = (Spinner) findViewById(R.id.spinnerCidadeUpdate);
        editCEP = (EditText) findViewById(R.id.editCEPUpdate);
        editCPF = (EditText) findViewById(R.id.editCPFUpdate);

        editNome.setText(cliente.getNome());
        editEstado.setText(cliente.getEstado());
        selectSpinnerItemByValue(spinnerCidade,cliente.getCidade());
        Long i = cliente.getCEP();
        editCEP.setText(i.toString());
        Long l = cliente.getCPF();
        editCPF.setText(l.toString());
    }

    public void atualizarCliente(View v){
        cliente.setNome(editNome.getText().toString());
        cliente.setEstado(editEstado.getText().toString());
        cliente.setCidade(spinnerCidade.getSelectedItem().toString());
        cliente.setCEP(Long.parseLong(editCEP.getText().toString()));
        cliente.setCPF(Long.parseLong(editCPF.getText().toString()));

        if(clienteDAO.atualizaCliente(cliente))
            Toast.makeText(AtualizarClientesActivity.this, "Cliente atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(AtualizarClientesActivity.this, "Erro ao atualizar cliente.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerCliente(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clienteDAO.deletaRegistro(ID_CLIENTE);
                Toast.makeText(AtualizarClientesActivity.this, "Cliente removido com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(AtualizarClientesActivity.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }

    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if(adapter.getItem(position).toString().equals(value)) {
                spnr.setSelection(position);
                return;
            }
        }
    }
}
