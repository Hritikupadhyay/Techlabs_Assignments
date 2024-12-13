import { Component } from '@angular/core';

@Component({
  selector: 'app-to-do-task',
  templateUrl: './to-do-task.component.html',
  styleUrls: ['./to-do-task.component.css']
})
export class ToDoTaskComponent {

  tasks: string[] = []; 
  task: string = ''; 
 
  addTask(): void { 
    if (this.task.trim()) { 
      this.tasks.push(this.task); 
      this.task = ''; 
    } 
  } 
 
  removeTask(index: number): void { 
    this.tasks.splice(index, 1); }
}
