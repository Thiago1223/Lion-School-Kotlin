package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.StudentList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentService {

    @GET("alunos")
    fun getStudentByCourse(@Query("curso") sigla: String): Call<StudentList>

}