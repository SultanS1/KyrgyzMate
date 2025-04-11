package alatoo.edu.kg.kyrgyzmate.extensions

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.ViewGroup
import android.view.Window
import android.widget.ProgressBar
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

fun Context.showOneActionDialog(
    title: String,
    actionText: String,
    action: (DialogInterface) -> Unit) {
    val builder = AlertDialog.Builder(this)
        .setTitle(title)
        .setPositiveButton(actionText) { dialog, _ ->
            action.invoke(dialog)
        }.show()

    builder.create()
    builder.window?.setLayout(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}

fun Context.showTwoActionDialog(
    title: String,
    positiveActionText: String,
    negativeActionText: String,
    positiveAction: (DialogInterface) -> Unit,
    negativeAction: (DialogInterface) -> Unit) {
    val builder = AlertDialog.Builder(this)
        .setTitle(title)
        .setPositiveButton(positiveActionText) { dialog, _ ->
            positiveAction.invoke(dialog)
        }.setNegativeButton(negativeActionText) { dialog, _ ->
            negativeAction.invoke(dialog)
        }.show()

    builder.create()

    builder.window?.setLayout(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
}

fun Context.showLoadingDialog(lifecycle: Lifecycle): AlertDialog {
    val progressBar = ProgressBar(this).apply {
        isIndeterminate = true
        setPadding(10, 20, 10, 20)
    }

    val loadingDialog = AlertDialog.Builder(this)
        .setView(progressBar)
        .setCancelable(false)
        .create()

    loadingDialog.setOnShowListener {
        loadingDialog.window?.let { window ->
            val originalCallback = window.callback

            window.callback = object : Window.Callback by originalCallback {
                override fun onWindowFocusChanged(hasFocus: Boolean) {
                    if (!hasFocus) {
                        loadingDialog.dismiss()
                    }
                    originalCallback?.onWindowFocusChanged(hasFocus)
                }
            }
        }
    }

    loadingDialog.setOnDismissListener {
        loadingDialog.window?.callback = null // Cleanup
    }

    val observer = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            if (loadingDialog.isShowing) {
                loadingDialog.dismiss()
            }
            lifecycle.removeObserver(this)
        }
    }
    lifecycle.addObserver(observer)

    loadingDialog.show()
    return loadingDialog
}

