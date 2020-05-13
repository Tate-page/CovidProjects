
public class GameChess {
	private String[][] chessboard;
	int BlackTurn1WhiteTurn0;
	boolean gameFinish;
	
	GameChess(){//LOADS CHESSBOARD INTO STRING[][] AND TURN STARTS WITH WHITE WITH INT 0
		chessboard=new String[8][8];
		BlackTurn1WhiteTurn0=0;
		gameFinish=false;
		for(int i=0;i<8;i++) 
		{
			for(int j=0;j<8;j++) 
			{
				if(i==6)
					chessboard[i][j]="WP";
				else if(i==1)
					chessboard[i][j]="BP";
				else if(i==0) 
				{
					if(j==0||j==7)
						chessboard[i][j]="BR";
					if(j==1||j==6)
						chessboard[i][j]="Bk";
					if(j==2||j==5)
						chessboard[i][j]="BB";
					if(j==3)
						chessboard[i][j]="BQ";
					if(j==4)
						chessboard[i][j]="BK";
				}
				else if(i==7) 
				{
					if(j==0||j==7)
						chessboard[i][j]="WR";
					if(j==1||j==6)
						chessboard[i][j]="Wk";
					if(j==2||j==5)
						chessboard[i][j]="WB";
					if(j==3)
						chessboard[i][j]="WQ";
					if(j==4)
						chessboard[i][j]="WK";
				}
				else
					chessboard[i][j]="--";
			}
		}
	}
	
	void displayBoard() {//DISPLAYS BOARD DONE
		for(int i=0;i<8;i++) 
		{
			System.out.printf("%10s", i);
		}
		System.out.println();
		System.out.println("   ---------------------------------------------------------------------------------");
		
		for(int i=0;i<8;i++) {
			System.out.print(i+"  |");
			for(int j=0;j<8;j++) {
				System.out.printf("%5s", chessboard[i][j]);
				System.out.printf("%5s","|");
			}
			System.out.println();
			System.out.println("   ---------------------------------------------------------------------------------");
		}
	}
	
	void movePieces(int x1, int y1, int x2, int y2) {
		if(rulebook(x1,y1,x2,y2)==true) {
			this.chessboard[y2][x2]=this.chessboard[y1][x1];//moves piece
			this.chessboard[y1][x1]="--";//fills original position with blank
			
			if(this.BlackTurn1WhiteTurn0==0)//changes the turn
				this.BlackTurn1WhiteTurn0=1;
			else
				this.BlackTurn1WhiteTurn0=0;
		}
		else {
			System.out.println("Sorry that's not allowed");
		}
	}
	
	boolean rulebook(int x1, int y1, int x2, int y2) {
		boolean ans=false;
		if(this.rightColor(x1, y1, x2, y2)) {//checks if taking piece
			if(chessboard[y1][x1].charAt(1)=='P') {
				if(this.rightMovePawn(x1,y1,x2,y2)) {//checks if pawn is moving correctly
					ans=true;
				}
			}
			if(chessboard[y1][x1].charAt(1)=='R') {
				if(this.rightMoveRook(x1,y1,x2,y2)) 
					ans=true;
			}
			if(chessboard[y1][x1].charAt(1)=='k') {
				if(this.rightMoveKnight(x1, y1, x2, y2)) 
					ans=true;
			}
			if(chessboard[y1][x1].charAt(1)=='B') {
				if(this.rightMoveBishop(x1, y1, x2, y2))
					ans=true;
			}
			if(chessboard[y1][x1].charAt(1)=='Q') {
				if(this.rightMoveBishop(x1, y1, x2, y2) || this.rightMoveRook(x1, y1, x2, y2))//makes sense to me
					ans=true;
			}
			if(chessboard[y1][x1].charAt(1)=='K') {
				if(this.rightMoveKing(x1, y1, x2, y2))
					ans=true;
			}
		}
		
		return ans;
	}
	
	boolean rightColor(int x1, int y1, int x2, int y2) {//checks if taking piece with same color and if you are moving the correct piece
		boolean ans=false;
		if(this.chessboard[y1][x1].charAt(0)!=this.chessboard[y2][x2].charAt(0) || this.chessboard[y2][x2]=="--") {
			if(this.BlackTurn1WhiteTurn0==0 && this.chessboard[y1][x1].charAt(0)=='W') {//checks if white if moving white
				ans=true;
			}
			else if(this.BlackTurn1WhiteTurn0==1 && this.chessboard[y1][x1].charAt(0)=='B')//checks if black is moving black
				ans=true;
			}
		return ans;
	}
	
	boolean rightMovePawn(int x1, int y1, int x2, int y2) {//needs to add pawn to queen conversion
		boolean ans=false;
		if(x1!=x2) {//if pawn is taking a piece
			if(this.chessboard[y1][x1].charAt(0)!=this.chessboard[y2][x2].charAt(0)&&chessboard[y2][x2]!="--")//checks if they're taking a different piece
				if(this.BlackTurn1WhiteTurn0==0) {
					if(y1-y2==1 && Math.abs(x2-x1)==1)//checks if white pawn is moving diagonally upward by one step
						ans=true;
				}
				else {
					if(y2-y1==1 && Math.abs(x2-x1)==1)//checks if black pawn is moving diagonally downward by one step
						ans=true;
				}
		}
		else if(x1==x2){//if pawn is moving without taking a piece it checks if it is moving straight
			if(this.BlackTurn1WhiteTurn0==0) {//checks if white
				if(y1==6 && y1-y2==2)//if white pawn is still in starting position
					ans=true;
				if(y1-y2==1)//if white pawn isn't in starting position
					ans=true;
			}
			else {//checks if black
				if(y1==1 && y2-y1==2)//if black pawn is in starting position
					ans=true;
				if(y2-y1==1)//if black pawn isn't in starting position
					ans=true;
			}
		}
		return ans;
	}
	
	boolean rightMoveRook(int x1, int y1, int x2, int y2) {
		boolean ans=false;
			if(x1==x2 && y2-y1!=0) {//if rook is moving up or down
				if(y2>y1) {//if moving down
					
					boolean isClear=true;
					for(int i=y1+1;i<y2;i++) {//checks if spaces between are empty spaces
						if(this.chessboard[i][x1]!="--") {
							isClear=false;
							break;
						}
						
					}
					if(isClear && this.chessboard[y2][x2].charAt(0)!=this.chessboard[y1][x1].charAt(0))//checks if path is clear and the last piece is placeable
						ans=true;
					
				}
				else {//if moving up
					boolean isClear=true;
					for(int i=y1-1;i>y2;i--) {
						if(this.chessboard[i][x1]!="--") {
							isClear=false;
							break;
						}
						if(i==y2)
							ans=true;
					}
					if(isClear && this.chessboard[y2][x2].charAt(0)!=this.chessboard[y1][x1].charAt(0))//checks if path is clear and the last piece is placeable
						ans=true;
				}
			}
			if(y1==y2 && x2-x1!=0) {//if rook is moving left or right
				if(x2>x1) {//if rook is moving to the right
					boolean isClear=true;
					for(int i=x1+1;i<x2;i++) {
						if(this.chessboard[y1][i]!="--") {
							isClear=false;
							break;
						}
						if(i==y2)
							ans=true;
					}
					if(isClear && this.chessboard[y2][x2].charAt(0)!=this.chessboard[y1][x1].charAt(0))//checks if path is clear and the last piece is placeable
						ans=true;
				}
				else {//if rook is moving to the left
					boolean isClear=true;
					for(int i=x1-1;i>x2;i--) {
						if(this.chessboard[y1][i]!="--") {
							isClear=false;
							break;
							}
						if(i==y2)
							ans=true;
					}
					if(isClear && this.chessboard[y2][x2].charAt(0)!=this.chessboard[y1][x1].charAt(0))//checks if path is clear and the last piece is placeable
						ans=true;
				}
			}
		return ans;
	}
	
	boolean rightMoveKnight(int x1, int y1, int x2, int y2) {
		boolean ans=false;
		if((Math.abs(x1-x2)==2 && Math.abs(y1-y2)==1) || (Math.abs(x1-x2)==1 && Math.abs(y1-y2)==2))
			ans=true;
		return ans;
	}

	boolean rightMoveBishop(int x1, int y1, int x2, int y2) {
		boolean ans=false;
		if(Math.abs(x2-x1)==Math.abs(y2-y1)) {//checks if the x and y move an equal ammount
			if(y2>y1) {//if bishop is moving down
				if(x2>x1) {//if moving down and to the right
					boolean isClear=true;
					int tempY=y1+1;
					for(int tempX=x1+1;tempX<x2;tempX++) {
						if(this.chessboard[tempY][tempX]!="--") {
							isClear=false;
							break;
						}
						tempY++;
					}
					if(isClear==true && this.chessboard[y1][x1].charAt(0)!=this.chessboard[y2][x2].charAt(0))
						ans=true;	
				}
				if(x1>x2) {//if moving down and to the left
					boolean isClear=true;
					int tempY=y1+1;
					for(int tempX=x1-1;tempX>x2;tempX--) {
						if(this.chessboard[tempY][tempX]!="--") {
							isClear=false;
							break;
						}
						tempY++;
					}
					if(isClear==true && this.chessboard[y1][x1].charAt(0)!=this.chessboard[y2][x2].charAt(0))
						ans=true;
				}
			}
			else {//if bishop is moving up
				if(x2>x1) {//if bishop is moving up and to right
					boolean isClear=true;
					int tempY=y1-1;
					for(int tempX=x1+1;tempX<x2;tempX++) {
						if(this.chessboard[tempY][tempX]!="--") {
							isClear=false;
							break;
						}
						tempY--;
					}
					if(isClear==true && this.chessboard[y1][x1].charAt(0)!=this.chessboard[y2][x2].charAt(0))
						ans=true;
				}
				if(x1>x2) {//if bishop is moving up and to the left
					boolean isClear=true;
					int tempY=y1-1;
					for(int tempX=x1-1;tempX>x2;tempX--) {
						if(this.chessboard[tempY][tempX]!="--") {
							isClear=false;
							break;
						}
						tempY--;
					}
					tempY--;
					if(isClear==true && this.chessboard[y1][x1].charAt(0)!=this.chessboard[y2][x2].charAt(0))
						ans=true;
				}
			}
		}
		return ans;
	}

	boolean rightMoveKing(int x1, int y1, int x2, int y2) {
		boolean ans=false;
		if(Math.abs(x2-x1)<2 && Math.abs(y2-y1)<2)
			ans=true;
		return ans;
	}
}
