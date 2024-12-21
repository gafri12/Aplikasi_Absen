package com.example.aplikasiabsen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class KetidakhadiranActivity extends AppCompatActivity {
    private EditText etIzinDate, etIzinReason;
    private Button btnSubmitIzin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izin);

        etIzinDate = findViewById(R.id.etIzinDate);
        etIzinReason = findViewById(R.id.etIzinReason);
        btnSubmitIzin = findViewById(R.id.btnSubmitIzin);

        btnSubmitIzin.setOnClickListener(v -> submitIzin());
    }

    private void submitIzin() {
        String izinDate = etIzinDate.getText().toString().trim();
        String izinReason = etIzinReason.getText().toString().trim();

        if (izinDate.isEmpty() || izinReason.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try (Connection conn = DatabaseHelper.connect()) {
                    String query = "INSERT INTO izin (user_id, izin_date, reason) VALUES (?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, 1); // Ganti dengan user ID yang sebenarnya
                    stmt.setString(2, izinDate);
                    stmt.setString(3, izinReason);
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
                    Toast.makeText(KetidakhadiranActivity.this, "Izin berhasil diajukan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(KetidakhadiranActivity.this, "Gagal mengajukan izin", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
