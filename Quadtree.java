package projetCameleon;

//tout les methodes sont fonctionelles
//pour créer une gille de 3*(2^k) regions a l'aide d'une arbre quadratique(arbre complet avec des feulles qui sont des tableaux
public class Quadtree{
  

  	public class Node{
  		char couleur;
  		char[] tab;
  		int hauteur;
  		Node uu,ud,dd,du;
  		
  		Node(int h, char c){
  			hauteur=h;
  			couleur=c;
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
  	private int dim(Node n) {
  		return (int)(3*Math.pow(2, (double)(n.hauteur)));
  	}
  	
  	//pour donner le couleur du case (x,y)
  	private char showColor(Node n, int x, int y) {
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
  			n.tab[(y-1)*3+(x-1)]=c;
  			return;
  		}else {
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
  	
}
