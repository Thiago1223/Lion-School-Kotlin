package br.senai.sp.jandira.lionschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.Student
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
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
                StudentScreen(matriculaAluno.toString())
            }
        }
    }
}


@Composable
fun StudentScreen(matricula: String) {
    val context = LocalContext.current

    var listStudent by remember {
        mutableStateOf(Student("", "", "", "", emptyList()))
    }

    val call = RetrofitFactory().getInfoService().getStudentbyMatricula(matricula)

    call.enqueue(object : Callback<Student> {
        override fun onResponse(call: Call<Student>, response: Response<Student>) {
            if (response.isSuccessful) {
                val studentResponse = response.body()
                if (studentResponse != null) {
                    listStudent = studentResponse
                }
            } else {
                Log.e("teste", "Erro na resposta da API: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<Student>, t: Throwable) {
            Log.i("teste", "onFailure: ${t.message} ")
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
                    items(listStudent.curso) {
                        AsyncImage(
                            model = listStudent.foto,
                            contentDescription = "",
                            modifier = Modifier.size(240.dp)
                        )
                        Text(
                            text = listStudent.nome,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(51, 71, 176, 255),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(listStudent.curso.getOrNull(0)?.disciplinas.orEmpty()) {

                    var barra = 2.4 * it.media.toDouble()
                    var corBarra = Color.White

                    if (it.media.toDouble() > 60) {
                        corBarra = Color(51, 71, 176, 255)
                    } else if (it.media.toDouble() >= 50) {
                        corBarra = Color(229, 182, 87, 255)
                    } else if (it.media.toDouble() < 50) {
                        corBarra = Color(193, 16, 16, 255)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = it.sigla,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(51, 71, 176, 255)
                        )
                        Box(
                            modifier = Modifier
                                .height(16.dp)
                                .width(220.dp)
                                .background(Color(238, 239, 248, 255))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(barra.dp)
                                    .clip(
                                        shape = RoundedCornerShape(
                                            topEnd = 4.dp,
                                            bottomEnd = 4.dp
                                        )
                                    )
                                    .background(corBarra)
                            )
                        }
                        Text(
                            text = it.media,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = corBarra
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}