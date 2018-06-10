package test.androidtestc.Util;

/**
 * Created by User on 13/09/2017.
 */

public class BancoUtil {
    //Tabela de Cliente
    public static final String NOME_BANCO = "testc.db";
    public static final String TABELA_CLIENTE = "Cliente";
    public static final String ID_CLIENTE = "_id";
    public static final String NOME_CLIENTE = "nome";
    public static final String ESTADO_CLIENTE = "estado";
    public static final String CIDADE_CLIENTE = "cidade";
    public static final String CEP_CLIENTE = "cep";
    public static final String CPF_CLIENTE = "cpf";
    public static final String CLIENTE_USUARIO = "_idUsuario";

    //Criando Tabela Usuario
    public static final String TABELA_USUARIO = "usuario";
    public static final String ID_USUARIO = "_id";
    public static final String LOGIN_USUARIO = "login";
    public static final String SENHA_USUARIO = "senha";

    //Tabela do Produto Nota

    public static final String TABELA_PRODUTO_NOTA = "Produto_Nota";
    public static final String ID_PRODUTO_NOTA = "Id_Produto_Nota";
    public static final String PRODUTO_PRODUTO = "Produto";
    public static final String NUMERO_NOTA = "Num_nota";
    public static final String QUANTIDADE = "Qtd_Produto";
    public static final String VALOR = "Vlr_Produto";

    //Tabela Nota
    public static final String TABELA_NOTA = "Nota";
    public static final String ID_NOTA = "_id";
    public static final String CLIENTE_NOTA = "_idCliente";
    public static final String TIPO_PAGAMENTO = "Tipo_Pagamento";

    public static final int VERSAO = 4;
}
