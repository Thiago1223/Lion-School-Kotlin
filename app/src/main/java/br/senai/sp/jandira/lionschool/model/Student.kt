package br.senai.sp.jandira.lionschool.model

data class Student(
    val id: Long,
    val nome: String,
    val foto: String,
    val matricula: String,
    val status: String,
    val ano: String
)
