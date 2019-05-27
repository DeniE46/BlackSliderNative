package com.denie.slidlenative

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.workspaces_adapter_row.view.*

class WorkspacesAdapter(

    private val itemClickListener:OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var items : List<WorkspaceModel>

    interface OnItemClickListener{
        fun onItemClick(workspaceModel: WorkspaceModel, item:View){}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workspaces_adapter_row, parent, false)

        return CViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CViewHolder).bind(items[position], itemClickListener)
    }

    class CViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(workspaceModel: WorkspaceModel, itemClick:OnItemClickListener) = with(itemView) {
            workspaceName.text = workspaceModel.name
            ownerName.text = workspaceModel.ownerName

            setOnClickListener {
                itemClick.onItemClick(workspaceModel, itemView)
            }
        }
    }

    fun setWorkspaces(list:List<WorkspaceModel>){
        this.items = list
        notifyDataSetChanged()
    }

}