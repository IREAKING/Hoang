package com.example.aphelios;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DownloadDataTask().execute("https://hoangngoctriet-bo-game-s.azurewebsites.net/api/Student/GetAllStudents");
    }

    private class DownloadDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                result = stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            ArrayList<Student> students = parseJsonArray(result);

            StudentAdapter adapter = new StudentAdapter(MainActivity.this, students);

            ListView listView = findViewById(R.id.listview);

            // Đặt Adapter cho ListView
            listView.setAdapter(adapter);
        }
    }

    private ArrayList<Student> parseJsonArray(String jsonArray) {
        ArrayList<Student> students = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(jsonArray);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int id = object.getInt("id");
                String hinhAnh= object.getString("hinhAnh");
                String ten = object.getString("ten");
                String quequan = object.getString("queQuan");
                int tuoi = object.getInt("tuoi");
                Student student = new Student(id, hinhAnh, ten, quequan, tuoi);
                students.add(student);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return students;
    }
}
