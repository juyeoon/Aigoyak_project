package org.techtown.AndroidStudioAigoyak;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;

public class A_Age extends AppCompatActivity {
    private static final String TAG = "A_Age";
    Context context;

    public static String age = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.age);

        ImageButton button = (ImageButton) findViewById(R.id.button_next);
        EditText age_text = (EditText) findViewById(R.id.age);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                age =  age_text.getText().toString();
                saveNote();//실험중
                Intent intent = new Intent(A_Age.this, A_Uniqueness.class);
                startActivity(intent);
            }
        });

    }
    //되는지 안 되는지 모름.
    private void saveNote(){
        String sql = "UPDATE "+NoteDatabase.TABLE_USER+ " SET FEATURE = '" +age+"'";

        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);

    }

}
