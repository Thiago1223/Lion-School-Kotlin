package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.Student
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface InfoService {

    @GET("alunos/{matricula}")
    fun getStudentbyMatricula(@Path("matricula") matricula: String): Call<Student>

}