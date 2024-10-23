package com.example.finale.ui

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LoginFragmentUnitTest {

    private lateinit var fragment: LoginFragment

    @Before
    fun setUp() {
        fragment = LoginFragment()
        // Manually set the injected values for testing
        fragment.usernameCorrect = "correctUsername"
        fragment.passwordCorrect = "correctPassword"
    }

    @Test
    fun testCheckLoginInfo_CorrectCredentials() {
        val result = fragment.checkLoginInfo("correctUsername", "correctPassword",
            fragment.usernameCorrect,
            fragment.passwordCorrect)
        assertTrue(result)
    }

    @Test
    fun testCheckLoginInfo_IncorrectUsername() {
        val result = fragment.checkLoginInfo("wrongUsername", "correctPassword",
            fragment.usernameCorrect,
            fragment.passwordCorrect)
        assertFalse(result)
    }

    @Test
    fun testLoginErrorReason_EmptyFields() {
        val errorMessage = fragment.loginErrorReason("", "",
            fragment.usernameCorrect,
            fragment.passwordCorrect)
        assertEquals("Enter valid information", errorMessage)
    }

    @Test
    fun testLoginErrorReason_IncorrectCredentials() {
        val errorMessage = fragment.loginErrorReason("wrongUsername", "wrongPassword",
            fragment.usernameCorrect,
            fragment.passwordCorrect)
        assertEquals("Either username or password is incorrect", errorMessage)
    }
}
