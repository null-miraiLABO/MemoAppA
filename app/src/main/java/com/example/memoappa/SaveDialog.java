package com.example.memoappa;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class SaveDialog extends Dialog {
    private MainActivity2 parent;
    private EditText editText;

    public SaveDialog(@Nullable Context context){
        super(context);
        parent=(MainActivity2)context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle("ファイルの保存");
        setContentView(R.layout.dialog_save);

        editText=findViewById(R.id.editText2);
        editText.setText(parent.fileName);//parentは、MainActivity2

        View button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        View button2=findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.fileName=editText.getText().toString();
                parent.saveData();
                dismiss();
            }
        });

    }

}
