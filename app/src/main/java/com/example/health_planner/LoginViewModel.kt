package com.example.health_planner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health_planner.api.RetrofitClient
import com.example.health_planner.models.LoginRequest
import com.example.health_planner.models.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val request = LoginRequest(email, password)
            RetrofitClient.instance.login(request).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        _loginSuccess.postValue(true)
                    } else {
                        _loginSuccess.postValue(false)
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _loginSuccess.postValue(false)
                }
            })
        }
    }
}
