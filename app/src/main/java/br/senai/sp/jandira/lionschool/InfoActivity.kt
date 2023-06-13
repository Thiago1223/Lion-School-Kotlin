package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.model.Course
import br.senai.sp.jandira.lionschool.model.InfoList
import br.senai.sp.jandira.lionschool.model.Student
import br.senai.sp.jandira.lionschool.model.StudentList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                val matriculaAluno = intent.getStringExtra("matricula")
                InfoScreen(matriculaAluno.toString())
            }
        }
    }
}

@Composable
fun InfoScreen(matricula: String) {

    var listStudent by remember {
        mutableStateOf(listOf<Student>())
    }

    var context = LocalContext.current

    // Cria uma chamada para o endpoint
    val call = RetrofitFactory().getInfoService().getStudentbyMatricula(matricula)

    // Executar a chamada
    call.enqueue(object : Callback<InfoList> {
        override fun onResponse(
            call: Call<InfoList>,
            response: Response<InfoList>
        ) {
            listStudent = response.body()!!.aluno
        }

        override fun onFailure(call: Call<InfoList>, t: Throwable) {

        }

    })

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
                    text = stringResource(id = R.string.app_name),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(100.dp),
                    color = Color(51, 71, 176, 255)
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
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
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    items(listStudent) {
                        AsyncImage(
                            model = it.foto,
                            contentDescription = "",
                            modifier = Modifier.size(240.dp)
                        )
                        Text(
                            text = it.nome,
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview4() {
    LionSchoolTheme {
        InfoScreen("")
    }
}