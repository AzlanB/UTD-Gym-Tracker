// leaderboardScript.js

function navigateToHomePage() {
    // Assuming "HomePage.html" is in the same directory
    window.location.href = "HomePage.html";
}

function showLeaderboard(selectedLeaderboard) {
    // Get the leaderboard container
    const leaderboardContainer = document.getElementById('leaderboardContainer');

    // Clear existing content
    leaderboardContainer.innerHTML = '';

    // Create new content based on the selected leaderboard
    const leaderboardTitle = document.createElement('h2');
    leaderboardTitle.textContent = `${selectedLeaderboard} Leaderboard`;

    const leaderboardTable = document.createElement('table');
    leaderboardTable.innerHTML = `
        <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Weight Lifted (lbs)</th>
            </tr>
        </thead>
        <tbody>
            <!-- Add dynamic content based on the selected leaderboard -->
        </tbody>
    `;

    // Append elements to the leaderboard container
    leaderboardContainer.appendChild(leaderboardTitle);
    leaderboardContainer.appendChild(leaderboardTable);
}
