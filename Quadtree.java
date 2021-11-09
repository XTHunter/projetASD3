import java.awt.Point;

public class Quadtree{
  Node regions;
  int hauteur;

  public class Node{
    char couleur;
    int hauteur;
    char[] tab;
    Node uu,ud,dd,du;
    Node(){
      couleur='A';
      tab=null;
      uu=null;
      ud=null;
      dd=null;
      du=null;
    }
  }  

  public void creerRegion(Node a, int k){
    a= new Node();
    a.hauteur=k;
    if(k==0){
      a.tab = new char[9];
      for (int i=0; i<9; i++){
        a.tab[i]='A';
      }
    }else{
      creerRegion(a.uu, k-1);
      creerRegion(a.ud, k-1);
      creerRegion(a.dd, k-1);
      creerRegion(a.du, k-1);
    }
  }

  public char afficherCouleur(Node n, Point p){
      int mid = (int)(3*Math.pow(2,(double)(n.hauteur))/2);
      if(n.hauteur==0){
        if(p.y==3){
          return n.tab[p.x+6];
        }
        if(p.y==2){
          return n.tab[p.x+3];
        }
        if(p.y==1){
          return n.tab[p.x];
        }
      }else{
        if(p.x<=mid&&p.y<=mid){
          return afficherCouleur(n.uu,p);
        }
        if(p.x<=mid&&p.y>mid){
          return afficherCouleur(n.ud,new Point(p.x, p.y-mid));
        }
        if(p.x>mid&&p.y<=mid){
          return afficherCouleur(n.du,new Point(p.x-mid, p.y));
        }
        if(p.x>mid&&p.y>mid){
          return afficherCouleur(n.dd,new Point(p.x-mid, p.y-mid));
        }
      }
  }

  public void afficher(Node n){
    int cote = (int)(3*Math.pow(2,(double)(n.hauteur)));
    for (int i=1; i<=cote; i++){
      for (int j=1; j<=cote; j++){
        if (j<cote) {
          System.out.print(afficherCouleur(n,new Point(j, i)));
        } else {
          System.out.println(afficherCouleur(n,new Point(j, i)));
        }
      }
    }
  }

  public void changerCouleur(Node n, Point p, char a){
    int mid = (int)(3*Math.pow(2,(double)(n.hauteur))/2);
      if(n.hauteur==0){
        if(p.y==3){
          n.tab[p.x+6]=a;
        }
        if(p.y==2){
          n.tab[p.x+3]=a;
        }
        if(p.y==1){
          n.tab[p.x]=a;
        }
      }else{
        if(p.x<=mid&&p.y<=mid){
          changerCouleur(n.uu,p,a);
          return;
        }
        if(p.x<=mid&&p.y>mid){
          changerCouleur(n.ud,new Point(p.x, p.y-mid),a);
          return;
        }
        if(p.x>mid&&p.y<=mid){
          changerCouleur(n.du,new Point(p.x-mid, p.y),a);
          return;
        }
        if(p.x>mid&&p.y>mid){
          changerCouleur(n.dd,new Point(p.x-mid, p.y-mid),a);
          return;
        }
      }
  }

  public boolean regionRempli(Node n){
    if(n.couleur=='A'){
      return false;
    }else{
      return true;
    }
  }
}