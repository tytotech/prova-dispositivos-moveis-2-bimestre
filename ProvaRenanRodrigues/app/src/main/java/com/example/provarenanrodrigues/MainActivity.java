package com.example.provarenanrodrigues;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //Criar banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS frases (id INTEGER PRIMARY KEY AUTOINCREMENT, frase VARCHAR)");

            //Inserir dados
            bancoDados.execSQL("INSERT INTO frases(frase) VALUES('A coragem é a primeira das qualidades humanas, pois garante todas as outras.')");
            bancoDados.execSQL("INSERT INTO frases(frase) VALUES('A persistência é o caminho do êxito.')");
            bancoDados.execSQL("INSERT INTO frases(frase) VALUES('O sucesso nasce do querer, da determinação e persistência em se chegar a um objetivo.')");
            bancoDados.execSQL("INSERT INTO frases(frase) VALUES('Talento é dom, é graça. E sucesso nada tem a ver com sorte, mas com determinação e trabalho.')");
            bancoDados.execSQL("INSERT INTO frases(frase) VALUES('Para ter um negócio de sucesso, alguém, algum dia, teve que tomar uma atitude de coragem.')");

            //Consulta com cursor
            String consulta = "SELECT id, frase FROM frases";

            //Criando Cursor
            Cursor cursor = bancoDados.rawQuery(consulta, null);

            //Criando índice
            int indiceID = cursor.getColumnIndex("id");
            int indiceFrase = cursor.getColumnIndex("frase");

            int quantidade = cursor.getCount();

            //Gerando registro aleatório
            Random random = new Random();
            int idAleatorio = random.nextInt(quantidade) + 1;

            //Consulta por ID
            String consultaID = "SELECT frase FROM frases WHERE id = " +idAleatorio;
            cursor = bancoDados.rawQuery(consultaID, null);
            cursor.moveToFirst();

            //Criando indice
            int indiceIDFrase = cursor.getColumnIndex("frase");
            String getFrase = cursor.getString(indiceIDFrase);

            //Apresentando resultado
            TextView textoFrases = findViewById(R.id.textoFrases);
            textoFrases.setText(getFrase);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
