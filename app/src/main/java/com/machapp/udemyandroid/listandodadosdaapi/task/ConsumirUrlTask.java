package com.machapp.udemyandroid.listandodadosdaapi.task;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.machapp.udemyandroid.listandodadosdaapi.adapter.PessoaAdapter;
import com.machapp.udemyandroid.listandodadosdaapi.entity.Pessoa;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by internet on 17/10/2017.
 */
public class ConsumirUrlTask extends AsyncTask<String,Void,String> {

    public TextView txtRetorno;
    public ProgressBar progressBar;
    public ArrayList<Pessoa> lstPessoas;
    public String[] nomes;
    public ListView listv;
    public Context context;

    public ConsumirUrlTask(TextView txtRetorno, ProgressBar progressBar, ArrayList<Pessoa>lstPessoas, String[] nomes, ListView listv, Context context){
        this.txtRetorno = txtRetorno;
        this.progressBar = progressBar;
        this.lstPessoas = lstPessoas;
        this.nomes = nomes;
        this.listv = listv;
        this.context = context;


    }



    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String result="";

        try {
            //conexão
            URL myUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(10000); // tempo
            connection.setConnectTimeout(10000); // tempo para sair da requisição caso não haja resposta
            connection.connect();

            //lendo os dados de uma conexão ativa
            String inputLine="";
            InputStreamReader streamReader = new InputStreamReader((connection.getInputStream()));
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((inputLine = bufferedReader.readLine()) != null){ // irá ler até que chegue ao fim.
                stringBuilder.append(inputLine);
            }

            streamReader.close();
            bufferedReader.close();

            result = stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //txtRetorno.setText(s);
        progressBar.setVisibility(View.INVISIBLE);

        try {
            JSONObject reader = new JSONObject(s);
            JSONArray arrayPessoas = reader.getJSONArray("results");
            int total = arrayPessoas.length();

            for(int i=0; i<total ; i++){
                JSONObject obj = arrayPessoas.getJSONObject(i);
                Pessoa p = new Pessoa();
                p.setNome(obj.getString("name"));
                p.setHair_color(obj.getString("hair_color"));
                p.setSkin_color(obj.getString("skin_color"));
                p.setEye_color(obj.getString("eye_color"));
                p.setGender(obj.getString("gender"));
                p.setId(i);
                lstPessoas.add(p);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //StringBuilder sb = new StringBuilder();
        nomes = new String[lstPessoas.size()];
        int i=0;
        for(Pessoa p:lstPessoas){
            //sb.append(p.getNome());
            //sb.append("\n");
            nomes[i] = p.getNome();
            i++;
        }
        //txtRetorno.setText(sb.toString());
        atualizarListView();
    }

    private void atualizarListView() {
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
        android.R.layout.simple_list_item_1,android.R.id.text1,nomes);*/

        PessoaAdapter adapter = new PessoaAdapter(this.lstPessoas,this.context);
        this.listv.setAdapter(adapter);
    }
}
