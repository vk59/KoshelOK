package com.tinkoff_sirius.koshelok.model

sealed class TransactionType {

    sealed class Income() : TransactionType() {
        class Salary() : Income()
        class PartWorkJob() : Income()
        class Present() : Income()
        class Capitalization() : Income()
    }

    sealed class Outcome() : TransactionType() {
        class Food() : Outcome()
        class Clothes() : Outcome()
        class Services() : Outcome()
    }
}
