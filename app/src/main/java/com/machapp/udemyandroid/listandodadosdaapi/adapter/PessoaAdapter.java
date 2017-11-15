package com.machapp.udemyandroid.listandodadosdaapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.machapp.udemyandroid.listandodadosdaapi.R;
import com.machapp.udemyandroid.listandodadosdaapi.entity.Pessoa;

import java.util.ArrayList;

/**
 * Created by internet on 14/11/2017.
 */
public class PessoaAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private ArrayList<Pessoa> lstPessoas;
    private Context context;

    public PessoaAdapter(){}
    public PessoaAdapter(ArrayList<Pessoa> lstPessoas, Context context){
        this.lstPessoas = lstPessoas;
        this.context =context;
        this.mInflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return  this.lstPessoas.size();
    }

    @Override
    public Pessoa getItem(int position) {
        return this.lstPessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pessoa pessoa = lstPessoas.get(position);

        convertView =mInflater.inflate(R.layout.pessoa_layout,null);

        ((TextView) convertView.findViewById(R.id.textId)).setText(String.valueOf((pessoa.getId())));
        ((TextView) convertView.findViewById(R.id.textNome)).setText(pessoa.getNome());
        ((TextView) convertView.findViewById(R.id.textGenero)).setText(pessoa.getGender());

        return convertView;
    }
}
