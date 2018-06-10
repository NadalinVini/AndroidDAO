package test.androidtestc.DAO;

/**
 * Created by User on 09/06/2018.
 */

public class Nota {
    private int ID;
    private String Tipo_Pagamento;
    private long idCliente;

    public Nota() {
    }

    public Nota(int ID, String tipo_Pagamento, long idCliente) {
        this.ID = ID;
        this.Tipo_Pagamento = tipo_Pagamento;
        this.idCliente = idCliente;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTipo_Pagamento() {
        return Tipo_Pagamento;
    }

    public void setTipo_Pagamento(String tipo_Pagamento) {
        Tipo_Pagamento = tipo_Pagamento;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }
}
