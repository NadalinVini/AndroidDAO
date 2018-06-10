package test.androidtestc.Activity_Nota;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import test.androidtestc.Activity.MainActivity;
import test.androidtestc.DAO.Nota;
import test.androidtestc.DAO.NotaDAO;
import test.androidtestc.R;

public class AtualizarNotasActivity extends Activity {

    private int ID_NOTA;
    private NotaDAO notaDAO;
    private Nota nota;

    private Spinner spinnerTipoPagamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_notas);

        Intent intent = getIntent();
        if(intent.hasExtra("ID_NOTA")){
            ID_NOTA = intent.getIntExtra("ID_NOTA",0);
        }
        notaDAO = new NotaDAO(this);
        nota = notaDAO.carregaNotaPorID(ID_NOTA);

        spinnerTipoPagamento = (Spinner) findViewById(R.id.spinnerTipoPagamentoUpdate);

        selectSpinnerItemByValue(spinnerTipoPagamento,nota.getTipo_Pagamento());
    }

    public void atualizarNota(View v){
        nota.setTipo_Pagamento(spinnerTipoPagamento.getSelectedItem().toString());


        if(notaDAO.atualizarNota(nota))
            Toast.makeText(AtualizarNotasActivity.this, "Nota atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(AtualizarNotasActivity.this, "Erro ao atualizar nota.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerNota(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notaDAO.deletaRegistro(ID_NOTA);
                Toast.makeText(AtualizarNotasActivity.this, "Nota removido com sucesso.", Toast.LENGTH_SHORT).show();
                telaInicial();
            }
        });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void telaInicial() {
        Intent telaInicial = new Intent(AtualizarNotasActivity.this,MainActivity.class);
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
