package com.example.health_planner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health_planner.api.RetrofitClient
import com.example.health_planner.models.HealthJournalEntry
import com.example.health_planner.models.ReminderResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HealthJournalViewModel : ViewModel() {

    private val _entries = MutableLiveData<List<HealthJournalEntry>>()
    val entries: LiveData<List<HealthJournalEntry>> get() = _entries

    private val _entryAddResult = MutableLiveData<Result<Boolean>>()
    val entryAddResult: LiveData<Result<Boolean>> get() = _entryAddResult

    fun fetchEntries(userId: Int) {
        viewModelScope.launch {
            RetrofitClient.instance.getEntries(userId).enqueue(object : Callback<List<HealthJournalEntry>> {
                override fun onResponse(call: Call<List<HealthJournalEntry>>, response: Response<List<HealthJournalEntry>>) {
                    if (response.isSuccessful) {
                        _entries.postValue(response.body())
                    } else {
                        _entries.postValue(emptyList())
                    }
                }

                override fun onFailure(call: Call<List<HealthJournalEntry>>, t: Throwable) {
                    _entries.postValue(emptyList())
                }
            })
        }
    }

    fun addEntry(entry: HealthJournalEntry) {
        viewModelScope.launch {
            RetrofitClient.instance.addEntry(entry).enqueue(object : Callback<ReminderResponse> {
                override fun onResponse(call: Call<ReminderResponse>, response: Response<ReminderResponse>) {
                    if (response.isSuccessful && response.body()?.success == true) {
                        _entryAddResult.postValue(Result.success(true))
                        fetchEntries(entry.user_id) // Refresh entries list
                    } else {
                        _entryAddResult.postValue(Result.failure(Exception("Не удалось создать запись")))
                    }
                }

                override fun onFailure(call: Call<ReminderResponse>, t: Throwable) {
                    _entryAddResult.postValue(Result.failure(t))
                }
            })
        }
    }
}
