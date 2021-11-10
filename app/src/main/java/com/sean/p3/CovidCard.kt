package com.sean.p3

import android.os.Build
import androidx.annotation.RequiresApi
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.time.LocalDate
import java.util.*

@Entity
data class CovidCard constructor(
    @Id
    var id:Long = 0,
    var vaccine:String = "1st Dose COVID-19",
    var Manufacturer:String = "Pfizer",
    var date: String = "mm/dd/yyyy",
    val ClinicSite:String = "",
    var isVaxinated:Boolean = false)