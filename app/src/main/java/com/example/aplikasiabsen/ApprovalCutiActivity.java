package com.example.aplikasiabsen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ApprovalCutiActivity extends AppCompatActivity {
    private ListView lvApprovalCuti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_cuti);

        lvApprovalCuti = findViewById(R.id.lvApprovalCuti);
        loadApprovalCuti();
    }

    private void loadApprovalCuti() {
        new AsyncTask<Void, Void, ArrayList<String>>() {
            @Override
            protected ArrayList<String> doInBackground(Void... voids) {
                ArrayList<String> cutiList = new ArrayList<>();
                try (Connection conn = DatabaseHelper.connect()) {
                    Statement stmt = conn.createStatement();
                    String query = "SELECT * FROM cuti WHERE status = 'Pending'";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String entry = "ID: " + rs.getInt("id") +
                                "\nUser ID: " + rs.getInt("user_id") +
                                "\nAlasan: " + rs.getString("reason") +
                                "\nTanggal: " + rs.getString("start_date") + " - " + rs.getString("end_date");
                        cutiList.add(entry);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return cutiList;
            }

            @Override
            protected void onPostExecute(ArrayList<String> cutiList) {
                if (cutiList.isEmpty()) {
                    Toast.makeText(ApprovalCutiActivity.this, "Tidak ada pengajuan cuti", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ApprovalCutiActivity.this, android.R.layout.simple_list_item_1, cutiList);
                    lvApprovalCuti.setAdapter(adapter);
                }
            }
        }.execute();
    }
}

