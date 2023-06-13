package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.StudentList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentService {

    @GET("alunos")
<<<<<<< HEAD
    fun getStudentByCourse(@Query("curso") sigla: String): Call<StudentList>
=======
    fun getStudentByCourse(@Query("curso") siglaCurso: String): Call<StudentList>
>>>>>>> edf63dfbd79312384d9813e53e987b9f09b542d0

}