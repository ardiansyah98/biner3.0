package com.ilkom.biner.biner;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.alicebot.ab.AIMLProcessor;
import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.PCAIMLProcessorExtension;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AskActivity extends AppCompatActivity {

    TextView jawabanNya;
    EditText pertanyaan;
    Button btnJawaban;

    private Bot bot;
    private Chat chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }


        jawabanNya = findViewById(R.id.textJawaban);
        pertanyaan = findViewById(R.id.editPertanyaan);
        btnJawaban = findViewById(R.id.btnTanya);

        final String namaPeople = getIntent().getStringExtra("intent_nama");
        final int umurPeople = getIntent().getIntExtra("intent_usia",0);

        btnJawaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pertanyaanNya = pertanyaan.getText().toString().trim().toLowerCase();
                //String pertanyaanNya = pertanyaan.getText().toString();

//                if (pertanyaanNya.equals("berapa umur anda?")){
//                    jawabanNya.setText("umur saya "+umurPeople+" tahun");
//                } else if (pertanyaanNya.equals("siapa nama anda?")){
//                    jawabanNya.setText("nama saya "+namaPeople+"!");
//                } else if (pertanyaanNya.isEmpty()){
//                    jawabanNya.setText("");
//                    Toast.makeText(AskActivity.this, "Tulis pertanyaan anda", Toast.LENGTH_SHORT).show();
//                } else {
//                    jawabanNya.setText("");
//                    Toast.makeText(AskActivity.this, "Pertanyaan tidak tersedia", Toast.LENGTH_SHORT).show();
//                }
                if (pertanyaanNya.isEmpty()){
                    String botResponse = chat.multisentenceRespond("hello");
                    jawabanNya.setText(botResponse);
                    //Toast.makeText(AskActivity.this, "ketikan halo", Toast.LENGTH_SHORT).show();
                } else {
                    String botResponse = chat.multisentenceRespond(pertanyaanNya);
                    jawabanNya.setText(botResponse);
                }
            }
        });
    }

    private void loadData() throws Exception {

        boolean sdCardAvailable = isSdCardAvailable();


        AssetManager assetManager = getResources().getAssets();
        File dir = new File(Environment.getExternalStorageDirectory().toString() + "/chatbot/bots/biner3");
        boolean makeDir = dir.mkdirs();
        if (dir.exists()) {

            try {
                for (String fileAsset : assetManager.list("chatbot")) {
                    File subdir = new File(dir.getPath() + "/" + fileAsset);
                    boolean subdirCheck = subdir.mkdirs();
                    for (String file : assetManager.list("chatbot/" + fileAsset)) {
                        File f = new File(dir.getPath() + "/" + fileAsset + "/" + file);
                        if (f.exists()) {
                            continue;
                        }
                        InputStream in = null;
                        OutputStream out = null;
                        in = assetManager.open("chatbot/" + fileAsset + "/" + file);
                        out = new FileOutputStream(dir.getPath() + "/" + fileAsset + "/" + file);


                        copyFile(in, out);
                        in.close();
                        in = null;
                        out.flush();
                        out.close();
                        out = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        MagicStrings.root_path = Environment.getExternalStorageDirectory().toString() + "/chatbot";
        System.out.println("working directory: " + MagicStrings.root_path);
        AIMLProcessor.extension = new PCAIMLProcessorExtension();


        bot = new Bot("biner3", MagicStrings.root_path, "chat");
        chat = new Chat(bot);


    }

    private boolean isSdCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ? true : false;
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException{
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}
