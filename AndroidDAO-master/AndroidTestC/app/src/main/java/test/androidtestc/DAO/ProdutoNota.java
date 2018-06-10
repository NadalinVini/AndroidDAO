package test.androidtestc.DAO;

import android.icu.math.BigDecimal;

/**
 * Created by User on 10/06/2018.
 */

public class ProdutoNota {
    private int ID;
    private String Produto;
    private long ID_Nota;
    private long Quantidade;
    private Double Preco;

    public ProdutoNota() {
    }

    public ProdutoNota(int ID, String produto, long ID_Nota, long quantidade, Double preco) {
        this.ID = ID;
        Produto = produto;
        this.ID_Nota = ID_Nota;
        Quantidade = quantidade;
        Preco = preco;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProduto() {
        return Produto;
    }

    public void setProduto(String produto) {
        Produto = produto;
    }

    public long getID_Nota() {
        return ID_Nota;
    }

    public void setID_Nota(long ID_Nota) {
        this.ID_Nota = ID_Nota;
    }

    public long getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(long quantidade) {
        Quantidade = quantidade;
    }

    public Double getPreco() {
        return Preco;
    }

    public void setPreco(Double preco) {
        Preco = preco;
    }
}
