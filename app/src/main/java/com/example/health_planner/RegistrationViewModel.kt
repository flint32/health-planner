package com.example.health_planner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health_planner.api.RetrofitClient
import com.example.health_planner.models.RegisterRequest
import com.example.health_planner.models.RegisterResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationViewModel : ViewModel() {

    private val _registrationSuccess = MutableLiveData<Boolean>()
    val registrationSuccess: LiveData<Boolean> get() = _registrationSuccess

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            val request = RegisterRequest(name, email, password)
            RetrofitClient.instance.register(request).enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    if (response.isSuccessful) {
                        _registrationSuccess.postValue(true)
                    } else {
                        _registrationSuccess.postValue(false)
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    _registrationSuccess.postValue(false)
                }
            })
        }
    }
}
