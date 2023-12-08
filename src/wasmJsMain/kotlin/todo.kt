import kotlinx.browser.window
import org.w3c.dom.*

class TodoApp(private val window: Window) {
    private val document: Document = window.document
    private val inputBox: HTMLInputElement = document.getElementById("input-box") as HTMLInputElement
    private val button: HTMLButtonElement = document.querySelector("button") as HTMLButtonElement
    private val list: HTMLUListElement = document.getElementById("list-container") as HTMLUListElement
    private val storage: Storage = window.localStorage

    init {
        list.addEventListener("click") { event ->
            val target = event.target as HTMLElement
            if (target.tagName == "LI") {
                target.classList.toggle("checked")
                this.saveData()
            } else if (target.tagName == "SPAN") {
                target.parentElement?.remove()
                this.saveData()
            }
        }

        button.addEventListener("click") {
            this.addTask()
        }
    }

    private fun addTask() {
        if (this.inputBox.value.isEmpty()) {
            this.window.alert("Please enter a task!")
        } else {
            val li = this.document.createElement("li") as HTMLLIElement
            li.innerHTML = this.inputBox.value
            this.list.appendChild(li)
            this.inputBox.value = ""

            val span = this.document.createElement("span") as HTMLSpanElement
            span.innerHTML = "&times;"
            li.appendChild(span)
        }
        this.saveData()
    }

    private fun saveData() {
        this.storage.setItem("todo_list", this.list.innerHTML)
    }

    fun showTasks() {
        val data = this.storage.getItem("todo_list")
        println(data)
        if (data != null) {
            this.list.innerHTML = data
        }
    }
}

fun main() {
    println("Hello, Kotlin/JS!")
    TodoApp(window).showTasks()
}