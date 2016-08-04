import java.util.Scanner;

class Player {
		public int pointScore;
		public int gameScore;
		public int setScore;
		String playerName;
		public Player(String name) {
			pointScore=0;
			gameScore=0;
			setScore=0;
			playerName = name;
		}
		public int getPointScore() {
			return pointScore;
		}
		public int getGameScore() {
			return gameScore;
		}
		public int getSetScore() {
			return setScore;
		}
		public String getName() {
			return playerName;
		}
		public void resetPointScore() {
			pointScore=0;
		}
		public void resetGameScore() {
			gameScore = 0;
			resetPointScore();
		}
		
}
class ScoreBoard {
	
	static int[] scores={0,15,30,40};
	
	Player player1,player2;
	public ScoreBoard(String Player1, String Player2) {
		player1= new Player(Player1);
		player2= new Player(Player2);
	}
	public static void main(String[] args) {
		ScoreBoard match = new ScoreBoard("A","B");
		Scanner input = new Scanner(System.in);
		String Game = input.nextLine();
		match.calculateScore(Game);
		
	}
	
	public void calculateScore(String points){
		String currentPoint;
		displayScore();
		for(int i=0;i<points.length();i++){
			
			currentPoint=points.charAt(i)+"";
			updatePoint(currentPoint,player1,player2);
			displayScore();
		}
		
	}
	
	public void updatePoint(String currentPoint, Player A, Player B){
		if(currentPoint.equals(A.getName())){
			A.pointScore++;
		}
		else{
			B.pointScore++;
		}
		updateGame(A,B);
	}
	
	public void updateGame(Player A, Player B){
		if(A.pointScore>=4&&A.pointScore-B.pointScore>=2){
			A.gameScore++;
			A.resetPointScore();
			B.resetPointScore();
			updateSet(A,B);
		}
		else if(B.pointScore>=4&&B.pointScore-A.pointScore>=2){
			B.gameScore++;
			A.resetPointScore();
			B.resetPointScore();
			updateSet(A,B);
		}
	}
	
	public void updateSet(Player A, Player B){
		if(A.gameScore>=6&&A.gameScore-B.gameScore>=2){
			A.setScore++;

			A.resetGameScore();
			B.resetGameScore();
		}
		else if(B.gameScore>=4&&B.gameScore-A.gameScore>=2){
			B.setScore++;

			A.resetGameScore();
			B.resetGameScore();
		}
	}
    public Player getLeadPlayer() {
    	if(player1.getSetScore()==player2.getSetScore()) {
    		if(player1.getGameScore()==player2.getGameScore()) {
    			if(player1.getPointScore()==player2.getPointScore()) {
    	        	return null;
            	}
            	else if(player1.getPointScore()>player2.getPointScore()) {
            		return player1;
            	}
            	else {
            		return player2;
            	}
        	}
        	
        	else if(player1.getGameScore()>player2.getGameScore()) {
        		return player1;
        	}
        	else {
        		return player2;
        	}
    	}
    	else if(player1.getSetScore()>player2.getSetScore()) {
    		return player1;
    	}
    	else {
    		return player2;
    	}
    }
	public void displayScore() {
		System.out.println("Set score:" +player1.getSetScore()+"-"+player2.getSetScore());
		System.out.println("Game score:" +player1.getGameScore()+"-"+player2.getGameScore());
		if(player1.getPointScore()>=3 && player2.getPointScore()>=3) {
			if(player1.getPointScore()==player2.getPointScore()) {
				System.out.println("Point score: Deuce");
			}
			else if(player1.getPointScore()>player2.getPointScore()){
				System.out.println("Point score: Advantage - "+player1.getName());
			}
			else {
				System.out.println("Point score: Advantage - "+player2.getName());
			}
		} else {
			System.out.println("Point score:" +scores[player1.getPointScore()]+"-"+scores[player2.getPointScore()]);
		}
		if(getLeadPlayer()!=null) {
			System.out.println("Leading Player: "+getLeadPlayer().getName());
		}
		System.out.println("----------------------------------------");
	}
}