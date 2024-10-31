package eu.centralpay.ui.exo16

import java.time.LocalDate
import kotlin.reflect.full.memberProperties

data class MyClass1(
    val id: Int,
    val name: String,
    val myClass2: MyClass2 = MyClass2(12),
    val date: LocalDate? = LocalDate.now(),
    val list: List<MyClass2>? = listOf(MyClass2(12), MyClass2(13)),
)

data class MyClass2(
    val id: Int,
)

fun Any.toJson(result: StringBuilder = StringBuilder(), isInitial: Boolean = true): StringBuilder {
    val kClass = this::class

    val properties = kClass.memberProperties
    when {
        this is Int -> result.append(this.toString())
        this is Long -> result.append(this.toString())
        this is Iterable<*> -> {
            result.append("[")
            this.forEachIndexed { index, elt ->
                elt?.toJson(result, false) ?: "null"
                if (index != this.count() - 1) result.append(", ")
            }
            result.append("]")
        }

        else -> if (properties.isNotEmpty()) {
            result.append("{")
            try {
                properties.mapIndexed { index, property ->
                    result.append("\"${property.name}\": ")
                    property.call(this)?.toJson(result, false) ?: result.append("null")
                    if (index != properties.size - 1) result.append(", ")
                }.toString()
            } catch (e: Exception) {
                result.append("\"$this\"")
            }
            result.append("}")
        } else result.append("\"$this\"")
    }
    return result
}

fun main() {
    val myClass1 = MyClass1(1, "name")
    val myClass2 = MyClass2(2)

    println(myClass1.toJson())
    println(myClass2.toJson())
}