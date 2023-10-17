package com.example.todoapp

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.ui.theme.White20
import com.example.todoapp.viewmodel.Todo
import com.example.todoapp.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var value by remember {
        mutableStateOf("")
    }

    BasicTextField (
        value,
        onValueChange = {newValue -> value = newValue},
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 30.dp)
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                ) {
                    if (value.length === 0) {
                        Text(
                            text = if (value.length === 0) "Search" else value,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                    } else {
                        innerTextField()
                    }
                }

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    ),
                    modifier = Modifier.background(Color.White)
                ) {
                    Text(
                        text = "Search",
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
    }
}

@Preview
@Composable
fun AppHeader(modifier: Modifier = Modifier) {
    Surface (
        modifier = modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary,
    ) {
        Column (
            modifier = Modifier
                .padding(horizontal = 13.dp)
                .padding(bottom = 22.dp, top = 24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge,
            )

            Spacer(modifier = Modifier.height(14.dp))

            SearchBar()
        }
    }
}

@Composable
fun TodoItem(itemData: Todo,  onChangeStatus: (todoItem: Todo) -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 10.dp)
        ) {
            Button(
                onClick = { onChangeStatus(itemData) },
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = if(itemData.done) MaterialTheme.colorScheme.primary else Color.Transparent,
                    modifier = Modifier
                        .size(60.dp)
                        .border(2.dp, MaterialTheme.colorScheme.surfaceVariant, CircleShape),
                )
            }

            Spacer(modifier = Modifier.width(30.dp))

            // Content
            Column {
                Text(
                    text = itemData.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = itemData.detail,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        lineHeight = 24.sp,
                    ),
                    maxLines =  1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Divider(
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Preview
@Composable
fun TodoItemPreview(todoViewModel: TodoViewModel = viewModel()) {
    TodoItem(itemData = todoViewModel.todoList.get(0), onChangeStatus = {})
}

@Preview
@Composable
fun TodoList(modifier: Modifier = Modifier, todoViewModel: TodoViewModel = viewModel()) {
    Column (
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
                .background(MaterialTheme.colorScheme.primary)
                .clip(RoundedCornerShape(topStartPercent = 100, topEndPercent = 100))
                .background(MaterialTheme.colorScheme.surface),
        )

        LazyColumn (
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        ) {
            items(todoViewModel.todoList) {
                TodoItem(itemData = it, onChangeStatus = { item ->
                    todoViewModel.switchStatus(item)
                })
            }
        }
    }
}


@Preview (
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview (
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column() {
        AppHeader()

        TodoList()
    }
}

@Composable
fun TodoApp() {
    TodoAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen()
        }
    }
}

// Roboto Font
public val robotoFontFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold),
)
