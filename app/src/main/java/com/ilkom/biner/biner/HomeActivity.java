package com.ilkom.biner.biner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ArrayList<People> peoples = new ArrayList<>();
        peoples.add(new People("Afghan", 18));
        peoples.add(new People("Budi", 15));
        peoples.add(new People("Ikram", 20));
        peoples.add(new People("Ryo", 25));
        peoples.add(new People("Juan", 18));

        int size = peoples.size();

        String[] array_nama = new String[size];

        for (int i = 0; i < size; i++) {
            array_nama[i] = peoples.get(i).getNama();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.label, array_nama);
        ListView listView = findViewById(R.id.listNama);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, AskActivity.class);
                intent.putExtra("intent_usia", peoples.get(position).getUsia());
                intent.putExtra("intent_nama", peoples.get(position).getNama());
                startActivity(intent);
            }
        });
    }
}
