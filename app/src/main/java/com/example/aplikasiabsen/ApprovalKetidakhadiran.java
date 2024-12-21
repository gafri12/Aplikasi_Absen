package com.example.aplikasiabsen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ApprovalKetidakhadiran extends AppCompatActivity {
    private ListView lvApprovalIzin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_ketidakhadiran);

        lvApprovalIzin = findViewById(R.id.lvApprovalIzin);
        loadApprovalIzin();
    }

    private void loadApprovalIzin() {
        new AsyncTask<Void, Void, ArrayList<String>>() {
            @Override
            protected ArrayList<String> doInBackground(Void... voids) {
                ArrayList<String> izinList = new ArrayList<>();
                try (Connection conn = DatabaseHelper.connect()) {
                    Statement stmt = conn.createStatement();
                    String query = "SELECT * FROM izin WHERE status = 'Pending'";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        String entry = "ID: " + rs.getInt("id") +
                                "\nUser ID: " + rs.getInt("user_id") +
                                "\nTanggal: " + rs.getString("izin_date") +
                                "\nAlasan: " + rs.getString("reason");
                        izinList.add(entry);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return izinList;
            }

            @Override
            protected void onPostExecute(ArrayList<String> izinList) {
                if (izinList.isEmpty()) {
                    Toast.makeText(ApprovalKetidakhadiran.this, "Tidak ada permintaan izin", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ApprovalKetidakhadiran.this, android.R.layout.simple_list_item_1, izinList);
                    lvApprovalIzin.setAdapter(adapter);
                }
            }
        }.execute();
    }

    // Metode untuk menyetujui permintaan izin
    private void approveIzin(int izinId) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try (Connection conn = DatabaseHelper.connect()) {
                    String query = "UPDATE izin SET status = 'Disetujui' WHERE id = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, izinId);
                    stmt.executeUpdate();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if (success) {
                    Toast.makeText(ApprovalKetidakhadiran.this, "Izin berhasil disetujui", Toast.LENGTH_SHORT).show();
                    loadApprovalIzin(); // Refresh daftar
                } else {
                    Toast.makeText(ApprovalKetidakhadiran.this, "Gagal menyetujui izin", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    // Metode untuk menolak permintaan izin
    private void rejectIzin(int izinId) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try (Connection conn = DatabaseHelper.connect()) {
                    String query = "UPDATE izin SET status = 'Ditolak' WHERE id = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, izinId);
                    stmt.executeUpdate();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if (success) {
                    Toast.makeText(ApprovalKetidakhadiran.this, "Izin berhasil ditolak", Toast.LENGTH_SHORT).show();
                    loadApprovalIzin(); // Refresh daftar
                } else {
                    Toast.makeText(ApprovalKetidakhadiran.this, "Gagal menolak izin", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
