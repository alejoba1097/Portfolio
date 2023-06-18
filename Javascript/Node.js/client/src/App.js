import React, { useEffect, useState } from 'react';
import TaskList from './components/TaskList';

const App = () => {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    // Fetch tasks from the server (you'll need to implement this)
    fetchTasks();
  }, []);

  const fetchTasks = async () => {
    try {
      // Make a request to your backend API to fetch tasks
      const response = await fetch('/api/tasks');
      const data = await response.json();

      // Set the tasks in the state
      setTasks(data);
    } catch (error) {
      console.log('Error fetching tasks:', error);
    }
  };

  const createTask = async (newTask) => {
    try {
      // Make a request to your backend API to create a new task
      const response = await fetch('/api/tasks', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newTask),
      });
      const data = await response.json();
  
      // Update the tasks in the state
      setTasks((prevTasks) => [...prevTasks, data]);
    } catch (error) {
      console.log('Error creating task:', error);
    }
  };

  return (
    <div>
      <h1>Task Tracker</h1>
      <TaskList tasks={tasks} createTask={createTask} />
    </div>
  );
};

export default App;