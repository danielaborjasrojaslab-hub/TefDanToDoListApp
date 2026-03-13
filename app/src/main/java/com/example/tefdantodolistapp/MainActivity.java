package com.example.tefdantodolistapp;
// Indica el paquete al que pertenece esta clase dentro del proyecto

import androidx.appcompat.app.AppCompatActivity;
// Importa la clase base para actividades compatibles con diferentes versiones de Android

import android.content.Intent;
// Permite usar Intents para cambiar de una Activity a otra

import android.os.Bundle;
// Permite manejar el estado de la Activity cuando se crea

import android.view.View;
// Permite trabajar con elementos visuales de la interfaz

import android.widget.*;
// Importa todos los componentes gráficos como Button, ListView, EditText, etc.

import java.util.ArrayList;
// Permite usar listas dinámicas de datos


public class MainActivity extends AppCompatActivity {
    // Declara la clase principal de la aplicación y hereda de AppCompatActivity


    // Campo donde el usuario escribe la tarea
    EditText editTextTask;
    // Variable que representa el campo de texto donde el usuario escribe la tarea


    // Botones
    Button buttonAdd;
    Button buttonDelete;
    Button buttonCompleted;
    // Variables que representan los tres botones de la interfaz


    // Lista visual
    ListView listViewTasks;
    // Componente que mostrará la lista de tareas en pantalla


    // CheckBox para marcar tarea completada
    CheckBox checkCompleted;
    // Casilla que permite indicar si una tarea está completada


    // Lista de tareas
    ArrayList<String> taskList = new ArrayList<>();
    // Lista dinámica donde se almacenan las tareas


    // Lista de tareas completadas
    ArrayList<String> completedTasks = new ArrayList<>();
    // Lista donde se guardarán las tareas que se marcaron como completadas


    // Adaptador para conectar datos con ListView
    ArrayAdapter<String> adapter;
    // El adaptador se encarga de mostrar los datos de la lista en el ListView


    // Posición de la tarea seleccionada
    int selectedPosition = -1;
    // Guarda la posición de la tarea seleccionada (-1 significa que no hay ninguna seleccionada)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Método que se ejecuta cuando se crea la Activity

        super.onCreate(savedInstanceState);
        // Llama al método de la clase padre para iniciar correctamente la Activity

        setContentView(R.layout.activity_main);
        // Carga el diseño XML llamado activity_main


        // Vincular componentes del XML con variables Java
        editTextTask = findViewById(R.id.editTextTask);
        // Conecta el EditText del XML con la variable editTextTask

        buttonAdd = findViewById(R.id.buttonAdd);
        // Conecta el botón agregar con su variable

        buttonDelete = findViewById(R.id.buttonDelete);
        // Conecta el botón eliminar

        buttonCompleted = findViewById(R.id.buttonCompleted);
        // Conecta el botón ver completadas

        listViewTasks = findViewById(R.id.listViewTasks);
        // Conecta el ListView

        checkCompleted = findViewById(R.id.checkCompleted);
        // Conecta el CheckBox


        // Crear adaptador
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_single_choice,
                taskList
        );
        // El adaptador toma los datos de taskList y los muestra en el ListView


        // Asignar adaptador al ListView
        listViewTasks.setAdapter(adapter);
        // Conecta el adaptador al ListView para que se puedan mostrar las tareas


        // Permitir seleccionar un elemento
        listViewTasks.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // Permite seleccionar solo una tarea a la vez


        // BOTÓN AGREGAR
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            // Define qué ocurre cuando se presiona el botón agregar

            @Override
            public void onClick(View v) {

                String task = editTextTask.getText().toString();
                // Obtiene el texto que escribió el usuario

                if (!task.isEmpty()) {
                    // Verifica que la tarea no esté vacía

                    taskList.add(task);
                    // Agrega la tarea a la lista

                    adapter.notifyDataSetChanged();
                    // Actualiza el ListView para mostrar la nueva tarea

                    editTextTask.setText("");
                    // Limpia el campo de texto

                }

            }
        });


        // Detectar tarea seleccionada
        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Se ejecuta cuando el usuario selecciona una tarea de la lista

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedPosition = position;
                // Guarda la posición de la tarea seleccionada

            }
        });


        // BOTÓN ELIMINAR
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            // Define la acción del botón eliminar

            @Override
            public void onClick(View v) {

                if (selectedPosition != -1) {
                    // Verifica que haya una tarea seleccionada

                    String task = taskList.get(selectedPosition);
                    // Obtiene la tarea seleccionada

                    if (checkCompleted.isChecked()) {
                        // Verifica si el checkbox está marcado

                        completedTasks.add(task);
                        // Si está marcado, guarda la tarea como completada

                    }

                    taskList.remove(selectedPosition);
                    // Elimina la tarea de la lista principal

                    adapter.notifyDataSetChanged();
                    // Actualiza el ListView

                }

            }
        });


        // BOTÓN VER COMPLETADAS
        buttonCompleted.setOnClickListener(new View.OnClickListener() {
            // Acción del botón que muestra las tareas completadas

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                // Crea un Intent para abrir la segunda Activity

                intent.putStringArrayListExtra("completed", completedTasks);
                // Envía la lista de tareas completadas a la segunda Activity

                startActivity(intent);
                // Inicia la nueva Activity

            }
        });

    }
}