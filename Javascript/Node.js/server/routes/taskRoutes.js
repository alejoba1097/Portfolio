const express = require('express');
const router = express.Router();
const { Task } = require('../models');

// Get all tasks
router.get('/', async (req, res) => {
    try {
        const tasks = await Task.findAll();
        res.json(tasks);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Server Error'});
    }
});

// Create new task
router.post('/', async (req, res) => {
    const { title, description } = req.body;
    try {
        const task = await Task.create({title, description});
        res.status(201).json(task);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: 'Server Error'});
    }
});

// Get a specific task by ID
router.get('/:id', async (req, res) => {
    const {id} = req.params;
    try {
        const task = await Task.findByPk(id);
        if (!task){
            return res.status(404).json({error: 'Task not found'});
        }
        res.json(task);
    } catch (error) {
        console.error(error);
        res.status(500).json({error: 'Server Error'});
    }
});

// Update a task
router.put('/:id', async (req, res) => {
    const {id} = req.params;
    const {title, description} = req.body;
    try{
        const task = await Task.findByPk(id);
        if(!task){
            return res.status(404).json({error: 'Task not found'});
        }
        task.tile = title;
        task.description = description;
        await task.save();
        res.json(task);
    } catch (error) {
        console.error(error);
        res.status(500).json({error: 'Server Error'});
    }
});

// Delete a task
router.delete('/:id', async(req, res) => {
    const{id} = req.params;
    try{
        const task = await Task.findByPk(id);
        if (!task){
            return res.status(404).json({error: 'Task not found'});
        }
        await task.destroy();
        res.status(204).json({message: 'Task deleted succesfully'});
    } catch (error){
        console.error(error);
        res.status(500).json({error: 'Server Error'});
    }
});

module.exports = router;