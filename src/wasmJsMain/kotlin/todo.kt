import org.w3c.dom.*

class TodoApp(private val window: Window) {
    private val document: Document = window.document
    private val inputBox: HTMLInputElement = document.getElementById("input-box") as HTMLInputElement
    private val button: HTMLButtonElement = document.querySelector("button") as HTMLButtonElement
    private val list: HTMLUListElement = document.getElementById("list-container") as HTMLUListElement

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

    private fun createTaskHtml(task: String): String {
        return """
            <li>
                $task
                <span>&times;</span>
            </li>
        """
    }

    private fun addTask() {
        if (this.inputBox.value.isEmpty()) {
            this.window.alert("Please enter a task!")
        } else {
            val taskName = this.inputBox.value;
            this.list.insertAdjacentHTML("beforeend", this.createTaskHtml(taskName));
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