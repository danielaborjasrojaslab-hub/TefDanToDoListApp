// =======================================================
// PAQUETE DE LA APLICACIÓN
// Aquí se define en qué paquete se encuentra esta clase
// =======================================================
package com.example.tefdantodolistapp;


// =======================================================
// IMPORTACIONES NECESARIAS
// =======================================================

// Permite crear Activities compatibles con versiones modernas de Android
import androidx.appcompat.app.AppCompatActivity;

// Permite usar el método onCreate y manejar el ciclo de vida de la Activity
import android.os.Bundle;

// Importa los componentes visuales que utilizaremos
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

    // ListView que mostrará la lista de tareas completadas
    ListView listViewCompleted;

    // Botón que permitirá regresar a la pantalla anterior
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
        // IMPORTANTE: el nombre del layout debe estar en minúsculas
        setContentView(R.layout.activity_main2);



        // =======================================================
        // VINCULAR COMPONENTES DEL XML CON EL CÓDIGO JAVA
        // =======================================================

        // Vinculamos el ListView definido en el XML
        listViewCompleted = findViewById(R.id.listViewCompleted);

        // Vinculamos el botón regresar
        buttonBack = findViewById(R.id.buttonBack);



        // =======================================================
        // RECIBIR DATOS DESDE LA ACTIVITY 1
        // =======================================================

        // Recibimos la lista de tareas completadas enviada desde la primera Activity
        ArrayList<String> completedTasks =
                getIntent().getStringArrayListExtra("completed");



        // =======================================================
        // VALIDACIÓN DE SEGURIDAD
        // =======================================================

        // Si la lista llega como null (vacía), creamos una nueva
        // Esto evita errores cuando se intenta mostrar la lista
        if(completedTasks == null){
            completedTasks = new ArrayList<>();
        }



        // =======================================================
        // CREAR EL ADAPTADOR
        // =======================================================

        // El adaptador conecta la lista de datos con el ListView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(

                        this, // contexto de la aplicación

                        android.R.layout.simple_list_item_1, // diseño simple para cada elemento

                        completedTasks // lista que contiene las tareas completadas
                );



        // =======================================================
        // MOSTRAR LOS DATOS EN EL LISTVIEW
        // =======================================================

        // Asignamos el adaptador al ListView para mostrar las tareas
        listViewCompleted.setAdapter(adapter);



        // =======================================================
        // EVENTO DEL BOTÓN REGRESAR
        // =======================================================

        // Cuando el usuario presiona el botón:
        // finish() cierra esta Activity y vuelve a la anterior
        buttonBack.setOnClickListener(v -> finish());

    }
}