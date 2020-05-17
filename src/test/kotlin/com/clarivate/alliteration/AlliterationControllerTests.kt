package com.clarivate.alliteration


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlliterationControllerTests {

	@LocalServerPort
	private val port = 0

	@Autowired
	private val restTemplate: TestRestTemplate? = null

	@Test
	@Throws(Exception::class)
	fun testExample() {
		val sentence = "Becky's beagle barked and bayed, becoming bothersome for Billy."
		assertThat(restTemplate!!.getForObject("http://localhost:$port/alliteration?sentence=$sentence",
				String::class.java)).contains("Alliteration percentage, b, 77.78")
	}

	@Test
	@Throws(Exception::class)
	fun testText1() {
		val sentence = "Mike made mellow music with his new microphone."
		assertThat(restTemplate!!.getForObject("http://localhost:$port/alliteration?sentence=$sentence",
				String::class.java)).contains("Alliteration percentage, m, 62.50")
	}

	@Test
	@Throws(Exception::class)
	fun testText2() {
		val sentence = "Yarvis yanked his ankle at yoga, and Yolanda yelled out in surprise."
		assertThat(restTemplate!!.getForObject("http://localhost:$port/alliteration?sentence=$sentence",
				String::class.java)).contains("Alliteration percentage, y, 41.67")
	}

	@Test
	@Throws(Exception::class)
	fun emptySentence() {
		val sentence = ""
		assertThat(restTemplate!!.getForObject("http://localhost:$port/alliteration?sentence=$sentence",
				String::class.java)).contains("Sentence was not provided")
	}

	@Test
	@Throws(Exception::class)
	fun invalidChar() {
		val sentence = "!No .result."
		assertThat(restTemplate!!.getForObject("http://localhost:$port/alliteration?sentence=$sentence",
				String::class.java)).contains("Alliteration percentage, null, 0.00")
	}

}
