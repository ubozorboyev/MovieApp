package ubr.persanal.movieapp.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import java.lang.StringBuilder
import java.text.SimpleDateFormat

@HiltAndroidApp
class App : Application() {




}

//fun main() {
//
//    val date = "2020-11-04"
//    val dateFarmat = SimpleDateFormat("yyyy-MM-dd")
//    val farmatOne = SimpleDateFormat("MMMM dd, yyyy")
//
//    println(dateFarmat.parse(date))
//
//    println(farmatOne.format(dateFarmat.parse(date)))
//
//}