package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPos = 0
    var wrongPos = 0
    val constant = '0'
    var secretChars = secret.toCharArray()
    var guessChars = guess.toCharArray()

    for (i in guess.indices) {
        if (secretChars[i] == guessChars[i]) {
            rightPos++
            secretChars[i] = constant
            guessChars[i] = constant
        }
    }

    for (i in guess.indices) {
        if (guessChars[i] == constant) {
            continue
        }
        for (j in secret.indices) {
            if (guessChars[i] == secretChars[j]) {
                wrongPos++
                secretChars[j] = '0'
                break;
            }
        }
    }

    return Evaluation(rightPos, wrongPos)
}
