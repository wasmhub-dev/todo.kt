import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.*

class TodoApp {
    private val inputBox = document.getElementById("input-box") as HTMLInputElement
    private val button = document.querySelector("button") as HTMLButtonElement
    private val list = document.getElementById("list-container") as HTMLUListElement

    init {
        list.addEventListener("click") { event ->
            val target = event.target as HTMLElement

            when (target.tagName) {
                "LI" -> target.classList.toggle("checked")
                "SPAN" -> target.parentElement?.remove()
            }

            this.saveData()
        }

        button.addEventListener("click") {
            this.addTask()
        }
    }

    private fun createTaskHtml(task: String, completed: Boolean): String {
        return """
            <li class="${if (completed) "checked" else ""}">
                $task
                <span>&times;</span>
            </li>
        """
    }

    private fun addTask() {
        if (this.inputBox.value.isEmpty()) {
            window.alert("Please enter a task!")
        } else {
            val taskName = this.inputBox.value;
            this.list.insertAdjacentHTML("beforeend", this.createTaskHtml(taskName, false));
            this.inputBox.value = ""
            this.saveData()
        }
    }

    private fun saveData() {
        window.localStorage.setItem("todo_html", this.list.innerHTML)
    }

    fun showTasks() {
        window.localStorage.getItem("todo_html")?.let {
            this.list.innerHTML = it
        }
    }
}