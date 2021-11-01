package Controle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

import Affichage.Afficheur;
import Model.*;

public class Controleur {

	private Afficheur afficheur;
	private Place currentPlace;
	private Player joueur;
	private Game jeu;
	private Scanner sc;

	public Controleur(Afficheur afficheur, Game game) {
		this.afficheur = afficheur;
		this.jeu = game;
		this.joueur = jeu.getPlayer();
		this.currentPlace = jeu.getStartPlace();
		this.sc = new Scanner(System.in);
	}

	public void jouer() {
		int action = 0;
		Arc destination;
		Png png;
		int indiceinteraction = 0;
		
		while ((this.currentPlace.getType()) != PlacementType.finish) {
			// Informer le joueur sur le lieu
			afficheur.afficherLieu(currentPlace);

			// Recuperer Connaissances visibles
			getConnaissancesVisibles(this.currentPlace, this.joueur);

			// Choisir l'action à effectuer
			action = choisirAction();

			// Effectuer Action
			switch (action) {
			case 1 :
				afficheur.afficherConnaissances(joueur);
				afficheur.afficherItemsPossedes(joueur);
				action=0;
				break;
			case 2 :
				afficheur.visiterPlace(currentPlace);
				action=0;
				break;

			case 3 :
				deposerObjet(joueur,currentPlace);
				action=0;
				break;
			case 4 : 
				prendreObjet(joueur, currentPlace);
				action=0;
				break;
			case 5 :
				png = choisirPng(currentPlace);
				action=0;
				if(png!=null){
					interaction(png, indiceinteraction, joueur);
				}
				break;
			case 6 : 
				destination = choisirArc(currentPlace);
				if (destination!= null) {
					emprunterChemin(destination);
					action=0;
				}
				break;
			}
			update(this.currentPlace, this.joueur);
		}
	}
	

	/** L'explorateur recoit toutes les connaissances visibles dont les conditions sont respectées du lieu 
	 * 
	 * @param l lieu contenant les connaissances
	 * @param e explorateur qui recoit les connaissances
	 */
	protected void getConnaissancesVisibles(Place place, Player player) {
		HashMap<Knowledge, Integer> knowledgesVisible;
		if (place.getKnowledges().size() > 0) {
			knowledgesVisible = place.getVisibleKnowledges();
			int nbObj = afficheur.listerConnaissances(knowledgesVisible);
			
			if(nbObj>0) {

				int index_item = lireInt(1, nbObj, "Choisissez un connaissance ", "Veuillez saisir un indentifiant valide");
				Knowledge knowledge = new Knowledge(null, null);
				int quantity = 0;
				int i=1;
				for (Entry<Knowledge, Integer> set : place.getVisibleKnowledges().entrySet()) {
					if (i==index_item) {
						knowledge = set.getKey();
						quantity = set.getValue();
						break;
					}
					i++;
				}
				afficheur.nouvelleKnowledge(knowledge, quantity);
				
				player.addKnowledge(knowledge, quantity);
				
				for (Entry<Knowledge, Integer> set : place.getKnowledges().entrySet()) {
					if (set.getKey() == knowledge) {
						set.setValue(0);
					}
				}
			}
		}
	}
	
	protected void interaction(Png png, int indiceinteraction, Player p){
		afficheur.interaction(png);
		if (verifyConditionEgalite(png.getInteractions().get(indiceinteraction).getConditionInteraction(), this.joueur)) {
			afficheur.listerChoices(png.getInteractions().get(indiceinteraction).getChoices());
			int indice = lireInt(1, png.getInteractions().get(indiceinteraction).getChoices().size(), "Quel choix voulez vous faire ?", "Saisissez un nombre correct :");
			Choice current_choice = png.getInteractions().get(indiceinteraction).getChoices().get(indice-1);
			faireAction(current_choice.getAction(), p);
		}
	}

	private void faireAction(Action a, Player player) {
		if (Condition.verifyCondition(player, a.getExchange().getConditionExchange())) {
			// On consomme les objets à consommer.
			if (a.getExchange().getConsumedItems().size() != 0) {
				for (Entry<Item, Integer> set : a.getExchange().getConsumedItems().entrySet()) {
					player.ConsumeItem(set.getKey(), set.getValue());
				}
			}
			// On ajoute les nouvelles connaissances et objets.
			if (a.getExchange().getGainedItems().size() != 0) {
				for (Entry<Item, Integer> set : a.getExchange().getGainedItems().entrySet()) {
					player.addItem(set.getKey(), set.getValue());
					afficheur.nouvelItem(set.getKey(), set.getValue());
				}
			}
			if (a.getExchange().getGainedKnowledge().size() != 0) {
				for (Entry<Knowledge, Integer> set : a.getExchange().getGainedKnowledge().entrySet()) {
					player.addKnowledge(set.getKey(), set.getValue());
					afficheur.nouvelleKnowledge(set.getKey(), set.getValue());
				}
			}
		}	
	}
	
	protected boolean verifyCondition(Condition condition,Player p){
		HashMap<Item,Integer> requireditems = condition.getRequiredItems();
		HashMap<Knowledge,Integer> requiredknowledges = condition.getRequiredKnowledges();
		boolean conditionVerified = true;
		for (Entry<Item, Integer> set : requireditems.entrySet()) {
			int itemquantityplayer = p.getQuantityItem(set.getKey());
			if (itemquantityplayer < set.getValue()) {
				conditionVerified = false;
			}
		}
		for (Entry<Knowledge, Integer> set : requiredknowledges.entrySet()) {
			System.out.println(set.getValue());
			System.out.println(p.getQuantityKnowledge(set.getKey()));
			int knowledgequantityplayer = p.getQuantityKnowledge(set.getKey());
			if (knowledgequantityplayer < set.getValue()) {
				conditionVerified = false;
			}
		}
		return conditionVerified;
	}
	
	protected boolean verifyConditionEgalite(Condition condition,Player p){
		HashMap<Item,Integer> requireditems = condition.getRequiredItems();
		HashMap<Knowledge,Integer> requiredknowledges = condition.getRequiredKnowledges();
		boolean conditionVerified = true;
		for (Entry<Item, Integer> set : requireditems.entrySet()) {
			int itemquantityplayer = p.getQuantityItem(set.getKey());
			if (itemquantityplayer != set.getValue()) {
				conditionVerified = false;
			}
		}
		for (Entry<Knowledge, Integer> set : requiredknowledges.entrySet()) {
			int knowledgequantityplayer = p.getQuantityKnowledge(set.getKey());
			if (knowledgequantityplayer != set.getValue()) {
				conditionVerified = false;
			}
		}
		return conditionVerified;
	}

	protected void deposerObjet(Player p, Place place){
		afficheur.listerObjets(p.getItems());
		int index_item = lireInt(1, p.getItems().size(), "Choisissez un objet ", "Veuillez saisir un indentifiant valide :");
		Item item = new Item(null, null, null, 0);
		int quantity = 0;
		int i=1;
		for (Entry<Item, Integer> set : p.getItems().entrySet()) {
			if (i==index_item) {
				item = set.getKey();
				quantity = set.getValue();
				break;
			}
			i++;
		}
		// Si le lieu autorise un dépot pour le joueur
		if (Condition.verifyCondition(p, place.getConditionsDepotObjet())) {
			if (place.getVisibleItems().containsKey(item)) {
				for (Entry<Item, Integer> set : place.getItems().entrySet()) {
					if (set.getKey() == item) {
						set.setValue(set.getValue()+quantity);
					}
				}
			} else {
				place.getItems().put(item, quantity);
			}
			p.setInventoryLoad(p.getInventoryLoad()-item.getPoids()*quantity);
		} else {
			afficheur.depotImpossible();
		}
	}
	
	protected void prendreObjet(Player player, Place place){
		HashMap<Item, Integer> objetsVisibles = place.getVisibleItems();
		
		int nbObj = afficheur.listerObjets(objetsVisibles);
		
		//Si il y a au moins un objet visible et prenable
		if(nbObj>0) {

			// Lire le choix de l'utilisateur
			int index_item = lireInt(1, nbObj, "Choisissez un objet ", "Veuillez saisir un indentifiant valide");
			Item item = new Item(null, null, null, 0);
			int quantity = 0;
			int i=1;
			for (Entry<Item, Integer> set : place.getVisibleItems().entrySet()) {
				if (i==index_item) {
					item = set.getKey();
					quantity = set.getValue();
					break;
				}
				i++;
			}
			afficheur.choisirItem(item);

			int choix_quantite=1;
			if (quantity >= 1){
				choix_quantite = lireInt(1, quantity, 
						"Veuillez saisir la quantité à  prendre",
						"Saisissez un nombre entre 1 et "+quantity);
			}
			
			boolean reussite = player.addItem(item, choix_quantite);
			if (reussite) {
				afficheur.placeInsuffisante(item);
				for (Entry<Item, Integer> set : place.getItems().entrySet()) {
					if (set.getKey() == item) {
						set.setValue(set.getValue()-quantity);
					}
				}
			} else {
				afficheur.nouvelItem(item,quantity);
			}
		}
	}
	

	protected Png choisirPng(Place place) {
		afficheur.listerPngs(place.getPng());
		if (place.getPng().size() != 0) {
			int indice = lireInt(1, place.getPng().size(), "A qui voulez vous parler ?", "Choisissez un numéro valide :");
			return place.getPng().get(indice-1);
		} else {
			return null;
		}
		
	}
	
	protected Arc choisirChemin(Place l){
		List<Arc> cheminsValide = getValidArcs(this.jeu, currentPlace, this.joueur);
		
		int nbChemins = afficheur.listerArcs(cheminsValide);
		if (nbChemins == 0) {
			return null;
		} else {
			int indice = lireInt(1, nbChemins, "Quel chemin voulez vous prendre ?", "Saisissez un nombre correct :");
			return cheminsValide.get(indice-1);
		}
	}
	
	private List<Arc> getValidArcs(Game jeu, Place place, Player player) {
		List<Arc> validArcs = new ArrayList<Arc>();
		for(Arc arc : place.getArcOut()) {
			Condition c = arc.getExchange().getConditionExchange();
			if(arc.getVisibility() == Visibility.visible && verifyCondition(c, player)){
				validArcs.add(arc);
			}
		}
		return validArcs;
	}
	
	protected Arc choisirArc(Place place){
		List<Arc> cheminsValide = getValidArcs(this.jeu, place, this.joueur);
		int nbChemins = afficheur.listerArcs(cheminsValide);
		if (nbChemins == 0) {
			return null;
		} else {
			int indice = lireInt(1, nbChemins, "Quel chemin voulez vous prendre ?", "Saisissez un nombre correct :");
			return cheminsValide.get(indice-1);
		}
	}
	
	
	protected void emprunterChemin(Arc arc){
		HashMap<Item, Integer> itemsToConsum = arc.getExchange().getConsumedItems();
		HashMap<Item, Integer> itemsToGain = arc.getExchange().getGainedItems();
		HashMap<Knowledge, Integer> knowledgeToGain = arc.getExchange().getGainedKnowledge();
		
		for (Entry<Item, Integer> set : itemsToConsum.entrySet()) {
			this.joueur.ConsumeItem(set.getKey(), set.getValue());
		}
		for (Entry<Item, Integer> set : itemsToGain.entrySet()) {
			if (this.joueur.getInventorySize()-this.joueur.getInventoryLoad() >= set.getKey().getPoids()*set.getValue()) {
				this.joueur.addItem(set.getKey(), set.getValue());
			}
		}
		for (Entry<Knowledge, Integer> set : knowledgeToGain.entrySet()) {
			this.joueur.addKnowledge(set.getKey(), set.getValue());
		}
		
		// aller dans le lieu suivant
		this.currentPlace = arc.getTarget();
		afficheur.emprunterChemin(arc);
		if (currentPlace.getType() == PlacementType.finish) {
			afficheur.finJeu();
		}
	}
	
	protected int choisirAction(){
		int choix=0;
		System.out.println("Que voulez vous faire ?");
		System.out.println("(1) Lister les connaissances et les objets possédées");
		System.out.println("(2) Lister ce qui est visible dans le lieu (personnes + objets)");
		System.out.println("(3) Déposer un objet");
		System.out.println("(4) Prendre un objet");
		System.out.println("(5) Interagir avec une personne");
		System.out.println("(6) Choisir un chemin");
		choix = lireInt(1, 8);
		return choix;
	}
	
	protected Action chooseAction(List<Choice> choices){
		for(Choice c : choices){
			if(verifyCondition(c.getstartconditions(), this.joueur)){
				return c.getAction();
			}
		}
		return null;
	}

	
	/** Demande à l'utilisateur de saisir un entier compris entre min et max 
	 * 
	 */
	private int lireInt(int min, int max) {
		//return lireInt(min, max, "Saisissez un entier entre "+min+" et "+max, "");
		return lireInt(min, max, "", "Saisissez un nombre entre "+min+" et "+max);
	}
	
	/** Demande Ã  l'utilisateur de saisir un entier compris entre min et max 
	 * 
	 * @param texteAcceuil : texte affiché avant chaque essai
	 * @param texteEchec   : texte affiché Ã  chaque saisie incorrecte
	 */
	private int lireInt(int min, int max, String texteAcceuil, String texteEchec){
		int res = min-1;
		boolean succes = false;

		while(!succes) {
			try  {

				System.out.println(texteAcceuil);

				String str = sc.nextLine();
				res = Integer.parseInt(str);
				
				if (res>=min && res<=max){
					succes = true;
				} else {
					System.out.println(texteEchec);
				}
			} catch (Exception e){
				System.out.println(texteEchec);
				//e.printStackTrace();
			}
		}
		return res;
	}
	
	private void update(Place place, Player player) {
		List<Arc> arc_out = place.getArcOut();
		if (arc_out.size() > 0) {
			for (Arc arc : arc_out) {
				if (verifyConditionEgalite(arc.getVisibilityCondition(), player)) {
					arc.setVisibility(Visibility.visible);
				}
			}
		}
	}

}
