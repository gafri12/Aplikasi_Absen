package com.example.aplikasiabsen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAbsen = findViewById(R.id.btn_absensi);
        Button btnCuti = findViewById(R.id.btn_Cuti);
        Button btnKetidakhadiran = findViewById(R.id.btn_tidakHadir);
        Button btnApproval = findViewById(R.id.btn_Approval);
        Button btnRiwayat = findViewById(R.id.btn_Riwayat);

//        btnAbsen.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent i = new Intent(MainActivity.this,AbsenActivity.class);
//                        startActivity(i);
//                    }
//                }
//        );
        btnAbsen.setOnClickListener(v -> {
            Log.d("MainActivity","Button clicked, launching AbsenActivity");
            startActivity(new Intent(MainActivity.this, AbsenActivity.class));
        });
        btnCuti.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CutiActivity.class)));
        btnKetidakhadiran.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, KetidakhadiranActivity.class)));
        btnApproval.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ApprovalCutiActivity.class)));
        btnRiwayat.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RiwayatActivity.class)));
    }
}


//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//public class MainActivity extends AppCompatActivity {
//
//    EditText username,password;
//    Button btnLogin;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        username = (EditText) findViewById(R.id.username);
//        password = (EditText) findViewById(R.id.password);
//        btnLogin = (Button) findViewById(R.id.btnLogin);
//
//        btnLogin.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                String usernameKey = username.getText().toString();
//                String passwordKey = password.getText().toString();
//
//                if(usernameKey.equals("admin")&&passwordKey.equals("123")){
//                    Toast.makeText(getApplicationContext(),"LOGIN SUKSES",
//                            Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(MainActivity.this,welcome.class);
//                    MainActivity.this.startActivity(intent);
//                    finish();
//                }else{
//                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                    builder.setMessage("Username atau Password Anda salah!")
//                            .setNegativeButton("Retry",null).create().show();
//                }
//            }
//        });
//    }
//}