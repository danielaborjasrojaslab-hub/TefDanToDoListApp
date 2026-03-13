package com.example.tefdantodolistapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Campo donde el usuario escribe la tarea
    EditText editTextTask;

    // Botones
    Button buttonAdd;
    Button buttonDelete;
    Button buttonCompleted;

    // Lista visual
    ListView listViewTasks;

    // CheckBox para marcar tarea completada
    CheckBox checkCompleted;

    // Lista de tareas
    ArrayList<String> taskList = new ArrayList<>();

    // Lista de tareas completadas
    ArrayList<String> completedTasks = new ArrayList<>();

    // Adaptador para conectar datos con ListView
    ArrayAdapter<String> adapter;

    // Posición de la tarea seleccionada
    int selectedPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular componentes del XML
        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonCompleted = findViewById(R.id.buttonCompleted);
        listViewTasks = findViewById(R.id.listViewTasks);
        checkCompleted = findViewById(R.id.checkCompleted);


        // Crear adaptador
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_single_choice,
                taskList
        );

        // Asignar adaptador al ListView
        listViewTasks.setAdapter(adapter);

        // Permitir seleccionar un elemento
        listViewTasks.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        // BOTÓN AGREGAR
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String task = editTextTask.getText().toString();

                if (!task.isEmpty()) {

                    taskList.add(task);
                    adapter.notifyDataSetChanged();
                    editTextTask.setText("");

                }

            }
        });


        // Detectar tarea seleccionada
        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedPosition = position;

            }
        });


        // BOTÓN ELIMINAR
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedPosition != -1) {

                    String task = taskList.get(selectedPosition);

                    if (checkCompleted.isChecked()) {

                        completedTasks.add(task);

                    }

                    taskList.remove(selectedPosition);
                    adapter.notifyDataSetChanged();

                }

            }
        });


        // BOTÓN VER COMPLETADAS
        buttonCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                intent.putStringArrayListExtra("completed", completedTasks);

                startActivity(intent);

            }
        });

    }
}