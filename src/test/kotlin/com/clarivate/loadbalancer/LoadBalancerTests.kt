package com.clarivate.loadbalancer


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test



class LoadBalancerTests {

	private val loadBalancer = LoadBalancer()

	@Test
	@Throws(Exception::class)
	fun testExampleOne() {
		val request: IntArray = intArrayOf(1, 3, 4, 2, 2, 2, 1, 1, 2)
		assertThat(loadBalancer.checkDistribution(request)).isTrue()
	}

	@Test
	@Throws(Exception::class)
	fun testExampleTwo() {
		val request: IntArray = intArrayOf(1, 1, 1, 1, 1, 1)
		assertThat(loadBalancer.checkDistribution(request)).isFalse()
	}

}
