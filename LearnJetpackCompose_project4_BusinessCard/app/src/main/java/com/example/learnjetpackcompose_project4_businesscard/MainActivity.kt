package com.example.learnjetpackcompose_project4_businesscard

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnjetpackcompose_project4_businesscard.business.InfoField
import com.example.learnjetpackcompose_project4_businesscard.ui.theme.LearnJetpackCompose_project4_BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val avatar = painterResource(id = R.drawable.ic_launcher_foreground)
            val infoList = mutableListOf<InfoField>();

            infoList.add(InfoField(icon = painterResource(id = R.drawable.baseline_supervised_user_circle_24), "Trần Văn Còn"))
            infoList.add(InfoField(icon = painterResource(id = R.drawable.baseline_mail_outline_24), "tranvanconkg@gmail.com"))
            infoList.add(InfoField(icon = painterResource(id = R.drawable.baseline_smartphone_24), "0327781160"))
            Log.d("Hello", infoList.size.toString())

            LearnJetpackCompose_project4_BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.bg_app_color)
                ) {
                    ComposeBusinessCard(
                        image = avatar,
                        name = "Trần  Văn Còn",
                        description = "Học lập trình android bằng Jetpack Compose",
                        infoList = infoList
                    )
                }
            }
        }
    }
}

@Composable
fun ComposeBusinessCard(image: Painter, name: String, description: String, infoList: List<InfoField>) {
    Column(
        modifier = Modifier
            .padding(20.dp),
    ){
        ComposeUserInfo(image, name, description, modifier = Modifier.weight(1f))

        // Render list of info field
        LazyColumn{
            items(infoList){
                ComposeInfoItem(icon = it.icon, text = it.value)
            }
        }
    }
}

@Composable
fun ComposeUserInfo(image: Painter, name: String, description: String, modifier: Modifier = Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier,
    ){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .size(180.dp)
                .background(colorResource(id = R.color.bg_avatar_color))
        )

        Text(
            text = name,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            modifier = Modifier.padding(top = 20.dp),
            textAlign = TextAlign.Center,
        )

        Text(
            text = description,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun ComposeInfoItem(icon: Painter, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp, bottom = 20.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(id = R.color.bg_item_color))
            .padding(10.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp),
        )

        Text(
            text,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            modifier = Modifier
                .padding(start = 10.dp)
        )
    }
}