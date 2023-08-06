import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
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
    }

    private void generateSecretNumber() {
        Random random = new Random();
        secretNumber = random.nextInt(MAX_NUMBER) + 1;
    }

    public void init() {
        generateSecretNumber();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        remainingGuesses = MAX_GUESSES;
        userScore = 0;
        generateSecretNumber();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Guessing Game</title></head><body>");
        out.println("<h1>Welcome to the Guessing Game!</h1>");
        out.println("<p>Try to guess the number between 1 and 100.</p>");
        out.println("<input type='number' id='userGuess'>");
        out.println("<button onclick='makeGuess()'>Make Guess</button>");
        out.println("<p id='message'></p>");
        out.println("<p>Remaining Guesses: <span id='remainingGuesses'>" + remainingGuesses + "</span></p>");
        out.println("<p>Current Score: <span id='userScore'>" + userScore + "</span></p>");
        out.println("<script src='script.js'></script>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userGuess = Integer.parseInt(request.getParameter("userGuess"));
        remainingGuesses--;
        String message = "";

        if (userGuess == secretNumber) {
            userScore++;
            message = "Congratulations! You guessed the correct number.";
            generateSecretNumber();
            remainingGuesses = MAX_GUESSES;
        } else if (remainingGuesses == 0) {
            message = "Sorry, you've used all your guesses. The correct number was: " + secretNumber;
            generateSecretNumber();
            remainingGuesses = MAX_GUESSES;
        } else if (userGuess < secretNumber) {
            message = "Too low. Try again.";
        } else {
            message = "Too high. Try again.";
        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.print(message + "|" + remainingGuesses + "|" + userScore);
        out.flush();
    }
}
