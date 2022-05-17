package com.example.calificacion;

import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    EditText nota1,nota2, nota3, nota4, nota5;
    Button btnCalcPro;
    TextView rendimiento, average;
    //Se crean las variables que permitiran controlar la información de los editText, Button y TextView; respectivamente
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nota1 = findViewById(R.id.textNota1);
        nota2 = findViewById(R.id.textNota2);
        nota3 = findViewById(R.id.textNota3);
        nota4 = findViewById(R.id.textNota4);
        nota5 = findViewById(R.id.textNota5);
        //Se asignan los valores de los EditText
        rendimiento = findViewById(R.id.txtViewDesempeño);
        average = findViewById(R.id.txtViewPromedio);
        //Se asignan los valores a los TextView.
        btnCalcPro = findViewById(R.id.btnCalcularPromedio);
        //Se asigna el estado del boton a la variable btnCalcPro.
    }



//Se crea el metodo calcularPrmedio
    public void calcularPromedio(View v) {
        /*
        Se comienza por evaluar si los EditText están vacios por medio de la llamada al metodo evaluarVacio
        si se cumple que no haya datos vacios, se evalua que las notas ingresadas esten en el rango apropiado entre 0 y 20.
        * */
        if (evaluarVacio()){
            if(evaluarNota()) {
                float c1 = Float.parseFloat(nota1.getText().toString());
                float c2 = Float.parseFloat(nota2.getText().toString());
                float c3 = Float.parseFloat(nota3.getText().toString());
                float c4 = Float.parseFloat(nota4.getText().toString());
                float c5 = Float.parseFloat(nota5.getText().toString());
                //Se crean las variables que tomaran los valores ingresados, para posteriormente sumarlas y obtener el promedio.
                float promedio;
                promedio = (c1 + c2 + c3 + c4 + c5) / 5f;
                average.setText(String.valueOf(promedio));
                //Se muestra el promedio del alumno
                //Posteriormente se evalua si el rendimiento fue de Malo a Excelente.
                if (promedio >= 0 & promedio <= 10.5) {
                    rendimiento.setText("Malo");
                } else if (promedio > 10.5 & promedio <= 14) {
                    rendimiento.setText("Regular");
                } else if (promedio > 14 & promedio <= 19) {
                    rendimiento.setText("Bueno");
                } else {
                    rendimiento.setText("Excelente");
                }

            }
        }

    }

    //Este metodo permite evaluar si los EditText estan o no vacios, y es de tipo booleano
    public boolean evaluarVacio(){
        String primerNota = nota1.getText().toString();
        String segundaNota = nota2.getText().toString();
        String tercerNota = nota3.getText().toString();
        String cuartaNota = nota4.getText().toString();
        String quintaNota = nota5.getText().toString();
        //Se asigna en variables los valores que tengan las variables notas (Previamente declaradas para conocer lo que tienen los EditText)

        boolean validacion = true; //Se crea la variable validacion con valor verdadero por defecto.

        /* En las lineas siguientes se evalua por medio de condiciones si es que los valores son vacíos
        de ser así se manda un error de que el dato es requerido y la variable validación se asigna como falsa.
         */
        if (primerNota.isEmpty()){
            nota1.setError("El dato es requerido");
            validacion =false;
        }
        if (segundaNota.isEmpty()){
            nota2.setError("El dato es requerido");
            validacion =false;
        }
        if (tercerNota.isEmpty()){
            nota3.setError("El dato es requerido");
            validacion =false;
        }
        if (cuartaNota.isEmpty()){
            nota4.setError("El dato es requerido");
            validacion =false;
        }
        if (quintaNota.isEmpty()){
            nota5.setError("El dato es requerido");
            validacion =false;
        }

        return validacion; //Se retorna si validacion es verdadera o falsa segun sea el caso de que esten vacios o no las notas.
    }

    public boolean evaluarNota(){
        float cal1 = Float.parseFloat(nota1.getText().toString());
        float cal2 = Float.parseFloat(nota2.getText().toString());
        float cal3 = Float.parseFloat(nota3.getText().toString());
        float cal4 = Float.parseFloat(nota4.getText().toString());
        float cal5 = Float.parseFloat(nota5.getText().toString());
        //Se asigna en variables los valores que tengan las variables notas (Previamente declaradas para conocer lo que tienen los EditText)
        //y se parsean en entero para posteriormente evaluarlos.

        boolean validacion = true;//Se crea la variable validacion con valor verdadero por defecto.

        /*
        Ahora se procede a evaluar si cada una  de las calificaciones estan dentro del rango de 0 a 20, de no ser así
        se manda un error de "la nota no es valida", y se asigna como falsa la validación, y así no hacer el calculo de las notas.
         */
        if (cal1<0 || cal1 >20){
            nota1.setError("La nota no es valida");
            validacion =false;
        }
        if (cal2<0 || cal2>20) {
            nota2.setError("La nota no es valida");
            validacion =false;

        }
        if (cal3<0 || cal3>20){
            nota3.setError("La nota no es valida");
            validacion =false;
        }
        if (cal4<0 || cal4>20){
            nota4.setError("La nota no es valida");
            validacion =false;
        }
        if (cal5<0 || cal5>20){
            nota5.setError("La nota no es valida");
            validacion =false;
        }
        return validacion;//Se retorna el valor de la validación de acuerdo a si cada nota fue o no correcta.
    }


}