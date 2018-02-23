package com.ilkom.biner.biner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AskActivity extends AppCompatActivity {

    TextView jawabanNya;
    EditText pertanyaan;
    Button btnJawaban;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        jawabanNya = findViewById(R.id.textJawaban);
        pertanyaan = findViewById(R.id.editPertanyaan);
        btnJawaban = findViewById(R.id.btnTanya);

        final String namaPeople = getIntent().getStringExtra("intent_nama");
        final int umurPeople = getIntent().getIntExtra("intent_usia",0);

        btnJawaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pertanyaanNya = pertanyaan.getText().toString().trim().toLowerCase();

                if (pertanyaanNya.equals("berapa umur anda?")){
                    jawabanNya.setText("umur saya "+umurPeople+" tahun");
                } else if (pertanyaanNya.equals("siapa nama anda?")){
                    jawabanNya.setText("nama saya "+namaPeople+"!");
                } else if (pertanyaanNya.isEmpty()){
                    jawabanNya.setText("");
                    Toast.makeText(AskActivity.this, "Tulis pertanyaan anda", Toast.LENGTH_SHORT).show();
                } else {
                    jawabanNya.setText("");
                    Toast.makeText(AskActivity.this, "Pertanyaan tidak tersedia", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
