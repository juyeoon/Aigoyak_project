package org.techtown.AndroidStudioAigoyak;

        import android.content.Context;
        import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.Button;
        import android.widget.Toast;

public class A_Uniqueness extends AppCompatActivity {
    private static final String TAG = "A_uniqueness";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uniqueness);


        ImageButton button_finish = (ImageButton) findViewById(R.id.button_finish);
        button_finish.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),"환영합니다!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(A_Uniqueness.this, MainActivity.class);
                startActivity(intent);

            }
        });

        //버튼 개수마다 추가될 예정=====================================================================================================================
        Button button1 = (Button) findViewById(R.id.MAO);
        String text1 = button1.getText().toString();

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button1.isSelected()){
                    button1.setSelected(false);
                    deleteNote(text1);
                }
                else{
                    button1.setSelected(true);
                    saveNote(text1);
                }
            }
        });

        Button button2 = (Button) findViewById(R.id.과민증);
        String text2 = button2.getText().toString();

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button2.isSelected()){
                    button2.setSelected(false);
                    deleteNote(text2);
                }
                else{
                    button2.setSelected(true);
                    saveNote(text2);
                }
            }
        });

        Button button3 = (Button) findViewById(R.id.피부염);
        String text3 = button3.getText().toString();

        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button3.isSelected()){
                    button3.setSelected(false);
                    deleteNote(text3);
                }
                else{
                    button3.setSelected(true);
                    saveNote(text3);
                }
            }
        });

        Button button4 = (Button) findViewById(R.id.녹내장);
        String text4 = button4.getText().toString();

        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button4.isSelected()){
                    button4.setSelected(false);
                    deleteNote(text4);
                }
                else{
                    button4.setSelected(true);
                    saveNote(text4);
                }
            }
        });
        Button button5 = (Button) findViewById(R.id.당뇨);
        String text5 = button5.getText().toString();

        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button5.isSelected()){
                    button5.setSelected(false);
                    deleteNote(text5);
                }
                else{
                    button5.setSelected(true);
                    saveNote(text5);
                }
            }
        });

        Button button6 = (Button) findViewById(R.id.두드러기);
        String text6 = button6.getText().toString();

        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button6.isSelected()){
                    button6.setSelected(false);
                    deleteNote(text6);
                }
                else{
                    button6.setSelected(true);
                    saveNote(text6);
                }
            }
        });

        Button button7 = (Button) findViewById(R.id.배뇨);
        String text7 = button7.getText().toString();

        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button7.isSelected()){
                    button7.setSelected(false);
                    deleteNote(text7);
                }
                else{
                    button7.setSelected(true);
                    saveNote(text7);
                }
            }
        });

        Button button8 = (Button) findViewById(R.id.부정맥);
        String text8 = button8.getText().toString();

        button8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button8.isSelected()){
                    button8.setSelected(false);
                    deleteNote(text8);
                }
                else{
                    button8.setSelected(true);
                    saveNote(text8);
                }
            }
        });

        Button button9 = (Button) findViewById(R.id.소화성궤양);
        String text9 = button9.getText().toString();

        button9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button9.isSelected()){
                    button9.setSelected(false);
                    deleteNote(text9);
                }
                else{
                    button9.setSelected(true);
                    saveNote(text9);
                }
            }
        });

        Button button10 = (Button) findViewById(R.id.신장);
        String text10 = button10.getText().toString();

        button10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button10.isSelected()){
                    button10.setSelected(false);
                    deleteNote(text10);
                }
                else{
                    button10.setSelected(true);
                    saveNote(text10);
                }
            }
        });

        Button button11 = (Button) findViewById(R.id.심장);
        String text11 = button11.getText().toString();

        button11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button11.isSelected()){
                    button11.setSelected(false);
                    deleteNote(text11);
                }
                else{
                    button11.setSelected(true);
                    saveNote(text11);
                }
            }
        });

        Button button12 = (Button) findViewById(R.id.알레르기);
        String text12 = button12.getText().toString();

        button12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button12.isSelected()){
                    button12.setSelected(false);
                    deleteNote(text12);
                }
                else{
                    button12.setSelected(true);
                    saveNote(text12);
                }
            }
        });

        Button button13 = (Button) findViewById(R.id.유당분해효소결핍증);
        String text13 = button13.getText().toString();

        button13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button13.isSelected()){
                    button13.setSelected(false);
                    deleteNote(text13);
                }
                else{
                    button13.setSelected(true);
                    saveNote(text13);
                }
            }
        });

        Button button14 = (Button) findViewById(R.id.위장);
        String text14 = button14.getText().toString();

        button14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button14.isSelected()){
                    button14.setSelected(false);
                    deleteNote(text14);
                }
                else{
                    button14.setSelected(true);
                    saveNote(text14);
                }
            }
        });

        Button button15 = (Button) findViewById(R.id.임부);
        String text15 = button15.getText().toString();

        button15.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button15.isSelected()){
                    button15.setSelected(false);
                    deleteNote(text15);
                }
                else{
                    button15.setSelected(true);
                    saveNote(text15);
                }
            }
        });

        Button button16 = (Button) findViewById(R.id.천식);
        String text16 = button16.getText().toString();

        button16.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button16.isSelected()){
                    button16.setSelected(false);
                    deleteNote(text16);
                }
                else{
                    button16.setSelected(true);
                    saveNote(text16);
                }
            }
        });

        Button button17 = (Button) findViewById(R.id.혈액응고);
        String text17 = button17.getText().toString();

        button17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button17.isSelected()){
                    button17.setSelected(false);
                    deleteNote(text17);
                }
                else{
                    button17.setSelected(true);
                    saveNote(text17);
                }
            }
        });

        Button button18 = (Button) findViewById(R.id.피부감염증);
        String text18 = button18.getText().toString();

        button18.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button18.isSelected()){
                    button18.setSelected(false);
                    deleteNote(text18);
                }
                else{
                    button18.setSelected(true);
                    saveNote(text18);
                }
            }
        });

        Button button19 = (Button) findViewById(R.id.혈압);
        String text19 = button19.getText().toString();

        button19.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button19.isSelected()){
                    button19.setSelected(false);
                    deleteNote(text19);
                }
                else{
                    button19.setSelected(true);
                    saveNote(text19);
                }
            }
        });

        Button button20 = (Button) findViewById(R.id.추가등록);


        button20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!button20.getText().toString().equals("추가등록")){//키워드가 추가되어있을 때 실행
                    if(button20.isSelected()){
                        String text20 = button20.getText().toString();
                        button20.setSelected(false);
                        deleteNote(text20);
                    }
                    else{
                        String text20 = button20.getText().toString();
                        button20.setSelected(true);
                        saveNote(text20);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"키워드를 추가하고 시도해주세요.", Toast.LENGTH_LONG).show();

                }

            }
        });

        ///키워드 추가
        Button add = (Button) findViewById(R.id.add);
        EditText keyword = (EditText) findViewById(R.id.keyword);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(button20.getText().toString().equals("추가등록")){
                    button20.setText(keyword.getText().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(),"키워드를 삭제하고 시도해주세요.", Toast.LENGTH_LONG).show();
                }

            }
        });

        ///키워드 삭제
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!button20.getText().toString().equals("추가등록")) {
                    String text20 = button20.getText().toString();
                    deleteNote(text20);
                    button20.setSelected(false);
                    button20.setText("추가등록");
                }
            }
        });
    }


    private void saveNote(String name){//db에 저장
        String feature = name;

        String sql = "insert into " +NoteDatabase.TABLE_USER + "(FEATURE) values (" + "'"+ feature + "')";

        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);

    }

    private void deleteNote(String name){//db에서 삭제
        String feature = name;//임의로 지정

        String sql = "delete from "+NoteDatabase.TABLE_USER+ " where feature = '" + feature + "'";
        Log.d(TAG, "sql : " + sql);
        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sql);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
