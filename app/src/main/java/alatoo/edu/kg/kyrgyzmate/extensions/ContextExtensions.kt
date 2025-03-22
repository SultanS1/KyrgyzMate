package alatoo.edu.kg.kyrgyzmate.extensions

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

fun Context.showOneActionDialog(
    title: String,
    actionText: String,
    icon: Int,
    action: (DialogInterface) -> Unit) {
    val builder = AlertDialog.Builder(this)

    builder.setTitle(title).setIcon(icon).
            setPositiveButton(actionText) { dialog, _ ->
                action.invoke(dialog)
            }.show()

    builder.create()
}

fun Context.showTwoActionDialog(
    title: String,
    positiveActionText: String,
    negativeActionText: String,
    positiveAction: (DialogInterface) -> Unit,
    negativeAction: (DialogInterface) -> Unit) {
    val builder = AlertDialog.Builder(this)

    builder.setTitle(title)
        .setPositiveButton(positiveActionText) { dialog, _ ->
            positiveAction.invoke(dialog)
        }.setNegativeButton(negativeActionText) { dialog, _ ->
            negativeAction.invoke(dialog)
        }.show()

    builder.create()
}

fun Context.showLoadingDialog(
    title: String,
    loaderResId: Int,
    negativeActionText: String,
    negativeAction: (DialogInterface) -> Unit) {
    val builder = AlertDialog.Builder(this)

    builder.setTitle(title)
        .setView(loaderResId)
        .setNegativeButton(negativeActionText) { dialog, _ ->
            negativeAction.invoke(dialog)
        }.show()

    builder.create()
}