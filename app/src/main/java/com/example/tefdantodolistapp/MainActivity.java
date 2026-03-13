package com.example.tefdantodolistapp;

// Importa la clase base para crear Activities en Android
import androidx.appcompat.app.AppCompatActivity;

// Permite cambiar de una Activity a otra
import android.content.Intent;

// Maneja el ciclo de vida de la Activity
import android.os.Bundle;

// Importa componentes visuales de Android (botones, listas, etc.)
import android.widget.*;

// Permite usar listas dinámicas
import java.util.ArrayList;


// =============================
// ACTIVITY PRINCIPAL DE LA APP
// =============================
public class MainActivity extends AppCompatActivity {

    // Campo de texto donde el usuario escribe la tarea
    EditText editTextTask;

    // Botones de la aplicación
    Button buttonAdd;        // Botón para agregar tarea
    Button buttonDelete;     // Botón para eliminar tarea
    Button buttonCompleted;  // Botón para ver tareas completadas

    // Lista visual donde se muestran las tareas
    ListView listViewTasks;

    // Checkbox para marcar si la tarea está completada
    CheckBox checkCompleted;


    // Lista que almacena TODAS las tareas creadas
    ArrayList<String> taskList = new ArrayList<>();


    // Lista que almacena SOLO las tareas completadas
    ArrayList<String> completedTasks = new ArrayList<>();


    // Adaptador que conecta la lista de datos con el ListView
    ArrayAdapter<String> adapter;


    // Variable que guarda la posición de la tarea seleccionada
    int selectedPosition = -1;



    // =============================
    // MÉTODO PRINCIPAL DE LA ACTIVITY
    // =============================
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Conecta esta Activity con el archivo XML del diseño
        setContentView(R.layout.activity_main);


        // =============================
        // VINCULAR COMPONENTES DEL XML
        // =============================

        editTextTask = findViewById(R.id.editTextTask);

        buttonAdd = findViewById(R.id.buttonAdd);

        buttonDelete = findViewById(R.id.buttonDelete);

        buttonCompleted = findViewById(R.id.buttonCompleted);

        listViewTasks = findViewById(R.id.listViewTasks);

        checkCompleted = findViewById(R.id.checkCompleted);



        // =============================
        // CREAR ADAPTADOR PARA LA LISTA
        // =============================

        adapter = new ArrayAdapter<>(
                this,                                     // Contexto de la aplicación
                android.R.layout.simple_list_item_single_choice, // Diseño de cada elemento
                taskList                                  // Lista de tareas
        );


        // Conectar el adaptador con el ListView
        listViewTasks.setAdapter(adapter);


        // Permitir seleccionar SOLO una tarea a la vez
        listViewTasks.setChoiceMode(ListView.CHOICE_MODE_SINGLE);



        // =============================
        // BOTÓN AGREGAR TAREA
        // =============================
        buttonAdd.setOnClickListener(v -> {

            // Obtener texto escrito por el usuario
            String task = editTextTask.getText().toString();

            // Verificar que no esté vacío
            if(!task.isEmpty()){

                // Agregar tarea a la lista
                taskList.add(task);

                // Actualizar ListView
                adapter.notifyDataSetChanged();

                // Limpiar campo de texto
                editTextTask.setText("");

            }

        });



        // =============================
        // DETECTAR TAREA SELECCIONADA
        // =============================
        listViewTasks.setOnItemClickListener((parent, view, position, id) -> {

            // Guardar la posición de la tarea seleccionada
            selectedPosition = position;

        });



        // =============================
        // BOTÓN ELIMINAR TAREA
        // =============================
        buttonDelete.setOnClickListener(v -> {

            // Verificar que haya una tarea seleccionada
            if(selectedPosition != -1){

                // Obtener la tarea seleccionada
                String task = taskList.get(selectedPosition);

                // Si el checkbox está marcado
                if(checkCompleted.isChecked()){

                    // Guardar tarea en lista de completadas
                    completedTasks.add(task);

                }

                // Eliminar tarea de la lista principal
                taskList.remove(selectedPosition);

                // Actualizar ListView
                adapter.notifyDataSetChanged();

            }

        });



        // =============================
        // BOTÓN VER TAREAS COMPLETADAS
        // =============================
        buttonCompleted.setOnClickListener(v -> {

            // Crear Intent para abrir la Activity 2
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);

            // Enviar la lista de tareas completadas a la otra Activity
            intent.putStringArrayListExtra("completed", completedTasks);

            // Iniciar la Activity 2
            startActivity(intent);

        });

    }
}