import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GuessingGameServlet")
public class GuessingGameServlet extends HttpServlet {
    private static final int MAX_NUMBER = 100;
    private static final int MAX_GUESSES = 10;
    private int secretNumber;
    private int remainingGuesses;
    private int userScore;

    public GuessingGameServlet() {
        userScore = 0;
        generateSecretNumber();
    }

    private void generateSecretNumber() {
        Random random = new Random();
        secretNumber = random.nextInt(MAX_NUMBER) + 1;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        remainingGuesses = MAX_GUESSES;
        userScore = 0;
        generateSecretNumber();
        request.setAttribute("remainingGuesses", remainingGuesses);
        request.setAttribute("userScore", userScore);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userGuess = Integer.parseInt(request.getParameter("userGuess"));
        remainingGuesses--;

        if (userGuess == secretNumber) {
            userScore++;
            generateSecretNumber();
            remainingGuesses = MAX_GUESSES;
            request.setAttribute("message", "Congratulations! You guessed the correct number.");
        } else if (remainingGuesses == 0) {
            generateSecretNumber();
            remainingGuesses = MAX_GUESSES;
            request.setAttribute("message", "Sorry, you've used all your guesses. The correct number was: " + secretNumber);
        } else if (userGuess < secretNumber) {
            request.setAttribute("message", "Too low. Try again.");
        } else {
            request.setAttribute("message", "Too high. Try again.");
        }

        request.setAttribute("remainingGuesses", remainingGuesses);
        request.setAttribute("userScore", userScore);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
