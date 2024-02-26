package ubr.persanal.movieapp.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes contentLayoutId:Int):Fragment(contentLayoutId) {




    abstract fun updatePagingList()


}