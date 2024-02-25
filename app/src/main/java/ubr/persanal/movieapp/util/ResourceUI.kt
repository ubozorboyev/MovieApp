package ubr.persanal.movieapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

sealed class ResourceUI<out T> {

    object Loading : ResourceUI<Nothing>()

    data class Error(val error: String?) : ResourceUI<Nothing>()

    data class Resource<T>(val data: T?) : ResourceUI<T>()
}


