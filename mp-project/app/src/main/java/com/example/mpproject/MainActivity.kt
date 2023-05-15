package com.example.mpproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.mpproject.components.AddItemDialogComponent
import com.example.mpproject.components.ProfileComponent
import com.example.mpproject.components.TaskComponent
import com.example.mpproject.components.WelcomeComponent
import com.example.mpproject.data.TaskModel
import com.example.mpproject.ui.theme.MPProjectTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var selectedScreen by remember { mutableStateOf(1) }
            val screens = listOf("Add")

            val openDialog = remember { mutableStateOf(false) }
            val taskModel by remember { mutableStateOf(TaskModel()) }

            MPProjectTheme {
                Scaffold(bottomBar = {
                    NavigationBar(
                        modifier = Modifier.height(90.dp),
                        containerColor = Color.White,
                        tonalElevation = 0.dp
                    ) {
                        screens.forEachIndexed { index, _ ->
                            val icon: ImageVector = Icons.Filled.Add
                            NavigationBarItem(
                                selected = selectedScreen == index,
                                onClick = {
                                    selectedScreen = index
                                    openDialog.value = true
                                },
                                icon = {
                                    Box(
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(CircleShape)
                                            .background(if (selectedScreen == index) Color.Black else Color.White),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = icon,
                                            contentDescription = "Add",
                                            modifier = Modifier
                                                .size(50.dp)
                                                .padding(12.dp),
                                            tint = if (selectedScreen == index) Color.White else Color.Black
                                        )
                                        if (openDialog.value) {
                                            AddItemDialogComponent(
                                                openDialog = openDialog,
                                                taskModel = taskModel
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    }
                }) {
                    LazyColumn(
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            top = 16.dp,
                            bottom = 16.dp
                        )
                    ) {
                        item {
                            ProfileComponent()
                        }

                        item {
                            Spacer(modifier = Modifier.height(30.dp))
                            WelcomeComponent()
                            Spacer(modifier = Modifier.height(30.dp))
                        }

                        items(taskModel.getAllToDoList()) { task ->
                            TaskComponent(task = task)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }
}