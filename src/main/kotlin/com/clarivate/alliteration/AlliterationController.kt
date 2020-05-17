package com.clarivate.alliteration


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Spring Boot Controller for alliterations.
 *
 * @author Danilo Acosta
 */
@SpringBootApplication
@RestController
class AlliterationController {

    /**
     * Get the highest alliteration of a sentence.
     *
     * @param sentence Any [String] sentence (including an empty sentence) with any character.
     * @return A string containing the result: "Alliteration percentage, [Char], [Float]". Where [Char]
     * is the consonant with highest alliteration percentage, and [Float] is the percentage with two
     * decimal precision. Or "Sentence was not provided" if the sentence is null of empty
     */
    @GetMapping("/alliteration")
    fun alliteration(@RequestParam(value = "sentence") sentence: String): String {
        if (!sentence.isNullOrEmpty()) {
            val alliterationCounter: HashMap<String, Int> = HashMap()
            // Split a sentence by whitespaces
            val tokens = sentence.split("\\s".toRegex())

            // For each token (or word), checks if the first character is a consonant, and adds the number of occurrence
            tokens.forEach { token ->
                val ch: Char = token.first().toLowerCase()
                if (ch.isLetter() && (ch != 'a' || ch != 'e' || ch != 'i' || ch != 'o' || ch != 'u')) {
                    val counterValue: Int? = alliterationCounter[ch.toString()]
                    alliterationCounter[ch.toString()] = if (counterValue != null) counterValue + 1 else 1
                }
            }

            //Gets the consonant with most occurrences in the sentence
            val maxBy: Map.Entry<String, Int>? = alliterationCounter.maxBy { it.value }
            val alliteration = maxBy?.key
            val percentage: Float? = if (maxBy != null) (maxBy.value.toFloat() / tokens.size.toFloat()) * 100 else 0F

            //Returns the
            return String.format("Alliteration percentage, %s, %.2f", alliteration, percentage)
        } else {
            return String.format("Sentence was not provided")
        }
    }
}