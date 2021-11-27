package projetCameleon;

import java.util.Scanner;


public class Moi{
	
	private boolean inInterval(Quadtree q, int x, int y) {
		return (x<q.dim(q.board) && x>1) && (y<q.dim(q.board) && y>1);
	}
	
	

	
	public void jouerBrave(Quadtree q, int x, int y) {
		// TODO Auto-generated method stub
		if(inInterval(q,x,y)) {
			if(q.showColor(q.board, x, y) == 'A') {
				q.changeColor(q.board, x, y, 'R');
				
				if(q.showColor(q.board, x+1, y) == 'B' && q.inRange(q.board,x+1,y)) {
					q.changeColor(q.board, x+1, y, 'R');
				}
				if(q.showColor(q.board, x+1, y+1) == 'B' && q.inRange(q.board,x+1,y+1)) {
					q.changeColor(q.board, x+1, y+1, 'R');
				}
				if(q.showColor(q.board, x, y+1) == 'B' && q.inRange(q.board,x,y+1)) {
					q.changeColor(q.board, x, y+1, 'R');
				}
				if(q.showColor(q.board, x-1, y+1) == 'B' && q.inRange(q.board,x-1,y+1)) {
					q.changeColor(q.board, x-1, y+1, 'R');
				}
				if(q.showColor(q.board, x-1, y) == 'B' && q.inRange(q.board,x-1,y)) {
					q.changeColor(q.board, x-1, y, 'R');
				}
				if(q.showColor(q.board, x-1, y-1) == 'B' && q.inRange(q.board,x-1,y-1)) {
					q.changeColor(q.board, x+1, y-1, 'R');
				}
				if(q.showColor(q.board, x, y-1) == 'B' && q.inRange(q.board,x,y-1)) {
					q.changeColor(q.board, x, y-1, 'R');
				}
				if(q.showColor(q.board, x+1, y-1) == 'B' && q.inRange(q.board,x+1,y-1)) {
					q.changeColor(q.board, x+1, y-1, 'R');
				}
				
			} else {
				@SuppressWarnings("resource")
				Scanner coo = new Scanner(System.in);
				System.out.println("L'adversiare a deja colorié cette case.");
				System.out.println("Choisir un autre point avec des coordonnées entre 1 et "+ q.dim(q.board));
				System.out.println("x: ");
				int x2 = coo.nextInt();
				System.out.println("y: ");
				int y2 = coo.nextInt();
				jouerBrave(q,x2, y2);
				return;
			}
			
		} else {
			@SuppressWarnings("resource")
			Scanner coo = new Scanner(System.in);
			System.out.println("point est hors limite.");
			System.out.println("Choisir un autre point avec des coordonnées entre 1 et "+ q.dim(q.board));
			System.out.println("x: ");
			int x2 = coo.nextInt();
			System.out.println("y: ");
			int y2 = coo.nextInt();
			jouerBrave(q,x2, y2);
			return;
		}
		
	}

	
	public void jouerTemeraire(Quadtree q, int x, int y) {
		// TODO Auto-generated method stub
		if(inInterval(q,x,y)) {
			if(q.showColor(q.board, x, y) == 'A') {
				q.changeColor(q.board, x, y, 'R');
				
				if(q.showColor(q.board, x+1, y) == 'B' && q.pasAcquis(q.board, x+1, y) && q.inRange(q.board,x+1,y)) {
					q.changeColor(q.board, x+1, y, 'R');
				}
				if(q.showColor(q.board, x+1, y+1) == 'B' && q.pasAcquis(q.board, x+1, y+1) && q.inRange(q.board,x+1,y+1)) {
					q.changeColor(q.board, x+1, y+1, 'R');
				}
				if(q.showColor(q.board, x, y+1) == 'B' && q.pasAcquis(q.board, x, y+1) && q.inRange(q.board,x,y+1)) {
					q.changeColor(q.board, x, y+1, 'R');
				}
				if(q.showColor(q.board, x-1, y+1) == 'B' && q.pasAcquis(q.board, x-1, y+1) && q.inRange(q.board,x-1,y+1)) {
					q.changeColor(q.board, x-1, y+1, 'R');
				}
				if(q.showColor(q.board, x-1, y) == 'B' && q.pasAcquis(q.board, x-1, y) && q.inRange(q.board,x-1,y)) {
					q.changeColor(q.board, x-1, y, 'R');
				}
				if(q.showColor(q.board, x-1, y-1) == 'B' && q.pasAcquis(q.board, x-1, y-1) && q.inRange(q.board,x-1,y-1)) {
					q.changeColor(q.board, x+1, y-1, 'R');
				}
				if(q.showColor(q.board, x, y-1) == 'B' && q.pasAcquis(q.board, x, y-1) && q.inRange(q.board,x,y-1)) {
					q.changeColor(q.board, x, y-1, 'R');
				}
				if(q.showColor(q.board, x+1, y-1) == 'B' && q.pasAcquis(q.board, x+1, y-1) && q.inRange(q.board,x+1,y-1)) {
					q.changeColor(q.board, x+1, y-1, 'R');
				}
				
			} else {
				@SuppressWarnings("resource")
				Scanner coo = new Scanner(System.in);
				System.out.println("L'adversiare a deja colorié cette case.");
				System.out.println("Choisir un autre point avec des coordonnées entre 1 et "+ q.dim(q.board));
				System.out.println("x: ");
				int x2 = coo.nextInt();
				System.out.println("y: ");
				int y2 = coo.nextInt();
				jouerTemeraire(q,x2, y2);
				return;
			}
			
		} else {
			@SuppressWarnings("resource")
			Scanner coo = new Scanner(System.in);
			System.out.println("point est hors limite.");
			System.out.println("Choisir un autre point avec des coordonnées entre 1 et "+ q.dim(q.board));
			System.out.println("x: ");
			int x2 = coo.nextInt();
			System.out.println("y: ");
			int y2 = coo.nextInt();
			jouerTemeraire(q,x2, y2);
			return;
		}
	
	}

	
	public int maScore(Quadtree q) {
		return q.showNumColor(q.board, 'R') - q.showNumColor(q.board, 'B');
	}
	
	

}
