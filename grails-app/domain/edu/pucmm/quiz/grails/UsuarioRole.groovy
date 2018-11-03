package edu.pucmm.quiz.grails

import groovy.transform.ToString
import org.apache.commons.lang.builder.HashCodeBuilder
import org.hibernate.criterion.DetachedCriteria


@ToString(cache=true, includeNames=true, includePackage=false)
class UsuarioRole implements Serializable {

    private static final long serialVersionUID = 1

    Usuario usuario
    Roles roles



    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (usuario) builder.append(usuario.id)
        if (roles) builder.append(roles.id)
        builder.toHashCode()
    }

    @Override
    boolean equals(other) {
        if (other instanceof UsuarioRole) {
            other.usuarioId == usuario?.id && other.rolesId == roles?.id
        }
    }

    static UsuarioRole get(long usuarioId, long rolesId) {
        criteriaFor(usuarioId, rolesId).get()
    }

    static boolean exists(long usuarioId, long rolesId) {
        criteriaFor(usuarioId, rolesId).count()
    }

    private static DetachedCriteria criteriaFor(long usuarioId, long rolesId) {
        UsuarioRole.where {
            usuario == Usuario.load(usuarioId) &&
                    roles == Roles.load(rolesId)
        }
    }


    static UsuarioRole create(Usuario usuario, Roles roles) {
        def instance = new UsuarioRole(usuario: usuario, roles: roles)
        instance.save()
        instance
    }



    static constraints = {
        roles validator: { Roles r, UsuarioRole ur ->
            if (ur.usuario?.id) {
                UsuarioRole.withNewSession {
                    if (UsuarioRole.exists(ur.usuario.id, r.id)) {
                        return ['userRole.exists']
                    }
                }
            }
        }
    }

    static mapping = {
        id composite: ['usuario', 'roles']
        version false
    }


}