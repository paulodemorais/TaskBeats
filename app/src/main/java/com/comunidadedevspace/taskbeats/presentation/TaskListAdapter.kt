package com.comunidadedevspace.taskbeats.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.Task

class TaskListAdapter(

    private val openTaskDetailView:(task: Task) -> Unit
    ): ListAdapter<Task, TaskListerViewHolder>(TaskListAdapter){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListerViewHolder {

            val view: View = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_task, parent, false)
            return TaskListerViewHolder(view)
    }


    override fun onBindViewHolder(holder: TaskListerViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task, openTaskDetailView)
    }

    companion object : DiffUtil.ItemCallback<Task>(){

        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.description == newItem.description
        }

    }
}

class TaskListerViewHolder(
    private val view: View
    ) : RecyclerView.ViewHolder(view){
        private val tvTitle = view.findViewById<TextView>(R.id.tv_task_title)
        private val tvDesc = view.findViewById<TextView>(R.id.tv_task_description)

        fun bind(task: Task, openTaskDetailView:(task: Task) -> Unit){
            tvTitle.text = task.title
            tvDesc.text = task.description

            view.setOnClickListener{
                openTaskDetailView.invoke(task)
            }
    }
}