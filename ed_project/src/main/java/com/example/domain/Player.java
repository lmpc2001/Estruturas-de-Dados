public class Player {
	private String playerName;
	private Bot[] playerBots;

	public Player(String playerName, Bot[] playerBots) {
		this.playerName = playerName;
		this.playerBots = playerBots;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Bot[] getPlayerBots() {
		return playerBots;
	}

	public void setPlayerBots(Bot[] playerBots) {
		this.playerBots = playerBots;
	}

	public String toString() {
		return "Player {" +
			"Name: " + this.playerName + "\n" +
			"Bots: " + this.playerBots + "\n" +
		"}";
	}
}
