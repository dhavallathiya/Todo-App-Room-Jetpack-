package com.example.todo_approomjetpack

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.viewModelFactory
import java.text.SimpleDateFormat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoListPage(viewModel: TodoViewModel) {

    val todos by viewModel.todosList.observeAsState()
    var inputText by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(modifier = Modifier.weight(1f), value = inputText, onValueChange = {
                inputText = it
            })
            Button(onClick = {
                viewModel.addTodo(inputText)
                inputText = ""
            }) {
                Text(text = "Add")
            }
        }

        todos?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it) { index, item ->
                        TodoItem(item, onDelete = {
                            viewModel.deleteTodo(item.id)
                        })
                    }
                }
            )
        } ?: Text(
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            text = "No Todos"
        )

    }
}

@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(item.CreatedAt),
                fontSize = 12.sp,
                color = Color.LightGray
            )
            Text(
                item.title,
                fontSize = 18.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painterResource(R.drawable.delete),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}