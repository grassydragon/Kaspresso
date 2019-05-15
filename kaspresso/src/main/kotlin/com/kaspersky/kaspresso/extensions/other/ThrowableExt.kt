package com.kaspersky.kaspresso.extensions.other

import io.reactivex.exceptions.CompositeException
import java.io.PrintWriter
import java.io.StringWriter

/**
 * @return the stack trace of the [Throwable] as a [String].
 */
fun Throwable.getStackTraceAsString(): String {
    val sw = StringWriter()
    val pw = PrintWriter(sw)

    printStackTrace(pw)

    return sw.toString()
}

inline fun <reified T : Throwable> invokeSafely(exceptions: MutableList<T>, action: () -> Unit) {
    try {
        action.invoke()
    } catch (e: Throwable) {
        if (e is T) {
            exceptions.add(e)
        } else {
            throw e
        }
    }
}

internal fun <T : Throwable> List<T>.throwAll() {
    when (this.size) {
        1 -> throw  this[0]
        in 2..Int.MAX_VALUE -> throw CompositeException(this)
    }
}