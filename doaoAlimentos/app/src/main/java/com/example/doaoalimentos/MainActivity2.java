package com.example.doaoalimentos;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import android.content.Intent;


public class MainActivity2 extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private CategoriaExpandableAdapter adapter;
    private List<ItemDoacao> itensSelecionados = new ArrayList<>();
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        db = new DatabaseHelper(this);

        List<String> categorias = Arrays.asList("Cesta Básica", "Cesta Verde", "Itens de Limpeza", "Produtos Não Perecíveis");

        HashMap<String, List<String>> items = new HashMap<>();
        items.put("Cesta Básica", Arrays.asList("Cesta Básica"));
        items.put("Cesta Verde", Arrays.asList("Cesta Verde"));
        items.put("Itens de Limpeza", Arrays.asList("Álcool", "Detergente", "Desinfetante", "Limpa Vidros", "Água Sanitária", "Luvas", "Balde", "Sacos para Lixo"));
        items.put("Produtos Não Perecíveis", Arrays.asList("Milho", "Macarrão", "Soja", "Café", "Fubá", "Óleo", "Leite em Pó", "Achocolatado", "Massas", "Açúcar", "Farinha", "Feijão"));

        expandableListView = findViewById(R.id.recyclerViewCategorias);
        adapter = new CategoriaExpandableAdapter(this, categorias, items);
        expandableListView.setAdapter(adapter);

        Button btnConcluir = findViewById(R.id.btnNext);
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                //intent.putParcelableArrayListExtra("itensSelecionados", new ArrayList<>(itensSelecionados));
                startActivity(intent);
            }
        });

    }


    private void adicionarItem(String categoria, String nomeItem, int quantidade) {
        ItemDoacao item = new ItemDoacao(categoria, nomeItem, quantidade);
        itensSelecionados.add(item);
        db.adicionarItem(item);
        Toast.makeText(this, "Item adicionado: " + nomeItem, Toast.LENGTH_SHORT).show();
    }
}
