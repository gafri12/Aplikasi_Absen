package com.example.aplikasiabsen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RiwayatActivity extends AppCompatActivity {
    private ListView lvRiwayatAbsen;
    private Button btnRefreshRiwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        lvRiwayatAbsen = findViewById(R.id.lvRiwayatAbsen);
        btnRefreshRiwayat = findViewById(R.id.btnRefreshRiwayat);

        loadRiwayatAbsen();

        btnRefreshRiwayat.setOnClickListener(v -> loadRiwayatAbsen());
    }

    private void loadRiwayatAbsen() {
        new AsyncTask<Void, Void, ArrayList<String>>() {
            @Override
            protected ArrayList<String> doInBackground(Void... voids) {
                ArrayList<String> riwayat = new ArrayList<>();
                try (Connection conn = DatabaseHelper.connect()) {
                    Statement stmt = conn.createStatement();
                    String query = "SELECT date, status, latitude, longitude FROM absen WHERE user_id = 1"; // Ganti dengan user ID sebenarnya
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String entry = "Tanggal: " + rs.getString("date") +
                                "\nStatus: " + rs.getString("status") +
                                "\nLokasi: " + rs.getDouble("latitude") + ", " + rs.getDouble("longitude");
                        riwayat.add(entry);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return riwayat;
            }

            @Override
            protected void onPostExecute(ArrayList<String> riwayat) {
                if (riwayat.isEmpty()) {
                    Toast.makeText(RiwayatActivity.this, "Tidak ada data riwayat", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(RiwayatActivity.this, android.R.layout.simple_list_item_1, riwayat);
                    lvRiwayatAbsen.setAdapter(adapter);
                }
            }
        }.execute();
    }
}

