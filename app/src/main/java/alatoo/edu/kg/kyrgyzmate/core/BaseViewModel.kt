package alatoo.edu.kg.kyrgyzmate.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : BaseState, ACTION : BaseAction>(private val initialState: STATE) : ViewModel() {

    private val coroutineScope =
        CoroutineScope(Dispatchers.Main.immediate + SupervisorJob() + CoroutineExceptionHandler() { coroutineContext, throwable ->
            println(throwable.message)
        })

    val state: StateFlow<STATE >
        get() = _state
    protected val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)

    abstract fun submitAction(action: ACTION)

    protected fun launch(operation: suspend () -> Unit) = coroutineScope.launch {
        operation()
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}