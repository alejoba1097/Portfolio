const express = require('express');
const app = express();

// Middleware
app.use(express.json());

// Routes
app.get('/', (req, res) => {
    res.send('Task Tracker API');
});

/* const taskRoutes = require('./server/routes/taskRoutes');
app.use('/tasks', taskRoutes);
*/

// Start the server
const port = process.env.PORT || 5000;
app.listen(port, () => {
    console.log('Server started on port ${port}')
});