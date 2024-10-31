package eu.centralpay.ui

import kotlin.reflect.KProperty

class ViewModelDelegate<T>(private val factory: () -> T) {
    var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (value == null) value = factory()
        return value!!
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }
}