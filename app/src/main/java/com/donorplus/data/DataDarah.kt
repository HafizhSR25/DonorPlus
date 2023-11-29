package com.donorplus.data

data class DataDarah(
    val id: String,
    val tipeDarah: String,
    val expiredDate: String,
    val location: String,

) {
    constructor() : this("", "", "", "")
}
