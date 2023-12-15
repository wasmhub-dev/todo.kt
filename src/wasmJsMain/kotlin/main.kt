import kotlinx.browser.window

fun main() {
    println("Hello, Kotlin/JS!")
    TodoApp(window).showTasks()
}