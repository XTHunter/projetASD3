package projetCameleon;

import java.awt.Point;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quadtree q = new Quadtree(2);
		q.createBoard(q.board);
		
		q.changeColor(q.board, 10, 10, 'R');
		Point p= q.recherchePointGlouton(q.board, new Point(0,0));
		q.changeColor(q.board, 10, 1, 'B');
		q.changeColor(q.board, 4, 11, 'B');
		q.changeColor(q.board, 12, 12, 'R');
		System.out.println(q.showColor(q.board, 10, 10));
		System.out.println( q.board.dd.casesjoue);
		//q.showBoard(q.board);
		
		
		
		
			
		

	}

	

}
