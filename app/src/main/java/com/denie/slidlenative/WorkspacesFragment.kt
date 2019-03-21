package com.denie.slidlenative

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.workspaces_fragment.*


class WorkspacesFragment : Fragment() {




    private lateinit var viewModel: WorkspacesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.workspaces_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WorkspacesViewModel::class.java)
        viewModel.getWorkspaces().observe(this, Observer<List<WorkspaceModel>> {
                workModelList -> workModelList?.let { initAdapter(workModelList) }})
    }

    private fun initAdapter(items: List<WorkspaceModel>){
        listView.layoutManager = LinearLayoutManager(activity)
        listView.adapter = WorkspacesAdapter(items)
    }

}
