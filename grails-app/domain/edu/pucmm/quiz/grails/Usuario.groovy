package edu.pucmm.quiz.grails

class Usuario {

    int id;
    String nombre;
    String apellido;
    String cedula;
    String email;
    String fechaNacimiento;
    transient String noIrPerteneceAlModelo;


    Date dateCreated    //fechaInicio
    Date lastUpdated     //fechaFin

    //Uno a Mucho.
    static hasMany = [listaEventos : EventoUsuario]


    static constraints = {
        id(unique: true)
        email(email: true);
        nombre(blank: false)
        apellido blank: false
        cedula blank: false
        fechaNacimiento blank: false
    }

}



