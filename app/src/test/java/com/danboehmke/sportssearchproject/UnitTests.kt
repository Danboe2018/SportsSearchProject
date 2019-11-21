package com.danboehmke.sportssearchproject

import com.android.volley.toolbox.Volley
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Mock
    lateinit var mainActivity: MainActivity
    lateinit var volley: Volley

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mainActivity = MainActivity()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_search_teams() {
        val testResp = mainActivity.searchTeams("Eagles")
        println("Test Resp: $testResp")
        assert(true)
    }

}