package test.androidtestc.DAO;

/**
 * Created by User on 05/06/2018.
 */

public class Cliente {
    private int ID;
    private String Nome;
    private String Estado;
    private String Cidade;
    private long CEP;
    private long CPF;
    private long id_usuario;

    public Cliente() {
    }

    public Cliente(int ID, String nome, String estado, String cidade, long CEP, long CPF, long id_usuario) {
        this.ID = ID;
        Nome = nome;
        Estado = estado;
        Cidade = cidade;
        this.CEP = CEP;
        this.CPF = CPF;
        this.id_usuario = id_usuario;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public long getCEP() {
        return CEP;
    }

    public void setCEP(long CEP) {
        this.CEP = CEP;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }
}
