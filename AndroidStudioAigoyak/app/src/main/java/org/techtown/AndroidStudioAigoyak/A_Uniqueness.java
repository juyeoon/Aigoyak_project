package org.techtown.AndroidStudioAigoyak;

        import android.content.Context;
        import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.util.Log;
        import android.view.View;
        import android.widget.ImageButton;

public class A_Uniqueness extends AppCompatActivity {
    private static final String TAG = "A_uniqueness";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uniqueness);

        ImageButton button = (ImageButton) findViewById(R.id.button_finish);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                saveNote();//실험중
                Intent intent = new Intent(A_Uniqueness.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    //되는지 안 되는지 모름.
    private void saveNote(){
        String feature = "피곤함";//임의로 지정

        String sql = "insert into " +NoteDatabase.TABLE_USER +//이거 바꾸다 말았음 이건 했는데 나중에 다른거 고치기
                "(FEATURE) values (" +
                "'"+ feature + "')";



        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);

    }
}


