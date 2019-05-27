package com.denie.slidlenative

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.workspaces_fragment.*
import kotlinx.android.synthetic.main.workspaces_fragment.view.*


class WorkspacesFragment : Fragment(), WorkspacesAdapter.OnItemClickListener {

    private lateinit var viewModel: WorkspacesViewModel
    private lateinit var workspaceAdapter: WorkspacesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.workspaces_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        workspaceShimmer.startShimmerAnimation()
        viewModel = ViewModelProviders.of(this).get(WorkspacesViewModel::class.java)
        viewModel.getWorkSpacesNew().observe(this, Observer<List<WorkspaceModel>> { workModelList ->
            workModelList?.let {
                initAdapter()
                updateAdapter(workModelList)
                workspaceShimmer.visibility = View.GONE
            }
        })
    }

    private fun initAdapter() {
        workspaceAdapter = WorkspacesAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = workspaceAdapter
    }

    private fun updateAdapter(list: List<WorkspaceModel>) {
        workspaceAdapter.setWorkspaces(list)
    }

    override fun onItemClick(workspaceModel: WorkspaceModel, item: View) {
        super.onItemClick(workspaceModel, item)
        Toast.makeText(activity, "clicked: " + workspaceModel.name, Toast.LENGTH_SHORT).show()
        val workspaceBundle = Bundle().apply { putInt("workspaceID", workspaceModel.id) }
        findNavController().navigate(R.id.action_workspacesFragment_to_presentationsFragment, workspaceBundle)
    }

    override fun onResume() {
        super.onResume()
        workspaceShimmer.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        workspaceShimmer.stopShimmerAnimation()
    }
}
