package ubr.persanal.movieapp.extentions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

fun Context.isConnected() : Boolean {


     val connectivityManager=getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

     val networkInfo = connectivityManager.activeNetworkInfo

     return  networkInfo!=null && networkInfo.isConnected

 }

fun Context.isNetworkAvailable(): Boolean {

    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw      = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    } else {
        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}


fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.showSnack(view: View) {
    Snackbar.make(view, this, Snackbar.LENGTH_SHORT).show()
}


@SuppressLint("SimpleDateFormat")
fun String.getDateFrom(): String {

    return try {
        val dateInput = SimpleDateFormat("yyyy-MM-dd")
        val dateOutput = SimpleDateFormat("MMM dd, yyyy")
        dateOutput.format(dateInput.parse(this)!!)

    } catch (e: Exception) {
        e.message.toString()
    }
}