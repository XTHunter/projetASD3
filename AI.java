package projetCameleon;

import java.awt.Point;

public class AI{
	
	public void jouerGloutonBrave(Quadtree q, int x, int y) {
		Point zero= new  Point(0,0);
		Point p = q.recherchePointGlouton(q.board, zero);
		q.changeColor(q.board, p.x, p.y, 'B');
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
	}

	
	public void jouerGloutonTemeraire(Quadtree q, int x, int y) {
		Point zero= new  Point(0,0);
		Point p = q.recherchePointGloutonT(q.board, zero);
		q.changeColor(q.board, p.x, p.y, 'B');
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
		
	}

	
	public void jouerAITemeraire(Quadtree q, int x, int y) {
		
	}
	
	
	public int score(Quadtree q) {
		return q.showNumColor(q.board, 'B') - q.showNumColor(q.board, 'R');
	}
	
}
