package com.besha.egyptguide.auth.authcore.domain.repo

interface AuthRepo {
    fun isLoggedIn(): Boolean
}