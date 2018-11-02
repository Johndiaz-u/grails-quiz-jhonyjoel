package edu.pucmm.quiz.grails

class EventoUsuario {


    String direccion;

    static belongsTo = [usuario : Usuario] //El cascada..


    static constraints = {
        usuario(nullable: true) //Permitiendo la agregración...
    }


}


