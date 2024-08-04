package com.bitflyer.github.client

import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before

abstract class BaseUnitTest {

    @Before
    open fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    open fun tearDown() {
        unmockkAll()
    }
}
