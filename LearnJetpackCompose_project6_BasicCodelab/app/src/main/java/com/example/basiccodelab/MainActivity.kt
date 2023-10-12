package com.example.basiccodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiccodelab.ui.theme.BasicCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            BasicCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = Color.White,
                ) {
                    var showScreen by rememberSaveable {
                        mutableStateOf(true)
                    }

                    if (showScreen) {
                        OnboardingScreen(onContinueClick = {showScreen = false})
                    } else {
                        CardList(cardList = List(100){"Hello World!"})
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "Welcome to Basic Codelab!",
            style = MaterialTheme.typography.bodyLarge,
        )
        
        Button(
            onClick = onContinueClick,
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            Text(
                text = "Continue",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
fun CardItem(title: String) {
    var expanded = rememberSaveable() {
        mutableStateOf(false)
    }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .animateContentSize()
                    .weight(1f)
                    .padding(bottom = if (expanded.value) 50.dp else 0.dp)
            ) {
                Text(text = title)
                
                Spacer(modifier = Modifier.height(10.dp))
                
                Text(text = "Hello world")
            }

            ElevatedButton(onClick = { expanded.value = !expanded.value }) {
                Text(text = if (expanded.value) "Less more" else "Show more")
            }
        }
    }
}

@Composable
fun CardList(cardList: List<String>) {
    LazyColumn {
        items(cardList) {
            CardItem(title = it)
        }
    }
}