package org.techtown.myapplication2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADD = "address";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    String txt;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        personList = new ArrayList<HashMap<String, String>>();
        getData("http://59.28.119.108:80/PHP_connection.php");

    }

    //JSON 들고옴
    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String uri = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    int i = 0;
                    while ((json = bufferedReader.readLine()) != null) {
                        i++;
                        sb.append(json + "\n");
                        System.out.println("sb: "+i+". "+sb.toString()+"\n");  //확인코드
                        System.out.println("json: "+i+". "+json+"\n");  //확인코드

                    }
                    //굳이 trim을 하는 이유는?
                    System.out.println("while 바깥: "+sb.toString().trim()+"\n");//확인코드
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                txt ="";
                try {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    peoples = jsonObj.getJSONArray(TAG_RESULTS);
                    for (int i = 0; i < peoples.length(); i++) {
                        JSONObject c = peoples.getJSONObject(i);
                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NAME);
                        String address = c.getString(TAG_ADD);

                        //확인코드
                        System.out.println("스트링 내용 확인"+id+name+address);

                        HashMap<String, String> persons = new HashMap<String, String>();
                        txt = txt + "@" + id + "@" + name + "@" + address + "\n";

                    }

                    ListAdapter adapter = new SimpleAdapter(
                            MainActivity.this, personList, R.layout.list_item,
                            new String[]{TAG_ID, TAG_NAME, TAG_ADD},
                            new int[]{R.id.id, R.id.name, R.id.address}
                    );
                    textView.setText(txt);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}