package com.denie.slidlenative

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WorkSpacesRepository {

    private val api = SlidleService.createService()
    private val liveDataList = MutableLiveData<MutableList<WorkspaceModel>>()

    fun getWorkspaces():MutableLiveData<MutableList<WorkspaceModel>>{
        GlobalScope.launch(Dispatchers.Main) {
            val data = withContext(Dispatchers.IO){
                api.getWorkspaces()
            }
            liveDataList.value = data
        }
        return liveDataList
    }


}