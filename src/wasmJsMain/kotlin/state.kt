
data class State(val tasks: List<Task>)

data class Task(val name: String, var checked: Boolean)