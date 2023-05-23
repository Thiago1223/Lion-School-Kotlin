package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class CourseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                CourseScreen()
            }
        }
    }
}

@Composable
fun CourseScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo_image),
                    contentDescription = "",
                    modifier = Modifier.size(72.dp)
                )
                Text(
                    text = "Lion School",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(100.dp),
                    color = Color(51, 71, 176, 255)
                )
            }
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Search",
                        fontSize = 20.sp,
                        color = Color(128, 128, 128, 255))},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color(240, 242, 245, 255),
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.list),
                    contentDescription = "",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Courses",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(51, 71, 176, 255)
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                backgroundColor = Color(240, 242, 245, 255),
                border = BorderStroke(width = 2.dp, Color(51, 71, 176, 255)),
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_ds),
                            contentDescription = "",
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.width(32.dp))
                        Text(
                            text = "DS",
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(51, 71, 176, 255)
                        )
                    }
                    Text(
                        text = "Desenvolvimento de Sistemas",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Learn how to develop web and mobile apps",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(128, 128, 128, 255),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                backgroundColor = Color(240, 242, 245, 255),
                border = BorderStroke(width = 2.dp, Color(51, 71, 176, 255)),
                shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_rds),
                            contentDescription = "",
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.width(32.dp))
                        Text(
                            text = "RDS",
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(51, 71, 176, 255)
                        )
                    }
                    Text(
                        text = "Redes de Computadores",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Learn how to develop web and mobile apps",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(128, 128, 128, 255),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    LionSchoolTheme {
        CourseScreen()
    }
}