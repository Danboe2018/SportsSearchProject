package com.danboehmke.sportssearchproject

import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    val mainActivity = Mockito.mock(MainActivity::class.java)
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