package projetCameleon;

import java.awt.Point;

//tout les methodes sont fonctionelles
//pour créer une gille de 3*(2^k) regions a l'aide d'une arbre quadratique(arbre complet avec des feulles qui sont des tableaux
public class Quadtree{
  

  	public class Node{
  		char couleur;
  		char[] tab;
  		int hauteur, casesjoue;
  		Node uu,ud,dd,du;
  		
  		Node(int h, char c){
  			hauteur=h;
  			couleur=c;
  			casesjoue=0;
  			tab=null;
  			uu=null;
  			ud=null;
  			dd=null;
  			du=null;
  		}
  	}
  	
  	public Node board;
  	
  	public Quadtree(int i){
  		board=new Node(i, 'A');
  	}
  	
  	//pour inserer un noeud
  	private Node insert(Node n, int h, char c) {
  		if(n==null) {
  			n=new Node(h,c);
  		}
  		return n;
  	}
  	
  
  	//pour ajouter 4 fils
  	private void addSubNodes(Node n) {
  		if (n.uu==null && n.ud==null && n.dd==null && n.du==null) {
  			n.uu=insert(n.uu, n.hauteur-1, n.couleur);
  			n.ud=insert(n.ud, n.hauteur-1, n.couleur);
  			n.dd=insert(n.dd, n.hauteur-1, n.couleur);
  			n.du=insert(n.du, n.hauteur-1, n.couleur);
  		}
  	}
  	
  	//ajouter un feuill (
  	private void addLastRegion(Node n) {
  		if(n.tab==null) {
  			n.tab=new char[] {'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'};
  		}
  	}
  	
  	//pour céer le plateau du jeu
  	public void createBoard(Node n) {
  		if(n.hauteur==0) {
  			addLastRegion(n);
  		}else {
  			addSubNodes(n);
  			createBoard(n.uu);
  			createBoard(n.ud);
  			createBoard(n.dd);
  			createBoard(n.du);
  		}
  	}
  	
  	//calculer 3*(2^k)
  	public int dim(Node n) {
  		return (int)(3*Math.pow(2, (double)(n.hauteur)));
  	}
  	
  	//pour donner le couleur du case (x,y)
  	public char showColor(Node n, int x, int y) {
  		char f = 0;
  		if(n.hauteur==0) {
  			f=n.tab[(y-1)*3+(x-1)];
  		}else {
  			if(x<=dim(n)/2 && y<=dim(n)/2) {
  				return showColor(n.uu, x,y);
  			}
  			if(x<=dim(n)/2 && y>dim(n)/2) {
  				return showColor(n.ud, x,y-(dim(n)/2));
  			}
  			if(x>dim(n)/2 && y>dim(n)/2) {
  				return showColor(n.dd, x-(dim(n)/2),y-(dim(n)/2));
  			}
  			if(x>dim(n)/2 && y<=dim(n)/2) {
  				return showColor(n.du, x-(dim(n)/2),y);
  			}
  		}
  		return f;
  	}
  	
  	//pour afficher le plateau du jeux
  	public void showBoard(Node n) {
  		for(int i=1; i<=dim(n); i++) {//y
  			for(int j=1; j<=dim(n); j++) {
  				if(j==dim(n)) {
  					System.out.println(showColor(n, j,i));
  				}else {
  					System.out.print(showColor(n, j,i));
  				}
  			}
  		}
  	}
  	
  	
  	
  	
  	//pour changer le couleur du case (x,y)
  	public void changeColor(Node n, int x, int y, char c) {
   		if(n.hauteur==0) {
  			if (showColor(n, x, y)=='A') {
  	  			n.casesjoue++;
  	  		}
  			n.tab[(y-1)*3+(x-1)]=c;
  			return;
  		}else {
  			n.casesjoue++;
  			if(x<=dim(n)/2 && y<=dim(n)/2) {
  				changeColor(n.uu, x,y,c);
  			} else {
  				if(x<=dim(n)/2 && y>dim(n)/2) {
  	  				changeColor(n.ud, x,y-(dim(n)/2),c);
  	  				
  	  			} else {
  	  				if(x>dim(n)/2 && y>dim(n)/2) {
  	  					changeColor(n.dd, x-(dim(n)/2),y-(dim(n)/2),c);
  	  				
  	  				}else {
  	  					changeColor(n.du, x-(dim(n)/2),y,c);
	  				
  	  				}
  	  			}	
  			}	
  		}
  	}
  	
  	//pour calculer le nombre des c existant dans le plateua du jeux ("le score")
  	public int showNumColor(Node n, char c) {
  		int result=0;
  		for(int i=1; i<=dim(n); i++) {//y
  			for(int j=1; j<=dim(n); j++) {
  				if(showColor(n,j,i)==c) {
  					result++;
  				}
  			}
  		}
  		return result;
  	}
  	
  	public boolean pasAcquis(Node n, int x, int y) {
		boolean f=false;
  		if (n.hauteur==0) {
			f= n.couleur=='A';
		}else {
			if(x<=dim(n)/2 && y<=dim(n)/2) {
  				return pasAcquis(n.uu, x,y);
  			}
  			if(x<=dim(n)/2 && y>dim(n)/2) {
  				return pasAcquis(n.ud, x,y-(dim(n)/2));
  			}
  			if(x>dim(n)/2 && y>dim(n)/2) {
  				return pasAcquis(n.dd, x-(dim(n)/2),y-(dim(n)/2));
  			}
  			if(x>dim(n)/2 && y<=dim(n)/2) {
  				return pasAcquis(n.du, x-(dim(n)/2),y);
  			}
		}
		return f;
	}
  	
  	public boolean inRange(Node n, int x, int y) {
  		int max=dim(n);
  		return (0<x && x<=max) && (0<y && y<max);
  	}
  	
  	public Point recherchePointGlouton(Node n, Point p) {
		//initialliser p a (0,0) TOUJOURS
		
		if(n.hauteur==0) {
			int a=0;
			Point p2= new Point(0,0);
			Point p3= new Point(0,0);
			for(int i=0; i<9; i++) {
				if(n.tab[i]=='A' ) {
					
					p3.y=(i%3)+1;
					switch(i) {
					case 0:
					case 3:
					case 6:
						p3.x=1;
						break;
					case 1:
					case 4:
					case 7:
						p3.x=2;
						break;
					case 2:
					case 5:
					case 8:
						p3.x=3;
						break;
					}
				}
				int a2=0;
				if (showColor(n,p3.x+1,p3.y)!='A' && inRange(n,p3.x+1,p3.y)) {
					a2++;
				}if (showColor(n,p3.x+1,p3.y+1)!='A' && inRange(n,p3.x+1,p3.y+1)) {
					a2++;
				}
				if (showColor(n,p3.x,p3.y+1)!='A' && inRange(n,p3.x,p3.y+1)) {
					a2++;
				}
				if (showColor(n,p3.x-1,p3.y+1)!='A' && inRange(n,p3.x-1,p3.y+1)) {
					a2++;
				}
				if (showColor(n,p3.x-1,p3.y)!='A' && inRange(n,p3.x-1,p3.y)) {
					a2++;
				}
				if (showColor(n,p3.x-1,p3.y-1)!='A' && inRange(n,p3.x-1,p3.y-1)) {
					a2++;
				}
				if (showColor(n,p3.x,p3.y-1)!='A' && inRange(n,p3.x,p3.y-1)) {
					a2++;
				}
				if (showColor(n,p3.x+1,p3.y-1)!='A' && inRange(n,p3.x+1,p3.y-1)) {
					a2++;
				}
				if (a<=a2) {
					a=a+2;
					p2=p3;
				}
			}
			p.x+=p2.x;
			p.y+=p2.y;
		} else {
			int f=1;
			int max=n.uu.casesjoue;
			if(max<=n.ud.casesjoue) {
				f++;
				max=n.ud.casesjoue;
			}
			if(max<=n.dd.casesjoue) {
				f++;
				max=n.dd.casesjoue;
			}
			if(max<=n.du.casesjoue) {
				f++;
				max=n.du.casesjoue;
			}
			switch (f) {
			case 1:
				p=recherchePointGlouton(n.uu,p);
				break;
			case 2:
				p=recherchePointGlouton(n.ud, new Point(p.x+dim(n)/2 , p.y));
				break;
			case 3:
				p=recherchePointGlouton(n.dd,new Point(p.x+dim(n)/2,p.y+dim(n)/2));
				break;
			case 4:
				p=recherchePointGlouton(n.du, new Point(p.x,p.y+dim(n)/2));
				break;
			}
		}
		return p;
	}
  	
  	public Point recherchePointGloutonT(Node n, Point p) {
		//initialliser p a (0,0) TOUJOURS
		
		if(n.hauteur==0) {
			int a=0;
			Point p2= new Point(0,0);
			Point p3= new Point(0,0);
			for(int i=0; i<9; i++) {
				if(n.tab[i]=='A' ) {
					
					p3.y=(i%3)+1;
					switch(p.y) {
					case 1:
						p3.x=i+1;
						break;
					case 2:
						p3.x=i-2;
						break;
					case 3:
						p3.x=i-5;
						break;
					}
				}
				int a2=0;
				if (showColor(n,p3.x+1,p3.y)!='A' && pasAcquis(n,p3.x+1,p3.y) && inRange(n,p3.x+1,p3.y)) {
					a2++;
				}if (showColor(n,p3.x+1,p3.y+1)!='A' && pasAcquis(n,p3.x+1,p3.y+1) && inRange(n,p3.x+1,p3.y+1)) {
					a2++;
				}
				if (showColor(n,p3.x,p3.y+1)!='A' && pasAcquis(n,p3.x,p3.y+1) && inRange(n,p3.x,p3.y+1)) {
					a2++;
				}
				if (showColor(n,p3.x-1,p3.y+1)!='A' && pasAcquis(n,p3.x-1,p3.y+1) && inRange(n,p3.x-1,p3.y+1)) {
					a2++;
				}
				if (showColor(n,p3.x-1,p3.y)!='A' && pasAcquis(n,p3.x-1,p3.y) && inRange(n,p3.x-1,p3.y)) {
					a2++;
				}
				if (showColor(n,p3.x-1,p3.y-1)!='A' && pasAcquis(n,p3.x-1,p3.y-1) && inRange(n,p3.x-1,p3.y-1)) {
					a2++;
				}
				if (showColor(n,p3.x,p3.y-1)!='A' && pasAcquis(n,p3.x,p3.y-1) && inRange(n,p3.x,p3.y-1)) {
					a2++;
				}
				if (showColor(n,p3.x+1,p3.y-1)!='A' && pasAcquis(n,p3.x+1,p3.y-1) && inRange(n,p3.x+1,p3.y-1)) {
					a2++;
				}
				if (a<=a2) {
					a=a+2;
					p2=p3;
				}
			}
			p.x+=p2.x;
			p.y+=p2.y;
		} else {
			int f=1;
			int max=n.uu.casesjoue;
			if(max<=n.ud.casesjoue) {
				f++;
				max=n.ud.casesjoue;
			}
			if(max<=n.dd.casesjoue) {
				f++;
				max=n.dd.casesjoue;
			}
			if(max<=n.du.casesjoue) {
				f++;
				max=n.du.casesjoue;
			}
			switch (f) {
			case 1:
				p=recherchePointGloutonT(n.uu,p);
				break;
			case 2:
				p=recherchePointGloutonT(n.ud, new Point(p.x+dim(n)/2 , p.y));
				break;
			case 3:
				p=recherchePointGloutonT(n.dd,new Point(p.x+dim(n)/2,p.y+dim(n)/2));
				break;
			case 4:
				p=recherchePointGloutonT(n.du, new Point(p.x,p.y+dim(n)/2));
				break;
			}
		}
		return p;
	}
  	
}