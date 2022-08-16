package com.oganbelema.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oganbelema.hellocompose.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateBusinessCard()
                }
            }
        }
    }
}

@Composable
fun CreateBusinessCard() {
    Surface( modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(400.dp)
            .padding(16.dp),
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            elevation = 8.dp) {
            
            Column(modifier = Modifier.height(300.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
                CreateCircleImage(R.drawable.profile)
                Divider(modifier = Modifier.padding(12.dp),
                thickness = 1.dp,
                color = Color.LightGray)
                CreateProfileDetail()
                CreatePortfolioButton()
            }
        }
    }
}

@Composable
private fun CreatePortfolioButton(modifier: Modifier = Modifier) {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    Button(onClick = { buttonClickedState.value = !buttonClickedState.value },
    modifier = modifier.padding(top = 20.dp)) {
        Text(text = "Portfolio", style = MaterialTheme.typography.button)
    }

    if (buttonClickedState.value) {
        Content()
    } else {
        Box {}
    }
}

@Composable
private fun CreateProfileDetail() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Belema Ogan",
            style = MaterialTheme.typography.h4
        )
        Text(
            text = "Software Engineer",
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "oganbelema@gmail.com",
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
private fun CreateCircleImage(@DrawableRes drawable: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(6.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = Color.LightGray,
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "User image",
            modifier = modifier.size(120.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun CreatePortfolioList(data: List<Portfolio>) {
    LazyColumn {
        items(data) { item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
            shape = RectangleShape, elevation = 4.dp) {
                Row(modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(16.dp)) {
                    CreateCircleImage(R.drawable.user, modifier = Modifier.size(60.dp))

                    Column(modifier = Modifier
                        .padding(7.dp)
                        .align(alignment = Alignment.CenterVertically)) {
                        Text(text = item.name, fontWeight = FontWeight.Bold)
                        Text(text = item.description, style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }
}

@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(8.dp)) {
        Surface(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(2.dp, Color.LightGray)
        ) {
            CreatePortfolioList(data = Portfolio.portfolioList())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HelloComposeTheme {
        CreateBusinessCard()
    }
}