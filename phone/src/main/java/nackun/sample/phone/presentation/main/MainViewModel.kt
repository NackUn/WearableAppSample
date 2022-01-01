package nackun.sample.phone.presentation.main

import androidx.lifecycle.ViewModel
import timber.log.Timber

open class MainViewModel : ViewModel() {
    fun onButtonClick() {
        Timber.d("onClick")
    }
}
