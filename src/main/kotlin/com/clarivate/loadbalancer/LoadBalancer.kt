package com.clarivate.loadbalancer

/**
 * Load balancer class.
 *
 * @author Danilo Acosta
 */
class LoadBalancer {

    /**
     * Get the highest alliteration of a sentence.
     *
     * @param request Any [IntArray] containing the values for the request.
     * @return [Boolean] if the request can be split in three consecutive groups with the same added value
     */
    fun checkDistribution(request: IntArray): Boolean {
        //Checks if there are enough tasks to distribute
        if (request.size >= 5) {
            var groupOne = request[0]
            var groupTwo = 0
            var groupThree = request[request.size - 1]

            var leftIndex = 1
            var rightIndex = request.size - 2
            var auxIndex = -1

            // Loop until there are not more values in the request
            while (auxIndex != rightIndex) {
                // Loop until first and last group are equal or indexes surpass the mid
                while (groupOne != groupThree && leftIndex + 2 != rightIndex) {
                    if (groupOne < groupThree) {
                        leftIndex++
                        groupOne += request[leftIndex - 1]
                    } else {
                        rightIndex--
                        groupThree += request[rightIndex + 1]
                    }
                }

                // Sets index for second group if it has not been set
                if (auxIndex == -1)
                    auxIndex = leftIndex + 1

                // Sums values for second group
                groupTwo += request[auxIndex]
                auxIndex++

                // If second group surpasses first and third group,
                // adds a value to the first group and delete one from the second
                // This will recalculate first and second group, until they match again
                if (groupTwo > groupOne) {
                    groupOne += request[leftIndex]
                    leftIndex++
                    groupTwo -= request[leftIndex]
                }

            }

            if (groupOne == groupTwo && groupTwo == groupThree)
                return true
            return false
        }
        return false
    }
}