package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.ui.theme.ui.theme.LionSchoolTheme

class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                InfoScreen()
            }
        }
    }
}

@Composable
fun InfoScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = br.senai.sp.jandira.lionschool.R.drawable.logo_image),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = "Lion School",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(100.dp),
                    color = Color(51, 71, 176, 255)
                )
            }
            Divider(
                modifier = Modifier
                    .width(280.dp)
                    .height(2.dp),
                color = Color(51, 71, 176, 255)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                border = BorderStroke(width = 2.dp, color = Color(51, 71, 176, 255))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "",
                        modifier = Modifier.size(240.dp)
                    )
                    Text(
                        text = "HÉLIDA BENTO DE OLIVEIRA LINS",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(51, 71, 176, 255),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview4() {
    LionSchoolTheme {
        InfoScreen()
    }
}