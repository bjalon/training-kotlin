package eu.centralpay.generic

open class Animal<T> {

    fun sleep(): T {
        println("RonnnnPshiiitt")
        return this as T
    }

    fun eating(): T {
        println("Miam")
        return this as T
    }
}

class Dog : Animal<Dog>() {
    fun bark(): Dog {
        println("Ouafff")
        return this
    }
}

class Cat : Animal<Cat>() {
    fun meow(): Cat {
        println("Miaouuuu")
        return this
    }
}

fun main() {
    val dog = Dog()
    val cat = Cat()

    dog.sleep().eating().bark()
    cat.sleep().eating().meow().meow()
}