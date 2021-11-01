package Affichage;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import Model.*;

public class Afficheur {
	
	public void afficherLieu(Place place) {
		System.out.println();
		System.out.println("Vous êtes dans " + place.getName());
		System.out.println();
	}

	public void visiterPlace(Place place) {
		HashMap<Item, Integer> itemsVisibles = place.getVisibleItems();
		List<Png> PngsVisibles = place.getPossiblePngs();
		
		System.out.println("******************* Items Visibles ********************");
		listerObjets(itemsVisibles);
		System.out.println("******************* Pngs  Visibles ********************");
		listerPngs(PngsVisibles);
		System.out.println("*******************************************************");

	}

	public void afficherCheminsAccessibles(Place place) {
		System.out.println("Les Arcs accessibles sont : ");
		for (Arc arc_out : place.getArcOut()) {
			if (arc_out.getVisibility() == Visibility.visible) {
				System.out.print("   " + "Le chemin "+arc_out.getName()+" qui va de "+place+" à  "+arc_out.getTarget().getName());
			}		
		}
	}

	public void emprunterChemin(Arc arc) {
		System.out.println("Vous empruntez  le chemin "+arc.getName()+" qui va de "+arc.getOrigin().getName()+" à  "+arc.getTarget().getName());
	}
	
	public void afficherItemsPossedes(Player player) {
		HashMap<Item, Integer> Items = player.getItems();
		int poidsMax = player.getInventorySize();
		
		System.out.println("******************* Items Possedes *******************");
		listerObjets(Items);
		System.out.println("*******************************************************");
		System.out.println("Poids Total : " + player.getInventoryLoad() + "/" + player.getInventorySize());
		System.out.println("*******************************************************");
		System.out.println();
	}
	
	public void afficherConnaissances(Player player) {
		HashMap<Knowledge, Integer> knowledges = player.getKnowledges();
		
		System.out.println("************** Connaissances Maitrisées ***************");
		listerConnaissances(knowledges);
		System.out.println("*******************************************************");
		System.out.println();
	}

	public void nouvelleKnowledge(Knowledge Knowledge, int quantity) {
		System.out.println("Vous venez d'acquérir : " + quantity + " " + Knowledge.getName());
	}

	public void choisirItem(Item Item) {
		System.out.println("Vous avez choisi : " + Item.getName());
	}
	
	public void nouvelItem(Item Item, int quantity) {
		System.out.println("Vous venez d'acquérir : " + quantity + " " + Item.getName());		
	}
	
	public void placeInsuffisante(Item item) {
		System.out.println("Pas assez de place pour acquérir "+ item.getName());
	}
	
	public void depotImpossible() {
		System.out.println("Vous n'avez pas le droit de déposer des Items ici");
	}
	
	public void deposerItem(Item Item) {	
		System.out.println("Vous venez de déposer " + Item);		
	}

	public void consommerItem(Item Item) {	
		System.out.println("Vous venez de consommer " + Item);		
	}
	
	public void interaction(Png Png) {
		System.out.println(Png.getName() + " vous propose la question suivante");
	}
	
	public int listerObjets(HashMap<Item, Integer> items) {
		int indice = 0;
		
		if (items.size() == 0) {
			System.out.println("Il n'y a aucun Items");
		} else {
			System.out.println("Id  Quantite Nom                                  Poids");
			for (Entry<Item, Integer> set : items.entrySet()) {
				indice++;
				System.out.println(String.format("%2d)%9d %-37s%d",indice , set.getValue(), set.getKey().getName(), set.getKey().getPoids())); 
			}
		}
		return indice;
	}
	
	public int listerChoices(List<Choice> choices) {
		int indice = 0;
		
		if (choices.size() == 0) {
			System.out.println("Il n'y a aucun choix");
		} else {
			for (Choice c : choices) {
				indice++;
				System.out.println("  " + indice + ") " + c.getDescription()); 
			}
		}
		return indice;
	}
	
	public int listerConnaissances(HashMap<Knowledge, Integer> knowledges) {
		int indice = 0;
		
		if (knowledges.size() == 0) {
			System.out.println("Il n'y a aucune Knowledges");
		} else {
			for (Entry<Knowledge, Integer> set : knowledges.entrySet()) {
				indice++;
				System.out.println(String.format("%2d)%9d %-37s",indice , set.getValue(), set.getKey().getName())); 
			}
		}
		return indice;
	}
	
	public int listerPngs(List<Png> Pngs) {
		int indice = 0;
		
		if (Pngs.size() == 0) {
			System.out.println("Il n'y a Png");
		} else {
			for (Png p : Pngs){
				indice++;
				System.out.println("   " + indice + ") " + p.getName());
			}
		}
		return indice;
	}
	
	public int listerArcs(List<Arc> Arcs) {
		int indice = 0;
		
		if (Arcs.size() == 0) {
			System.out.println("Il n'y a aucun Arcs");
		} else {
			for (Arc arc : Arcs){
				indice++;
				System.out.println("  " + indice + ") " + "Le chemin "+arc.getName()+" qui va de "+arc.getOrigin().getName()+" à  "+arc.getTarget().getName()); 
			}
		}
		return indice;
	}
	
	public void finJeu() {
		System.out.println("Vous avez fini le jeu !");
	}
}
