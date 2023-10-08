package com.example.learnjetpackcompose_project3_composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.learnjetpackcompose_project3_composequadrant.ui.theme.LearnJetpackCompose_project3_ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val colorList: List<Color> = listOf(
                colorResource(id = R.color.color1),
                colorResource(id = R.color.color2),
                colorResource(id = R.color.color3),
                colorResource(id = R.color.color4),
            )
            val titleList: List<String> = listOf(
                stringResource(id = R.string.article_title_1),
                stringResource(id = R.string.article_title_2),
                stringResource(id = R.string.article_title_3),
                stringResource(id = R.string.article_title_4),
            )
            val contentList: List<String> = listOf(
                stringResource(id = R.string.article_content_1),
                stringResource(id = R.string.article_content_2),
                stringResource(id = R.string.article_content_3),
                stringResource(id = R.string.article_content_4),
            )

            LearnJetpackCompose_project3_ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeContainer(backgroundColors = colorList, titleList = titleList, contentList = contentList)
                }
            }
        }
    }
}

@Composable
fun ComposeContainer(backgroundColors: List<Color>, titleList: List<String>, contentList: List<String>) {
    Column {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            ComposeQuadrant(
                backgroundColor = backgroundColors.get(0),
                title = titleList.get(0),
                content = contentList.get(0),
                modifier = Modifier.weight(1f)
            )

            ComposeQuadrant(
                backgroundColor = backgroundColors.get(1),
                title = titleList.get(1),
                content = contentList.get(1),
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.weight(1f)
        ) {
            ComposeQuadrant(
                backgroundColor = backgroundColors.get(2),
                title = titleList.get(2),
                content = contentList.get(2),
                modifier = Modifier.weight(1f)
            )

            ComposeQuadrant(
                backgroundColor = backgroundColors.get(3),
                title = titleList.get(3),
                content = contentList.get(3),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ComposeQuadrant(backgroundColor: Color, title: String, content: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        Text(
            text = content,
            textAlign = TextAlign.Justify,
        )
    }
}