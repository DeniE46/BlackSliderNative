package com.denie.slidlenative

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.presentations_adapter_row.view.*

class PresentationsAdapter(
    var items : List<PresentationModel>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<PresentationsAdapter.PresentationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresentationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.presentations_adapter_row, parent, false)
        return PresentationViewHolder(view)
    }

    interface OnItemClickListener{
        fun onItemClicked(presentationModel: PresentationModel, onItemClickListener: OnItemClickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PresentationViewHolder, position: Int) {
        holder.bind(items[position], onItemClickListener)
    }

    class PresentationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(presentationModel: PresentationModel, onItemClickListener: OnItemClickListener) = with(itemView){
            presentationName.text = presentationModel.title

            setOnClickListener {
                onItemClickListener.onItemClicked(presentationModel, onItemClickListener)
            }
        }
    }
}