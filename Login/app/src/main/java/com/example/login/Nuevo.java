package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Nuevo extends AppCompatActivity {
    CircleImageView img; //Varibale de tipo imagen en Circulo
    TextView tv1, tv2; //Variable de tipo vista de texto
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        img = findViewById(R.id.img); //Se inicializa para conocer el id en el layaout
        tv1 = findViewById(R.id.tv1);//Se inicializa para conocer el id en el layaout
        tv2 = findViewById(R.id.tv2);//Se inicializa para conocer el id en el layaout
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //Se crea la variable para guardar el usuario existente
        if (user !=null) { //Se condiciona si es que el usuario existe o no
            String name = user.getDisplayName();//Se declara una variable para almacenar el nombre del usuario
            String gmail = user.getEmail(); // Se declara una variable para almacenar el correo del usuario

            tv1.setText("Usuario: " + name); //Se pasan los datos de nombre y gmail, para mosrarlos en los textView
            tv2.setText("Correo: " + gmail);
            Picasso.get().load(user.getPhotoUrl()).placeholder(R.drawable.ic_launcher_foreground).into(img); //Se obtiene la imagen del usuario
        }else{
            getApplicationContext();
        }
    }
    public void salir(View v){
        FirebaseAuth.getInstance().signOut(); //Permite la salida del usuariO
        finish();
    }


}