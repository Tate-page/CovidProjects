import java.util.Scanner;

public class TestGameChess {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		GameChess cb1= new GameChess();
		cb1.displayBoard();
		
		while(cb1.gameFinish==false) {//when gameFinish is true the game is finished
			if(cb1.BlackTurn1WhiteTurn0==0)
				System.out.println("It's white's turn");
			else
				System.out.println("It's blacks turn");
			System.out.println("Enter the x-coordinate for the piece you want to move");
			int x1=input.nextInt();
			System.out.println("Enter the y-coordinate for the piece you want to move");
			int y1=input.nextInt();
			System.out.println("Enter the x-coordinate for the position you want to move to");
			int x2=input.nextInt();
			System.out.println("Enter the y-coordinate for the position you want to move to");
			int y2=input.nextInt();
			cb1.movePieces(x1, y1, x2, y2);
			cb1.displayBoard();
		}

	}

	/*public static void MovePieces() {
		
		
		
	}*/
}




