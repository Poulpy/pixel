package tp5_collection;
import tp2_event.Evenement;

import java.util.ArrayList ;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet ;

import tp1_date.Date;

public class AgendaV2 {
	private ArrayList<Evenement> listEvts ;
	private TreeSet<Evenement> arbreEvts ;
	private HashMap<Integer, TreeSet<Evenement>> mapEvts ;
	
	// Question 1, 5
	public AgendaV2() {
		listEvts = new ArrayList<Evenement>();
		arbreEvts = new TreeSet<Evenement>() ;
		mapEvts = new HashMap<Integer, TreeSet<Evenement>>() ;
	}// AgendaV2()
	
	// Question 7, 8
	public String toString() {
		String string = new String() ;
		// pour toutes les semaines de l'annee
		for (int x = 0 ; x < 52 ; x++)
			if (mapEvts.containsKey(x)) {
				// si il y a un evenement avec x pour semaine
				string +="Semaine "+x+" :\n" ;// alors on affiche les evenements
				for(Evenement evt : mapEvts.get(x))
					string += ">>> "+evt.getChDate().toString()+"\n\t"+evt.getChTitre()+" à "+evt.getChLieu()+"\n";
			}
		return string ;
	}//toString()
	
	// Question 1, 5
	public void ajout(Evenement parEvt) {
		listEvts.add(parEvt);
		arbreEvts.add(parEvt);
		
		Date date = parEvt.getChDate();
		GregorianCalendar calendar = new GregorianCalendar(date.getChAnnee(), date.getChMois(), date.getChJour());
		int numSemaine = calendar.get(Calendar.WEEK_OF_YEAR);// numero de la semaine dans l'annee
		if (mapEvts.containsKey(numSemaine))
			mapEvts.get(numSemaine).add(parEvt);
		else {
			TreeSet<Evenement> set = new TreeSet<Evenement>() ;
			set.add(parEvt);
			mapEvts.put(numSemaine, set);
		}
	}//ajout
	
	
	// Question 3
	public int nbExpose(String str) {
		// retourne le nombre de fois ou il y a "expose" dans le titre d'un evenement
		// dans l'agenda;pas le nombre de fois ou il y a "expose" dans le titre
		int iter = 0 ;
		for(int index = 0 ; index < this.listEvts.size() ; index++)
			if(this.listEvts.get(index).getChTitre().contains(str))
				iter++ ;
		return iter;
	}// nbExpose()
	
	
	// Question 4
	public int nbEvtEnregistre(Date parDate) {
		// retourne le nombre d'evenements qui ont
		// une meme date que celle passage en paramatre
		int nbEvts = 0 ;
		Iterator<Evenement> iter = this.arbreEvts.iterator();
		// il faut declarer un iterateur pour parcourir une ArrayList
		while(iter.hasNext())
			if (iter.next().getChDate().compareTo(parDate)==0)
				nbEvts++ ;
		return nbEvts ;
	}// nbEvtEnregistre
}
