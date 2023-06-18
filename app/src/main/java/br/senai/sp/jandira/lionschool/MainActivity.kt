package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                WelcomeScreen()
            }
        }
    }
}

@Composable
fun WelcomeScreen() {

    var context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                backgroundColor = Color(51, 71, 176, 255),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.text_welcome),
                            color = Color.White,
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(200.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.logo_image),
                            contentDescription = "",
                            modifier = Modifier.size(80.dp)
                        )
                    }
                    Card(
                        modifier = Modifier
                            .width(200.dp)
                            .height(8.dp),
                        backgroundColor = Color(229, 182, 87, 255),
                        shape = RoundedCornerShape(8.dp)
                    ) {

                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.student),
                    contentDescription = "",
                    modifier = Modifier.size(380.dp)
                )
                Spacer(modifier = Modifier.height(18.dp))
                Button(
                    onClick = {
                        var openCourse = Intent(context, CourseActivity::class.java)
                        context.startActivity(openCourse)
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .height(46.dp),
                    colors = ButtonDefaults.buttonColors(Color(229, 182, 87, 255)),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(width = 2.dp, Color(51, 71, 176, 255))
                ) {
                    Text(
                        text = stringResource(id = R.string.button_started),
                        fontSize = 20.sp,
                        color = Color(51, 71, 176, 255)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LionSchoolTheme {
        WelcomeScreen()
    }
}