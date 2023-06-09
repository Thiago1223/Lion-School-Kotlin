package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.Student
import br.senai.sp.jandira.lionschool.model.StudentList
import br.senai.sp.jandira.lionschool.model.Students
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                val siglaCurso = intent.getStringExtra("sigla")
                val nomeCurso = intent.getStringExtra("nome")
                StudentScreen(siglaCurso.toString(), nomeCurso.toString())
            }
        }
    }
}

@Composable
fun StudentScreen(curso: String, nomeCurso: String) {

    var listStudent by remember {
        mutableStateOf(listOf<Students>())
    }

    var studentsState by remember {
        mutableStateOf(value = "")
    }

    var listStudentStatus by remember {
        mutableStateOf(listOf<Students>())
    }

    var context = LocalContext.current

    // Cria uma chamada para o endpoint
    val call = RetrofitFactory().getStudentService().getStudentByCourse(curso)

    // Executar a chamada
    call.enqueue(object : Callback<StudentList> {
        override fun onResponse(
            call: Call<StudentList>,
            response: Response<StudentList>
        ) {
            listStudent = response.body()!!.alunos
            listStudentStatus = response.body()!!.alunos
        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {

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
                    painter = painterResource(id = R.drawable.logo_image),
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

            OutlinedTextField(
                value = studentsState,
                onValueChange = {
                    studentsState = it
                                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                label = {
                    Text(
                        text = stringResource(id = R.string.text_search),
                        fontSize = 20.sp,
                        color = Color(128, 128, 128, 255))
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color(240, 242, 245, 255),
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(8.dp),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                listStudentStatus = listStudent.filter { it.curso[0].conclusao == "$studentsState" }

                                if(studentsState == ""){
                                    listStudentStatus = listStudent
                                }
                            }
                    )
                }
            )
            Text(
                text = nomeCurso,
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = Color(51, 71, 176, 255),
                modifier = Modifier.padding(top = 24.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                              listStudentStatus = listStudent
                    },
                    colors = ButtonDefaults.buttonColors(Color(51, 71, 176, 255)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.button_all),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    onClick = {
                              listStudentStatus = listStudent.filter { it.status == "Cursando" }
                    },
                    colors = ButtonDefaults.buttonColors(Color(51, 71, 176, 255)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.button_studying),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    onClick = {
                        listStudentStatus = listStudent.filter { it.status == "Finalizado" }
                    },
                    colors = ButtonDefaults.buttonColors(Color(229, 182, 87, 255)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.button_finished),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            LazyColumn() {
                items(listStudentStatus) {
                    var backgroundCard = Color(0, 0, 0)
                    if (it.status == "Finalizado") {
                        backgroundCard = Color(229, 182, 87, 255)
                    } else {
                        backgroundCard = Color(51, 71, 176, 255)
                    }
                    Card(
                        modifier = Modifier
                            .size(
                                width = 200.dp,
                                height = 300.dp
                            )
                            .clickable {
                                var openInfo = Intent(context, InfoActivity::class.java)
                                openInfo.putExtra("matricula", it.matricula)
                                context.startActivity(openInfo)
                            },
                        backgroundColor = backgroundCard
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            AsyncImage(
                                model = it.foto,
                                contentDescription = "",
                                modifier = Modifier.size(220.dp)
                            )
                            Text(
                                text = it.nome,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    LionSchoolTheme {
        StudentScreen("", "")
    }
}