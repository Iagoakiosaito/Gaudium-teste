package br.com.gaudium.entrega;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import org.json.JSONException;

import br.com.gaudium.entrega.model.PerfilEntregador;

public class ProfileActivity extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        Fazendo a chamada da API
        try {

            //Este if seria para requisitar acesso de internet ao usuário para requisitar a Api
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            }
            else{

                PerfilEntregador perfilEntregador = PerfilEntregador.retornaPerfilDoEntregador();

                // Definindo os campos com os valores da Api
                TextView nome = findViewById(R.id.nome_entregador);
                nome.setText(perfilEntregador.getNome());

                TextView cargo = findViewById(R.id.cargo_entregador);
                cargo.setText(perfilEntregador.getCargo());

                TextView descricao = findViewById(R.id.desc_entregador);
                descricao.setText(perfilEntregador.getDescricao());

//                TextView entregas = findViewById(R.id.entregas_feitas);
//                entregas.setText(perfilEntregador.getEntregas());

//                TextView saldo = findViewById(R.id.saldo_entregador);
//                saldo.setText(perfilEntregador.getSaldo());

//                TextView = findViewById(R.id.);
//                nome.setText(perfilEntregador.getNome());

            }

        } catch (JSONException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //carregar Perfil do entregador
                } else {
                    Toast.makeText(this, "Não vai funcionar!!!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}
