package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.Course
import br.senai.sp.jandira.lionschool.model.CourseList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    var listCourse by remember {
        mutableStateOf(listOf<Course>())
    }
    
    var courseState by remember {
        mutableStateOf(value = "")
    }

    var context = LocalContext.current

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
                    text = stringResource(id = R.string.app_name),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(100.dp),
                    color = Color(51, 71, 176, 255)
                )
            }
            OutlinedTextField(
                value = courseState,
                onValueChange = {courseState = it},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = stringResource(id = R.string.text_search),
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
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {}
                    )
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.list),
                    contentDescription = "",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = stringResource(id = R.string.text_course),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(51, 71, 176, 255)
                )
            }
            LazyColumn(){
                // Cria uma chamada para o endpoint
                val call = RetrofitFactory().getCourseService().getCourse()

                // Executar a chamada
                call.enqueue(object : Callback<CourseList> {
                    override fun onResponse(
                        call: Call<CourseList>,
                        response: Response<CourseList>
                    ) {
                        listCourse = response.body()!!.cursos
                    }

                    override fun onFailure(call: Call<CourseList>, t: Throwable) {

                    }

                })
                items(listCourse){
                    Spacer(modifier = Modifier.height(32.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clickable {
                                var openStudents = Intent(context, StudentsActivity::class.java)
                                context.startActivity(openStudents)

                                openStudents.putExtra("sigla", it.sigla)

                            },
                        backgroundColor = Color(240, 242, 245, 255),
                        border = BorderStroke(width = 2.dp, Color(51, 71, 176, 255)),
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
                                AsyncImage(
                                    model = it.icone,
                                    contentDescription = "",
                                    modifier = Modifier.size(86.dp)
                                )
                                Spacer(modifier = Modifier.width(32.dp))
                                Text(
                                    text = it.sigla,
                                    fontSize = 48.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(51, 71, 176, 255)
                                )
                            }
                            Text(
                                text = it.nome,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = it.carga,
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
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    LionSchoolTheme {
        CourseScreen()
    }
}