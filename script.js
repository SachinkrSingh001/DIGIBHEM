document.addEventListener("DOMContentLoaded", function () {
    const maxGuesses = 10;
    let secretNumber, remainingGuesses, userScore;

    const guessInput = document.getElementById("user-guess");
    const feedback = document.getElementById("feedback");
    const remainingGuessesSpan = document.getElementById("remaining-guesses");
    const userScoreSpan = document.getElementById("user-score");
    const guessBtn = document.getElementById("guess-btn");
    const playAgainBtn = document.getElementById("play-again-btn");

    function generateSecretNumber() {
        secretNumber = Math.floor(Math.random() * 100) + 1;
    }

    function initializeGame() {
        generateSecretNumber();
        remainingGuesses = maxGuesses;
        feedback.textContent = "";
        remainingGuessesSpan.textContent = remainingGuesses;
        guessInput.value = "";
        guessInput.disabled = false;
        guessBtn.disabled = false;
        playAgainBtn.style.display = "none";
    }

    function displayFeedback(message) {
        feedback.textContent = message;
    }

    function displayScore() {
        userScoreSpan.textContent = userScore;
    }

    function checkGuess() {
        const userGuess = parseInt(guessInput.value);
        remainingGuesses--;

        if (userGuess === secretNumber) {
            displayFeedback("Congratulations! You guessed the correct number.");
            userScore++;
            guessInput.disabled = true;
            guessBtn.disabled = true;
            playAgainBtn.style.display = "block";
        } else if (userGuess < secretNumber) {
            displayFeedback("Too low. Try again.");
        } else {
            displayFeedback("Too high. Try again.");
        }

        remainingGuessesSpan.textContent = remainingGuesses;

        if (remainingGuesses === 0) {
            displayFeedback(`Sorry, you've used all your guesses. The correct number was: ${secretNumber}`);
            guessInput.disabled = true;
            guessBtn.disabled = true;
            playAgainBtn.style.display = "block";
        }
    }

    guessBtn.addEventListener("click", checkGuess);
    playAgainBtn.addEventListener("click", initializeGame);

    initializeGame();
});
