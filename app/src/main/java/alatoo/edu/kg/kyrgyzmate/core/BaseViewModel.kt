package alatoo.edu.kg.kyrgyzmate.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val coroutineScope =
        CoroutineScope(Dispatchers.Main.immediate + SupervisorJob() + CoroutineExceptionHandler() { coroutineContext, throwable ->
            println(throwable.message)
        })

    protected fun launch(operation: suspend () -> Unit) = coroutineScope.launch {
        operation()
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}