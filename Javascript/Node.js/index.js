const express = require('express');
const app = express();
const port = 5000;

app.use(express.json());

const taskRoutes = require('./server/routes/taskRoutes');
app.use('/tasks', taskRoutes);

app.get('/', (req, res) => {
    res.send('Hello World!');
});

app.listen(port, () => {
    console.log('Server started on port ${port}')
})