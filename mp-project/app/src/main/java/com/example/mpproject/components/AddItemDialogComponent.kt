package com.example.mpproject.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mpproject.data.Task
import com.example.mpproject.data.TaskModel
import com.example.mpproject.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemDialogComponent(
    openDialog: MutableState<Boolean>,
    taskModel: TaskModel = viewModel()

) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var body by remember { mutableStateOf(TextFieldValue("")) }
    var startTime by remember { mutableStateOf(TextFieldValue("")) }
    var endTime by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
        shape = RoundedCornerShape(16.dp),
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            openDialog.value = false
        },

        title = {
            Text(
                text = "Add Task", modifier = Modifier.padding(20.dp, 10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },

        text = {
            Column(
                modifier = Modifier.padding(10.dp, 0.dp),
            ) {
                TextField(
                    value = title,
                    onValueChange = { newText ->
                        title = newText
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    label = { Text("Enter title") }
                )
                TextField(
                    value = body,
                    onValueChange = { newText ->
                        body = newText
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    label = { Text("Enter body") }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    TextField(
                        value = startTime,
                        onValueChange = { newText ->
                            startTime = newText
                        },
                        modifier = Modifier
                            .fillMaxWidth(4f)
                            .padding(end = 5.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        label = { Text("From") }
                    )
                    TextField(
                        value = endTime,
                        onValueChange = { newText ->
                            endTime = newText
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        label = { Text("To") }
                    )
                }
            }
        },

        confirmButton = {
            Button(
                onClick = {
                    if (title.text.isNotEmpty()) {
                        //add the the todoItem on the todoList
                        taskModel.addTodoList(
                            Task(
                                id = (0..Int.MAX_VALUE).random(), //generate random id
                                title = title.text,
                                body = body.text,
                                startTime = startTime.text,
                                endTime = endTime.text
                            )
                        )
                        openDialog.value = false // for closing the dialog
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            ) {
                Text(
                    "Add", color = Color.White,
                )
            }
        },

        dismissButton = {
            Button(
                onClick = {
                    openDialog.value =
                        false //close the dialog to assign the value false when Cancel button is clicked
                },
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
            ) {
                Text(
                    "Cancel", color = Color.White,
                )
            }
        }
    )
}