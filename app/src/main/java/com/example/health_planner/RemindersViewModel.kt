package com.example.health_planner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health_planner.api.RetrofitClient
import com.example.health_planner.models.Reminder
import com.example.health_planner.models.ReminderResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemindersViewModel : ViewModel() {

    private val _reminders = MutableLiveData<List<Reminder>>()
    val reminders: LiveData<List<Reminder>> get() = _reminders

    private val _reminderAddResult = MutableLiveData<Result<Boolean>>()
    val reminderAddResult: LiveData<Result<Boolean>> get() = _reminderAddResult

    fun fetchReminders() {
        viewModelScope.launch {
            RetrofitClient.instance.getReminders().enqueue(object : Callback<List<Reminder>> {
                override fun onResponse(call: Call<List<Reminder>>, response: Response<List<Reminder>>) {
                    if (response.isSuccessful) {
                        _reminders.postValue(response.body())
                    } else {
                        _reminders.postValue(emptyList())
                    }
                }

                override fun onFailure(call: Call<List<Reminder>>, t: Throwable) {
                    _reminders.postValue(emptyList())
                }
            })
        }
    }

    fun addReminder(reminder: Reminder) {
        viewModelScope.launch {
            RetrofitClient.instance.addReminder(reminder).enqueue(object : Callback<ReminderResponse> {
                override fun onResponse(call: Call<ReminderResponse>, response: Response<ReminderResponse>) {
                    if (response.isSuccessful) {
                        _reminderAddResult.postValue(Result.success(true))
                        fetchReminders() // Refresh reminders list
                    } else {
                        _reminderAddResult.postValue(Result.failure(Exception("Не удалось создать напоминание")))
                    }
                }

                override fun onFailure(call: Call<ReminderResponse>, t: Throwable) {
                    _reminderAddResult.postValue(Result.failure(t))
                }
            })
        }
    }

    fun updateReminder(reminder: Reminder) {
        viewModelScope.launch {
            RetrofitClient.instance.updateReminder(reminder.reminder_id, reminder).enqueue(object : Callback<ReminderResponse> {
                override fun onResponse(call: Call<ReminderResponse>, response: Response<ReminderResponse>) {
                    if (response.isSuccessful) {
                        fetchReminders()
                    }
                }

                override fun onFailure(call: Call<ReminderResponse>, t: Throwable) {
                    // Handle error
                }
            })
        }
    }

    fun deleteReminder(reminderId: Int, userId: Int) {
        viewModelScope.launch {
            RetrofitClient.instance.deleteReminder(reminderId).enqueue(object : Callback<ReminderResponse> {
                override fun onResponse(call: Call<ReminderResponse>, response: Response<ReminderResponse>) {
                    if (response.isSuccessful) {
                        fetchReminders()
                    }
                }

                override fun onFailure(call: Call<ReminderResponse>, t: Throwable) {
                    // Handle error
                }
            })
        }
    }
}
