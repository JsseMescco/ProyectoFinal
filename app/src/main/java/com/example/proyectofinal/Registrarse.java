package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registrarse extends AppCompatActivity {
    Button Crear, Regresar;
    EditText email,contraseña, nombre, apellidos, telefono;
    FirebaseAuth fAuth; // firebase autenticacion
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        Crear = findViewById(R.id.btnCrear);
        Regresar = findViewById(R.id.btnRegresar);
        contraseña = findViewById(R.id.password);
        email = findViewById(R.id.etmail);
        nombre  = findViewById(R.id.Nombre);
        apellidos = findViewById(R.id.Apellidos);
        telefono = findViewById(R.id.Telefono);
        progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        Crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // el método trim sirve manipulación de cadenas (String), el cual sirve para quitar los espacios a la cadena
                String correo = email.getText().toString().trim();
                String pass = contraseña.getText().toString().trim();
                //validar los datos que se ingresaron
                if(TextUtils.isEmpty(correo)){
                    email.setError("Email es requerido");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    contraseña.setError("Password es requerido");
                    return;
                }
                //si es nenor a 6 caracteres
                if(pass.length()<6){
                    contraseña.setError("Password tiene que ser  >=6 caracteres");
                    return;
                }
                // se hara visible cuando el proceso de registro se esta realizando
                progressBar.setVisibility(view.VISIBLE);

                //REGISTRO DE USER EN FIREBASE
                // enviar datos firebase como (email - password) - oyente que nos confirme si el registro se realizo exitoso o no  <resultado autenticacion>
                fAuth.createUserWithEmailAndPassword(correo,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // si la tarea es exitosa
                        if(task.isSuccessful()){
                            Toast.makeText(Registrarse.this,"Usuario creado exitosamente",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            // caso contrario se produjo un error
                        }else{
                            Toast.makeText(Registrarse.this,
                                    " Se produjo un ERROR! usuario no creado" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            //
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        Crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}