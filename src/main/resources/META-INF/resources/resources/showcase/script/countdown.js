// Global variables to store the remaining time and the interval ID for the countdown
var timeLeft;
var countdownInterval;

/**
 * Start a countdown timer and display it in a specified div.
 * @param {number} durationInMilliseconds - Total duration of the countdown.
 * @param {string} divId - ID of the div where the countdown should be displayed.
 * @param {string} dialogWidgetVar - PrimeFaces widget variable for the dialog to be displayed.
 * @param {string} buttonId - ID of the button to trigger when the countdown expires.
 */
function startCountdown(durationInMilliseconds, divId, dialogWidgetVar, buttonId) {
    timeLeft = durationInMilliseconds;

    // Clear any existing countdown intervals
    if (countdownInterval) {
        clearInterval(countdownInterval);
    }

    // Display the dialog right when the countdown starts
    PF(dialogWidgetVar).show();

    // Start the countdown interval, updating every second
    countdownInterval = setInterval(function () {
        timeLeft -= 1000; // decrement by 1000 ms, which is 1 second

        // Calculate hours, minutes, and seconds from the remaining milliseconds
        var hours = parseInt((timeLeft / (1000 * 60 * 60)), 10);
        var minutes = parseInt(((timeLeft % (1000 * 60 * 60)) / (1000 * 60)), 10);
        var seconds = parseInt((timeLeft % (1000 * 60)) / 1000, 10);

        // Format the time values with leading zeros if needed
        hours = hours < 10 ? "0" + hours : hours;
        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        // Compile the formatted time string
        var formattedTime = (hours !== "00" ? hours + ":" : "") + minutes + ":" + seconds;

        // Update the countdown display with the formatted time
        document.getElementById(divId).innerHTML = formattedTime;

        // When the countdown reaches zero
        if (timeLeft <= 0) {
            clearInterval(countdownInterval);
            // Programmatically trigger the specified commandButton's click event (e.g., to logout)
            document.getElementById(buttonId).click();
            // Close the PrimeFaces dialog when timer expires
            PF(dialogWidgetVar).hide();
        }
    }, 1000); // this will make sure the interval fires every 1 second
}

/**
 * Stop the countdown timer and hide the associated dialog.
 * @param {string} divId - ID of the div where the countdown is displayed.
 * @param {string} dialogWidgetVar - PrimeFaces widget variable for the dialog to be hidden.
 */
function stopCountdown(divId, dialogWidgetVar) {
    // Clear the countdown interval
    clearInterval(countdownInterval);
    // Clear the countdown display in the specified div
    document.getElementById(divId).innerHTML = "";
    // Close the PrimeFaces dialog
    PF(dialogWidgetVar).hide();
}

