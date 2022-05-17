package com.example.practicageolocalizacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.practicageolocalizacion.databinding.ActivityMainBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;// Variable para la base de datos de Firebase
    DatabaseReference reference; //Variable de referencia a la base de datos
    private ActivityMainBinding binding; // Permite la vinculación de vista
    FusedLocationProviderClient ubicacion;//Varibale para almacenar la ubicación del usuario
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("usuario");
        binding.btnCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerUbicación();
            }//El escuchador del clic, al darse clic al boton este mandará a llamar al emto obtenerUbicacion
        });
    }

    private void obtenerUbicación(){
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){ //Al acceder no tiene permiso
            Toast.makeText(this, "Permiso Concedido", Toast.LENGTH_SHORT).show();//Mensaje de permiso
        }else{
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, 1); //Solicita los permisos, si no son concedidos
        }
        ubicacion = LocationServices.getFusedLocationProviderClient(MainActivity.this); //Se almacena la ubicación del usuario en la variable
        ubicacion.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!= null){ //Si se ha almacenada algo entrará a la condición
                    Double latitud = location.getLatitude(); //Se guarda la latitud de la coordenada
                    Double longitud = location.getLongitude(); //Se almacena la longitud de la coordenada.
                    binding.txtViewLatitude.setText("Latitud: "+latitud.toString()); //Por medio del binding, se envía el dato de latitud al
                    //textview correspondiente
                    binding.txtViewLongitud.setText("Longitud: "+longitud.toString());//Por medio del binding, se envía el dato de longitud al
                    //textview correspondiente
                    ObtenerDatos datos = new ObtenerDatos(latitud, longitud); //Dada una clase llamada ObtenerDatos, la cual contiene las variables
                    // longitud y latitud se crea un objeto.
                    reference.push().setValue(datos); //El objeto con dichas variables se envía a la base de datos.
                    Toast.makeText(MainActivity.this, "Ubicación agregada", Toast.LENGTH_SHORT).show();//Se envía
                }
            }
        });

    }
    private static class ObtenerDatos {
        /*
        Se crean dos variables de tipo Double de caraceter privado
        con sus respectivos metodos set y get*/
        private Double latitud;
        private Double longitud;

        public ObtenerDatos(Double latitud, Double longitud) {

            this.latitud = latitud;
            this.longitud = longitud;

        }

        public Double getLatitud() {
            return latitud;
        }

        public void setLatitud(Double latitud) {
            this.latitud = latitud;
        }

        public Double getLongitud() {
            return longitud;
        }

        public void setLongitud(Double longitud) {
            this.longitud= longitud;
        }


    }

}



