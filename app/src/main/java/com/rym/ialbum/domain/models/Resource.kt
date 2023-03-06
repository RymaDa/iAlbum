package com.rym.ialbum.domain.models


/**
 *
 *La classe Resource est une classe générique utilisée pour représenter l'état d'une opération
 *
 * asynchrone, telle qu'une requête réseau.
 *
 * Elle contient trois propriétés principales : status,data et message.
 *
 * La propriété status représente l'état de l'opération, qui peut être SUCCESS (réussite),
 *
 * ERROR (erreur), LOADING (chargement) ou ANY (toute autre situation).
 *
 * La propriété data contient les données de l'opération, qui peuvent être de n'importe quel type
 *
 * générique T. Si l'opération a réussi, data contiendra les données retournées.
 *
 * Si l'opérationa échoué, data sera null.
 *
 * La propriété message contient un message d'erreur ou de succès à afficher à l'utilisateur.
 *
 * La classe Resource contient également un objet enum interne Status qui représente les différents
 *
 * états possibles de l'opération.
 *
 * La classe contient également un objet companion qui fournit des méthodes utilitaires pour créer
 *
 * des instances de Resource. Ces méthodes sont nommées en fonction de l'état de l'opération :
 *
 * success, error et loading. Elles permettent de créer facilement des instances de Resource avec
 *
 * les bonnes propriétés en fonction de l'état de l'opération.
 *
 */



data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        INIT
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> error(message: String): Resource<T> {
            return Resource(Status.ERROR, null, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> init(data: T? = null): Resource<T> {
            return Resource(Status.INIT, null, null)
        }
    }
}
