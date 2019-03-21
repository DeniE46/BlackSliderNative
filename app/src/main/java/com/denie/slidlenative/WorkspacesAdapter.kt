package com.denie.slidlenative

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.workspaces_adapter_row.view.*

class WorkspacesAdapter(
    private val items : List<WorkspaceModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workspaces_adapter_row, parent, false)

        return CViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CViewHolder).bind(items[position])
    }

    class CViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(workspaceModel: WorkspaceModel) = with(itemView) {
            workspaceName.text = workspaceModel.name
            ownerName.text = workspaceModel.ownerName
        }
    }


}