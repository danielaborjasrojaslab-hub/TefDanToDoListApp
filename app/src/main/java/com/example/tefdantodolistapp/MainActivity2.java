// =======================================================
// PAQUETE DE LA APLICACIÓN
// =======================================================
package com.example.tefdantodolistapp;


// =======================================================
// IMPORTACIONES NECESARIAS
// =======================================================

// Permite crear Activities compatibles con versiones modernas de Android
import androidx.appcompat.app.AppCompatActivity;

// Permite usar el método onCreate y manejar el ciclo de vida de la Activity
import android.os.Bundle;

// Importa los componentes visuales (ListView, Button, etc.)
import android.widget.*;

// Permite trabajar con listas dinámicas
import java.util.ArrayList;



// =======================================================
// CLASE DE LA SEGUNDA PANTALLA (ACTIVITY 2)
// Esta pantalla mostrará únicamente las tareas completadas
// =======================================================
public class MainActivity2 extends AppCompatActivity {


    // =======================================================
    // DECLARACIÓN DE COMPONENTES VISUALES
    // =======================================================

    // ListView que mostrará las tareas completadas
    ListView listViewCompleted;

    // Botón para regresar a la pantalla anterior
    Button buttonBack;



    // =======================================================
    // MÉTODO PRINCIPAL DE LA ACTIVITY
    // Se ejecuta cuando la pantalla se crea
    // =======================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Llama al método onCreate de la clase padre
        super.onCreate(savedInstanceState);

        // Conecta esta Activity con su archivo XML de diseño
        setContentView(R.layout.activity_main2);



        // =======================================================
        // VINCULAR COMPONENTES DEL XML CON EL CÓDIGO JAVA
        // =======================================================

        // Conecta el ListView del XML con la variable Java
        listViewCompleted = findViewById(R.id.listViewCompleted);

        // Conecta el botón regresar
        buttonBack = findViewById(R.id.buttonBack);



        // =======================================================
        // RECIBIR DATOS DESDE LA ACTIVITY PRINCIPAL
        // =======================================================

        // Recibe la lista de tareas completadas enviada desde MainActivity
        ArrayList<String> completedTasks =
                getIntent().getStringArrayListExtra("completed");



        // =======================================================
        // VALIDACIÓN DE SEGURIDAD
        // =======================================================

        // Si la lista llega vacía (null), se crea una lista nueva
        if (completedTasks == null) {
            completedTasks = new ArrayList<>();
        }



        // =======================================================
        // CREAR EL ADAPTADOR
        // =======================================================

        // El adaptador conecta los datos con el ListView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(

                        this, // contexto de la Activity
                        android.R.layout.simple_list_item_1, // diseño simple de lista
                        completedTasks // lista de tareas completadas
                );



        // =======================================================
        // MOSTRAR LOS DATOS EN EL LISTVIEW
        // =======================================================

        // Asigna el adaptador al ListView
        listViewCompleted.setAdapter(adapter);



        // =======================================================
        // BOTÓN REGRESAR
        // =======================================================

        // Cuando se presiona el botón, se cierra esta Activity
        // y se regresa a la pantalla anterior
        buttonBack.setOnClickListener(v -> finish());

    }
}