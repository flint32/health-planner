package com.example.health_planner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health_planner.api.RetrofitClient
import com.example.health_planner.models.User
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun fetchUserData(userId: Int) {
        viewModelScope.launch {
            RetrofitClient.instance.getUser(userId).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        _user.postValue(response.body())
                    } else {
                        _user.postValue(null)
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    _user.postValue(null)
                }
            })
        }
    }
}
