package ubr.persanal.movieapp.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {



    private val _updatePagingData = MutableSharedFlow<Boolean>()

    val updatePagingData : SharedFlow<Boolean> = _updatePagingData



    fun updateUiPagingData(isUpdate: Boolean){


        viewModelScope.launch {
            _updatePagingData.emit(isUpdate)

        }

    }




}