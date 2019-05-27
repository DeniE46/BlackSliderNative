package com.denie.slidlenative

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.*
import retrofit2.*

class WorkspacesViewModel : ViewModel() {

    private var liveDataList = MediatorLiveData<List<WorkspaceModel>>()
    private val workspacesRepository :WorkSpacesRepository = WorkSpacesRepository()

    init {
        getWorkspaces()
    }

//    fun getWorkspaces(): LiveData<List<WorkspaceModel>>{
//        var items : MutableList<WorkspaceModel>
//        api.getWorkspaces().enqueue(object : Callback<MutableList<WorkspaceModel>>{
//            override fun onFailure(call: Call<MutableList<WorkspaceModel>>, t: Throwable) {
//                Log.d("TAG", "error occurred: " + t.message)
//            }
//
//            override fun onResponse(call: Call<MutableList<WorkspaceModel>>, response: Response<MutableList<WorkspaceModel>>) {
//                val data = response.body()
//                items = data!!
//                for(item in items){
//                    Log.d("TAG", item.name)
//                }
//                liveDataList.value = items
//
//            }
//        })
//
//        return liveDataList
//    }

   fun getWorkSpacesNew():LiveData<List<WorkspaceModel>>{

        return liveDataList
    }

    private fun getWorkspaces(){
       liveDataList.addSource(workspacesRepository.getWorkspaces()){workspaces->
           workspaces.map { it.name = it.name + " 46" }
           editWorkspacesList(workspaces)
       }
        Log.d("TAG", "from repo")
    }

    private fun editWorkspacesList(workspacesList: MutableList<WorkspaceModel>){
        workspacesList.add(WorkspaceModel(46, "from ViewModel", "Mutated"))
        liveDataList.postValue(workspacesList)
    }

}
