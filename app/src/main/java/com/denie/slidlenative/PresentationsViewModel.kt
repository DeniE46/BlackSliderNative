package com.denie.slidlenative

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel;
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PresentationsViewModel : ViewModel() {

    private val api = SlidleService.createService()
    private val liveData = MediatorLiveData<List<PresentationModel>>()

    fun getPresentationsPerWorkspace(workspaceId:Int): LiveData<List<PresentationModel>> {
        runBlocking {
            val presentations: MutableList<PresentationModel> = api.getPresentationsPerWorkspace(workspaceId)
            liveData.value = presentations
        }
        return liveData
    }
}
