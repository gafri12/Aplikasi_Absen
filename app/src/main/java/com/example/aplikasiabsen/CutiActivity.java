package com.example.aplikasiabsen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CutiActivity extends AppCompatActivity {
    private EditText etStartDate, etEndDate, etReason;
    private Button btnSubmitCuti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuti);

        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        etReason = findViewById(R.id.etReason);
        btnSubmitCuti = findViewById(R.id.btnSubmitCuti);

        btnSubmitCuti.setOnClickListener(v -> submitCuti());
    }

    private void submitCuti() {
        String startDate = etStartDate.getText().toString().trim();
        String endDate = etEndDate.getText().toString().trim();
        String reason = etReason.getText().toString().trim();

        if (startDate.isEmpty() || endDate.isEmpty() || reason.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try (Connection conn = DatabaseHelper.connect()) {
                    String query = "INSERT INTO cuti (user_id, start_date, end_date, reason) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setInt(1, 1); // Ganti dengan user ID yang sebenarnya
                    stmt.setString(2, startDate);
                    stmt.setString(3, endDate);
                    stmt.setString(4, reason);
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
                    Toast.makeText(CutiActivity.this, "Cuti berhasil diajukan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CutiActivity.this, "Gagal mengajukan cuti", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
