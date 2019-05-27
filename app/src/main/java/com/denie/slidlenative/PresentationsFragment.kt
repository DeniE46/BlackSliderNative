package com.denie.slidlenative

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.presentations_fragment.*


class PresentationsFragment : Fragment(), PresentationsAdapter.OnItemClickListener {


    override fun onItemClicked(
        presentationModel: PresentationModel,
        onItemClickListener: PresentationsAdapter.OnItemClickListener
    ) {
        Toast.makeText(activity, "got:" + presentationModel.title, Toast.LENGTH_SHORT).show()
    }

    private lateinit var viewModel: PresentationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.presentations_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PresentationsViewModel::class.java)

        val workspaceID = arguments?.get("workspaceID") as Int
        workspaceID.let {
            viewModel.getPresentationsPerWorkspace(workspaceID).observe(this, Observer { presentations ->
                initAdapter(presentations)
            })
        }
    }


    private fun initAdapter(items:List<PresentationModel>){
        presentationRecycler.layoutManager = LinearLayoutManager(activity)
        presentationRecycler.adapter = PresentationsAdapter(items, this)
    }
}
