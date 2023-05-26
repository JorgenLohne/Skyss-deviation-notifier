package com.jorgenlohne.deviationTracker.model

data class Deviation(
    val routes: List<String>,
    val heading: String,
    val message: String,
    val active: Boolean
) {

}