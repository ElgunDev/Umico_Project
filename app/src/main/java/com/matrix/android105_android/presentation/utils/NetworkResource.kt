package com.matrix.android105_android.presentation.utils

sealed class NetworkResource<T> {
     class Success<T>(val data:T):NetworkResource<T>()
    class Loading<T>():NetworkResource<T>()
    class Error<T>(val message:String):NetworkResource<T>()

}