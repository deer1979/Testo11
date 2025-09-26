package com.deercom.testo11.data

data class User(
    val alias: String = "",
    val nombres: String = "",
    val apellidos: String = "",
    val cargo: String = "",
    val documento: String = "",
    val telefonoPersonal: String = "",
    val telefonoDispositivo: String = "",
    val localidad: String = "",
    val fotoUri: String = "",
    val passwordHash: String = "" // modo pruebas: hash simple (SHA-256)
)

