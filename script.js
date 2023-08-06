function makeGuess() {
    var userGuess = document.getElementById('userGuess').value;

    if (userGuess === '') {
        alert('Please enter a valid number.');
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = xhr.responseText;
            var message = response.split('|')[0];
            var remainingGuesses = response.split('|')[1];
            var userScore = response.split('|')[2];

            document.getElementById('message').innerHTML = message;
            document.getElementById('remainingGuesses').innerText = remainingGuesses;
            document.getElementById('userScore').innerText = userScore;
        }
    };
    xhr.open('POST', 'GuessingGameServlet', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send('userGuess=' + userGuess);
}
