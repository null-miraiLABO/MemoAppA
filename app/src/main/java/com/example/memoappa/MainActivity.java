package com.example.memoappa;//パッケージネーム

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //フィールドとして定義変数名は適当
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//スーパークラスにあるonCreateメゾットを呼び出す
        setContentView(R.layout.activity_main);//activity_mainの表示
    }

    @Override
    //onCreateではなくonStartに書くことで2画面目から戻ったときに再び実行できる
    //詳しくは画面推移PDFを参照
    protected void onStart(){
        super.onStart();
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView listView = (ListView) adapterView;
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("FileName", (String) listView.getItemAtPosition(i));
                startActivity(intent);
            }
        });
        lodeList();
    }
    private void lodeList(){
        //arrayにどんどん新しく作ったファイルを保存する。
        ArrayList<String> array=new ArrayList();
        array.add("新しいファイル");
        try{
            File file=new File("data/data/"+getPackageName()+"/files");//getPackageName()、このパッケージのみ
            for(String name:file.list()){
                if (!name.equals("instant-run")){
                    array.add(name);
                }
            }
        }
        catch (Exception e){}

        String[] strs=new String[array.size()];
        for (int i=0;i<strs.length;i++)strs[i]=array.get(i);
        ArrayAdapter<String>ad =new ArrayAdapter(this,android.R.layout.simple_list_item_1,strs);
        listView.setAdapter(ad);
        registerForContextMenu(listView);

    }
}