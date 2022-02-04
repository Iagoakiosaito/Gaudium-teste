package br.com.gaudium.entrega.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;

public class PerfilEntregador {

    private String nome;
    private String cargo;
    private String descricao;
    private Array historico;

    public PerfilEntregador(String nome, String cargo, String descricao, Array historico){
        this.nome = nome;
        this.cargo = cargo;
        this.descricao = descricao;
        this.historico = historico;
    }

    public String getCargo() {
        return cargo;
    }

    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }

//    Lê os Bytes da request e transsforma em String
    private static String readStream(InputStream in){
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;

        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('n');
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return total.toString();
    }

//    Faz a requisição da Api
    private static String request(String stringUrl){
        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(stringUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        return "";
    }

    public static PerfilEntregador retornaPerfilDoEntregador()throws JSONException {

        String responseBody = request("https://dbgapi-desenv.taximachine.com.br/ps/perfil.php");
        JSONObject obj = new JSONObject(responseBody);
        String nome = obj.get("nome").toString();
        String cargo = obj.get("cargo").toString();
        String descricao = obj.get("descricao").toString();
        Array historico = (Array) obj.get("historico");

        return new PerfilEntregador(nome,cargo,descricao,historico);
    }

    /*COMO EU FARIA O ITEM 2:
    * Pegaria o array de histórico, iteraria cada índice para pegar assim a quantidade de entregas, o valor e cada nota individual
    * Com esses dados em mãos, eu simplesmente mandaria para a View a quantia de entregas(tamanho do array provavelmente funcionaria), faria a soma para cada valor, obtendo o saldo,
    * e faria a média para as notas*/

}
