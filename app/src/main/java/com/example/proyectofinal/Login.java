package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView tvRegistro;
    EditText Email, Contraseña;
    FirebaseAuth fAuth; // firebase autenticacion
    Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvRegistro = findViewById(R.id.crearText);
        Email = findViewById(R.id.etmail);
        Contraseña = findViewById(R.id.password);
        Login = findViewById(R.id.btnLogin);
        fAuth = FirebaseAuth.getInstance(); // recibiendo la isntancia para realizar todas las operaciones

        if(fAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}