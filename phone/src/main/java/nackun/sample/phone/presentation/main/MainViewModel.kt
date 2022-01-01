package nackun.sample.phone.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber

open class MainViewModel : ViewModel() {
    private val _buttonClickEvent: MutableSharedFlow<Unit> =
        MutableSharedFlow(
            replay = 0,
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val buttonClickEvent = _buttonClickEvent.asSharedFlow()

    fun onButtonClick() {
        Timber.d("onClick")
        viewModelScope.launch {
            _buttonClickEvent.emit(Unit)
        }
    }
}
