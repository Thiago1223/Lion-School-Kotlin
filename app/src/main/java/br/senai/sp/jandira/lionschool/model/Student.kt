package br.senai.sp.jandira.lionschool.model

data class Student(
    val nome: String,
    val foto: String,
    val matricula: String,
    val status: String,
    val curso: List<Course>
)
