package com.besha.egyptguide.auth.screens.login.data.model

data class GoogleSignInResult(
    val user:UserData?,
    val errorMessage:String?
)

data class UserData(
    val userId:String?,
    val fullName :String?,
    val email:String?,
    val phoneNumber:String?,
    val profilePhoto:String?
)
