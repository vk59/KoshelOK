package com.tinkoffsirius.koshelok

data class ApiPerson(val firstName: String, val lastName: String)

data class DbPerson(val name: String, val surname: String)

data class DomainPerson(val fullName: String)

fun ApiPerson.toDomain(): DomainPerson {
    return DomainPerson(fullName = "$firstName $lastName")
}

fun DomainPerson.toDb(): DbPerson {
    val (fn, ln) = fullName.split(" ")
    return DbPerson(name = fn, surname = ln)
}