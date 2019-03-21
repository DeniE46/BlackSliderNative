package com.denie.slidlenative

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkspacesViewModel : ViewModel() {

    private val liveDataList = MediatorLiveData<List<WorkspaceModel>>()
    private val api = SlidleService.createService()

    fun getWorkspaces(): LiveData<List<WorkspaceModel>>{
        var items : MutableList<WorkspaceModel>
        api.getWorkspaces().enqueue(object : Callback<MutableList<WorkspaceModel>>{
            override fun onFailure(call: Call<MutableList<WorkspaceModel>>, t: Throwable) {
                Log.d("TAG", "error occurred: " + t.message)
            }

            override fun onResponse(call: Call<MutableList<WorkspaceModel>>, response: Response<MutableList<WorkspaceModel>>) {
                val data = response.body()
                items = data!!
                for(item in items){
                    Log.d("TAG", item.name)
                }
                liveDataList.value = items

            }
        })

        return liveDataList

    }
}
