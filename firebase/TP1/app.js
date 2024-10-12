import { initializeApp } from "https://www.gstatic.com/firebasejs/10.14.0/firebase-app.js"
import {
  getDatabase,
  ref,
  push,
  set,
  onValue,
  remove,
  update,
} from "https://www.gstatic.com/firebasejs/10.14.0/firebase-database.js"

const firebaseConfig = {
  apiKey: "AIzaSyDNf3-0bKHG2vEr3HfPSTzLzVHYpR-blfo",
  authDomain: "essths-project.firebaseapp.com",
  databaseURL:
    "https://essths-project-default-rtdb.europe-west1.firebasedatabase.app",
  projectId: "essths-project",
  storageBucket: "essths-project.appspot.com",
  messagingSenderId: "465411764858",
  appId: "1:465411764858:web:f113512de5b355a02381c3",
}

const app = initializeApp(firebaseConfig)
const db = getDatabase(app)

console.log({ db })

let myTasks = []
let editingTaskId = null

const displayTasks = () => {
  document.getElementsByClassName("thing")[0].innerHTML = ""
  for (let i = 0; i < myTasks.length; i++) {
    document.getElementsByClassName("thing")[0].innerHTML += `
      <div class="container" style="display: flex; align-items: center;">
        <h2>${myTasks[i].task}</h2>
        <button type='button' onclick='deleteTask("${myTasks[i].id}")'>Delete task</button>
        <button type='button' onclick='editTask("${myTasks[i].id}", "${myTasks[i].task}")'>Edit task</button>
      </div>
    `
  }
}

window.addTask = async () => {
  const data = document.getElementsByClassName("inp")[0]
  const taskText = data.value

  if (editingTaskId) {
    const taskRef = ref(db, `tasks/${editingTaskId}`)
    update(taskRef, { task: taskText })
      .then(() => {
        console.log("Task updated successfully")
        editingTaskId = null
        data.value = ""
        alert("Task updated!")
      })
      .catch((error) => {
        console.error("Error updating task:", error)
      })
  } else {
    const tasksRef = ref(db, "tasks")
    const newTaskRef = push(tasksRef)

    set(newTaskRef, { task: taskText })
      .then(() => {
        console.log("Task saved successfully")
        data.value = ""
        alert("Task added successfully!")
      })
      .catch((error) => {
        console.error("Error saving task:", error)
      })
  }
}

window.editTask = (taskId, taskText) => {
  const data = document.getElementsByClassName("inp")[0]
  data.value = taskText
  editingTaskId = taskId
}

window.deleteTask = (taskId) => {
  const taskRef = ref(db, `tasks/${taskId}`)
  remove(taskRef)
    .then(() => {
      console.log("Task removed successfully")
    })
    .catch((error) => {
      console.error("Error deleting task:", error)
    })
}

const tasksRef = ref(db, "tasks")
onValue(tasksRef, (snapshot) => {
  myTasks = []
  snapshot.forEach((childSnapshot) => {
    const taskData = childSnapshot.val()
    myTasks.push({ id: childSnapshot.key, task: taskData.task })
  })
  displayTasks()
})
