package com.example.memoappa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity2 extends AppCompatActivity {
    protected String fileName;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText=(EditText)findViewById(R.id.editText);
        fileName=getIntent().getStringExtra("FileName");//getIntent()でファイル名読み込み。"FileName"がString型だからgetStringExtra
        setTitle(fileName);//画面上部のタイトル欄にfileNameを表示。
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();

        if(id==R.id.action_save){
            Dialog dialog=new SaveDialog(this);
            dialog.show();
            return true;
        }
        else if(id==R.id.action_close){
            finish();//MainActivity2を閉じる。MainActivityが開く
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveData(){
        FileOutputStream fileOutputStream=null;
        BufferedWriter bufferedWriter=null;

        try{
            fileOutputStream=openFileOutput(fileName,MODE_PRIVATE);
            bufferedWriter=new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(editText.getText().toString());
            bufferedWriter.flush();
            bufferedWriter.close();
            setTitle(fileName);
            Toast.makeText(this, "保存しました", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(this, "保存できませんでした", Toast.LENGTH_LONG).show();
        }
    }
}

//以下は〜のとこ。メニューアイテムをタップした時の処理