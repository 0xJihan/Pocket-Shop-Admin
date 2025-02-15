package com.jihan.pocketshop.domain.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.michaelflisar.kotpreferences.core.interfaces.StorageSetting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun <T> StorageSetting<T>.collectAsStateWithLifecycle(
    lifecycle: Lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = EmptyCoroutineContext,
    initialValue: T? = getCached()
): State<T?> {
    return flow.collectAsStateWithLifecycle(
        initialValue = initialValue, lifecycle, minActiveState, context
    )
}

@Composable
fun <T> StorageSetting<T>.collectAsStateWithLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = EmptyCoroutineContext,
    initialValue: T? = getCached()
): State<T?> {
    return flow.collectAsStateWithLifecycle(
        initialValue = initialValue, lifecycleOwner, minActiveState, context
    )
}

@Composable
fun <T> StorageSetting<T>.collectAsStateWithLifecycleNotNull(
    lifecycle: Lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = EmptyCoroutineContext,
    initialValue: T = getCached() ?: value
): State<T> {
    return flow.collectAsStateWithLifecycle(
        initialValue = initialValue, lifecycle, minActiveState, context
    )
}

@Composable
fun <T> StorageSetting<T>.collectAsStateWithLifecycleNotNull(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    context: CoroutineContext = EmptyCoroutineContext,
    initialValue: T = getCached() ?: value
): State<T> {
    return flow.collectAsStateWithLifecycle(
        initialValue = initialValue, lifecycleOwner, minActiveState, context
    )
}

@Composable
fun <T> StorageSetting<T>.collectAsState(initialValue: T? = getCached()): State<T?> {
    return flow.collectAsState(initial = initialValue)
}

@Composable
fun <T> StorageSetting<T>.collectAsStateNotNull(
    initialValue: T = getCached() ?: value
): State<T> {
    return flow.collectAsState(initial = initialValue)
}

@Composable
fun <T> StorageSetting<T>.asMutableState(): MutableState<T> {
    val state = remember { mutableStateOf(value) }
    LaunchedEffect(Unit) {
        snapshotFlow {
            state.value
        }.collect {
            withContext(Dispatchers.IO) {
                update(it)
            }
        }
    }
    LaunchedEffect(Unit) {
        flow.collect {
            state.value = it
        }
    }
    return state
}


fun String.toast(context: Context) {
    android.widget.Toast.makeText(context, this, android.widget.Toast.LENGTH_SHORT).show()
}

