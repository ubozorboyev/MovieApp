package ubr.persanal.movieapp.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

sealed class DataState<out T> {

    object Loading : DataState<Nothing>()
    class Error(val message: String?) : DataState<Nothing>()
    class ResponseData<T>(val data: T?) : DataState<T>()
}


fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun String.showSnack(view: View) {
    Snackbar.make(view, this, Snackbar.LENGTH_SHORT).show()
}


fun String.getDateFrom(): String {

    try {
        val dateInput = SimpleDateFormat("yyyy-MM-dd")
        val dateOutput = SimpleDateFormat("MMM dd, yyyy")
        return dateOutput.format(dateInput.parse(this))
    } catch (e: Exception) {
        return this
    }
}