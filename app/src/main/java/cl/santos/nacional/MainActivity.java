package cl.santos.nacional;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button botonregistro = findViewById(R.id.registro);
        Button botoniniciarsesion = findViewById(R.id.btninicio);
        Button botonInvitado = findViewById(R.id.btnInvitado);

        botonregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText correoEditText = findViewById(R.id.correo);
                EditText contraseñaEditText = findViewById(R.id.contraseña);

                String correo = correoEditText.getText().toString();
                String contraseña = contraseñaEditText.getText().toString();

                if (!correo.isEmpty() && !contraseña.isEmpty()) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(MainActivity.this, Inicio.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(MainActivity.this, "Registro fallido: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(MainActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botoniniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText correoEditText = findViewById(R.id.correo);
                EditText contraseñaEditText = findViewById(R.id.contraseña);

                String correo = correoEditText.getText().toString();
                String contraseña = contraseñaEditText.getText().toString();

                if (!correo.isEmpty() && !contraseña.isEmpty()) {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(MainActivity.this, Inicio.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(MainActivity.this, "Inicio de sesión fallido: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(MainActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        botonInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Inicio.class);
                startActivity(intent);
            }
        });
    }
}
