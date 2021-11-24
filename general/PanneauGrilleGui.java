package general;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Panneau rectangulaire d'au maximum MAX_LIGNES X MAX_COLONNES
 * qui permet d'obtenir s'il y  eu un clic, la position du clic et modifier le 
 * contenu de la case (couleur et texte).
 * 
 * Utile pour des TP3 en inf111 (jeux tels Sudoku, Binero, bataille navale...)
 * 
 * @author Pierre B�lisle (copyright 2016)
 * @version H2016
 * @r�vision A2021
 */
public class PanneauGrilleGui  extends JPanel{

	/*
	 * STRAT�GIE : On met des boutons dans un panneau mais on les retient aussi 
	 * dans une grille.  Une classe interne MonJButton h�rite de 
	 * JButton � laquelle on ajoute des attributs pour retenir la position
	 * du bouton dans la grille.  Tout cela pour �viter la recherche du 
	 * bouton lors d'un clic (deux boucles en moins).
	 *                        
	 * Un bool�en permet de retenir si un bouton a �t� cliqu� et il 
	 * est remis � faux apr�s une lecture de la position par son accesseur.
	 */

	// Limite pour voir le texte.
	public static final int MAX_LIGNES = 20;
	public static final int MAX_COLONNES = 20;
	
	// Celle du texte des boutons.
	public static final int TAILLE_CAR = 15;
	
	// Deux modes de fermeture du gui.  On quitte le programme  ou on 
	// dispose juste la fen�tre.
	public static final int QUITTE = JFrame.EXIT_ON_CLOSE;
	public static final int DISPOSE = JFrame.DISPOSE_ON_CLOSE;
	public static final int NOTHING = JFrame.DO_NOTHING_ON_CLOSE;

	// La grille qui sera affich�e (classse interne d�crite � la fin).
	private MonJButton [][] grille;

	// La position en y,x du dernier clic.
	private Coord dernierClic = new Coord();

	// Mis � vrai lors d'un clic et � faux dans getPosition.
	private boolean estClique;
	
	// Retenir la taille de la grille.
	private int nbLignes;
	private int nbColonnes;

	// Les couleurs.
	private Color couleurTexte;
	private Color couleurFond;
	
	// La dimension.
	private Dimension dim;
	
	PanneauGrilleGui self;
	
	/**
	 * Cr�e une grille selon les dimensions re�ues.
	 * 
	 * @param dim En pixels
	 */
	public PanneauGrilleGui(Dimension dim){
		
		
		// On retient la taille et les couleurs de la grille.
		this.nbLignes = (nbLignes>MAX_LIGNES)
				?MAX_LIGNES
						:Constantes.TAILLE;

		this.nbColonnes = (nbColonnes>MAX_COLONNES)
				?MAX_COLONNES
						:Constantes.TAILLE;

		this.couleurFond = Color.WHITE;
		this.couleurTexte = Color.BLACK;

		this.dim = dim;
		
		// On cr�e le tableau 2D (vide).
		grille = new MonJButton[nbLignes][nbColonnes];

		// Rien de cliqu� � date.
		estClique = false;
		
		self = this;
			
		initComposants();
		
	}
	
	private void initComposants(){
		
		// Une disposition en grille pour celui du haut.
		setLayout(new GridLayout(nbLignes, nbColonnes));

		// On ajoute les boutons vides.
		ajouterBoutons(this);

		// On veut �tre s�r de la bonne dimension en tout temps.
		setSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		setPreferredSize(dim);

	}
	

	/**
	 * Accesseur de la position du dernier clic.  Ne tient pas compte s'il y a 
	 * eu un clic ou non.
	 * 
	 * @return 
	 */
	public Coord getPosition(){

		estClique = false;
		
		return dernierClic;		
	}
	
	
	/**
	 * Retourne la valeur contenue dans une case
	 * @param coord La position de la case d�sir�e.
	 * 
	 * @return
	 */
	public String getValeur(Coord c){

		return grille[c.ligne][c.colonne].getText();
	}
	
	/**
	 * Permet de modifier la valeur d'une case de la grille.
	 * 
	 * @param coord La position de la case d�sir�e.
	 * @param valeur La nouvelle valeur.
	 */
	public void setValeur(Coord c, String valeur){
		
		grille[c.ligne][c.colonne].setText(valeur);
	}

	/**
	 * Accesseur du nombre de lignes.
	 * 
	 * @return Le nombre de lignes de la grille.
	 */
	public int getNbLignes() {
		
		return nbLignes;
	}

	/**
	 * Accesseur du nombre de colonnes.
	 * 
	 * @return Le nombre de colonnes de la grille.
	 */
	public int getNbColonnes() {
		
		return nbColonnes;
	}
	
	/**
	 * Permet de changer la couleur de fond d'une case.
	 * 
	 * @param coord La position de la case
	 * @param couleur La nouvelle couleur
	 */
	public void setCouleurFond(Coord c, Color couleurFond){
		
	        grille[c.ligne][c.colonne].setBackground(couleurFond);
	}

	/**
	 * Permet de changer la couleur de texte d'une case.
	 * 
	 * @param coord La position de la case
	 * @param couleur La nouvelle couleur
	 */
	public void setCouleurTexte(Coord c, Color couleurTexte){
		
		grille[c.ligne][c.colonne].setForeground(couleurTexte);
	}
	
	/**
	 * Retourne si un des boutons a �t� cliqu� depuis le dernier appel
	 * � l'accesseur de position.
	 * 
	 * @return Si un des boutons a �t� s�lectionn�.
	 */
	public boolean caseEstCliquee(){
		
		return estClique;
	}

	public void resetEstClique(){
		
		estClique = false;		
	}
	
	/**
	 * Le clic de cette case n'a plus d'effet
	 * @param x
	 * @param ligne
	 */
	public void desactiverCase(Coord c){
		
		grille[c.ligne][c.colonne].setEnabled(false);
	}
	
	/**
	 * Permet de remettre toutes les cases du panneau disponibles.
	 */
	public void reactiverCases(){
		
		for(int i=0; i < grille.length; i++)
			
			for(int j=0; j < grille[i].length; j++)
				
				grille[i][j].setEnabled(true);
				
	}
	
	
	/*
	 * Ajoute les boutons dans la grille et dans le panneau.
	 * 
	 * Principalement pour la lisibilit� du code.
	 */
	private void ajouterBoutons(JPanel panneau){

		for(int i = 0; i < nbLignes;i++)
			for(int j = 0; j <nbColonnes;j++){

				grille[i][j] =  new MonJButton(i,j, " ",  
						                       couleurTexte, 
						                       couleurFond);
				
				panneau.add(grille[i][j]);
			}	
	}
	
	/**
	 * Retourne une copie du panneau actuel (deep copy).
	 */
	public PanneauGrilleGui clone(){
		
		PanneauGrilleGui copie = new PanneauGrilleGui(this.dim);

		copie.copierEtatCases(this);
		
		return copie;
		
	}

	/**
	 * Sert � mettre les cases d'un panneau identique � une copie re�ue.
	 * 
	 * @param copie
	 */
	public void copierEtatCases(PanneauGrilleGui copie){

		// On met l'�tat de la copie comme celui du panneau actuel
		// sans les �couteurs
		for(int i = 0; i < nbLignes; i++){
			
			for(int j = 0; j < nbColonnes; j++){
				grille[i][j].setBackground(copie.grille[i][j].getBackground());
				grille[i][j].setForeground(copie.grille[i][j].getForeground());
				grille[i][j].setText(copie.grille[i][j].getText());
				grille[i][j].setEnabled(copie.grille[i][j].isEnabled());
			}
		}

	}
	/**
	 * Classe interne qui ajoute � un JButton la position (x,y) o� il se trouve 
	 * dans la grille.
	 * 
	 * Cela �vite de chercher cette position lors d'un clic.
	 */
	private class MonJButton extends JButton{

		// Juste enlever le warning.
		private static final long serialVersionUID = 1L;
		
		// Coordonn�e ligne colonne du bouton dans le gui.
		private int ligne;
		private int colonne;

		/**
		 * Constructeur avec la position du bouotn et sa valeur.
		 * @param y La position en ligne
		 * @param x La position en colonne
		 * @param valeur La valeur � afficher
		 */
		private MonJButton(int ligne, int colonne, String valeur,
				           Color couleurTexte, 
				           Color couleurFond){

			// On passe le texte � la classe parent.
			super(valeur);

			this.ligne = ligne;
			this.colonne = colonne;

			setForeground(self.couleurTexte);
			setBackground(couleurFond);
			setFont(new Font("sans serif", Font.BOLD, TAILLE_CAR));

			addActionListener(new EcouteurBtn());
		}
		
		private class EcouteurBtn implements ActionListener{
				
				public void actionPerformed(ActionEvent e) {

					// On obtient la r�f�rence du bouton cliqu�.
					MonJButton b = (MonJButton) e.getSource();

					// On retient la position du clic.
					dernierClic = new Coord();
					dernierClic.ligne = b.ligne;
					dernierClic.colonne = b.colonne;					
					estClique = true;		
				}	
		}
	}
}