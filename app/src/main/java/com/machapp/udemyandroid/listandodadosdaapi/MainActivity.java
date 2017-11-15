package com.machapp.udemyandroid.listandodadosdaapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.machapp.udemyandroid.listandodadosdaapi.entity.Pessoa;
import com.machapp.udemyandroid.listandodadosdaapi.task.ConsumirUrlTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public TextView txtRetorno;
    public ProgressBar progessBar;
    public ArrayList<Pessoa> lstPessoas;
    public String[] nomes;
    public ListView listv;
    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtRetorno = (TextView) findViewById(R.id.txtRetorno);
        progessBar = (ProgressBar) findViewById(R.id.progressBar);
        lstPessoas = new ArrayList<Pessoa>();
        nomes = new String [lstPessoas.size()];
        listv=(ListView)findViewById(R.id.listv);
    }
    public void getPessoas(View view){

        progessBar.setVisibility(View.VISIBLE);
        String url = "https://swapi.co/api/people";
        ConsumirUrlTask task = new ConsumirUrlTask(txtRetorno, progessBar,lstPessoas,nomes,listv,this);
        task.execute(url);
    }
}
