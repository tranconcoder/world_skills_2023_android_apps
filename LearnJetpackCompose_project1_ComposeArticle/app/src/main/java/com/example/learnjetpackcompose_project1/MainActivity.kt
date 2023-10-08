package com.example.learnjetpackcompose_project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnjetpackcompose_project1.ui.theme.LearnJetpackCompose_project1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val image: Painter = painterResource(id = R.drawable.bg_compose_background)
            val title: String = stringResource(id = R.string.tutorial_title)
            val params1: String = stringResource(id = R.string.tutorial_params1)
            val params2: String = stringResource(id = R.string.tutorial_params2)
            
            LearnJetpackCompose_project1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    GreetingJetpackComposeTutorial(image, title, params1, params2)
                }
            }
        }
    }
}

@Composable
fun GreetingJetpackComposeTutorial(image: Painter, title: String, params1: String, params2: String) {
    Column {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            GreetingTitle(title)

            GreetingParam(content = params1, modifier = Modifier.padding(start = 16.dp, end = 16.dp))

            GreetingParam(content = params2, modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun GreetingTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        fontSize = 24.sp,
        modifier = modifier.padding(16.dp),
    )
}

@Composable
fun GreetingParam(content: String, modifier: Modifier = Modifier) {
    Text(
        text = content,
        textAlign = TextAlign.Justify,
        modifier = modifier,
    )
}