package control;

import database.DAOException;
import entity.*;
import database.elaboratoDAO;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class GestioneElaborati
{
	private ArrayList<elaborato> listaElaborati = new ArrayList<elaborato>();
	
	public GestioneElaborati() throws DAOException 
	{
		listaElaborati = elaboratoDAO.readAll();
	}
	
	public ArrayList<elaborato> getElaborati() 
	{
		return listaElaborati;
	}

	public void setElaborati(ArrayList<elaborato> l)
	{
		this.listaElaborati = l;
	}
	
	public void addElaborato(elaborato e) throws DAOException 
	{
		listaElaborati.add(e);
	
		int idElab = elaboratoDAO.create(e);
		
		e.setIdElaborato(idElab);			
	}
	
	public void delElaborato(elaborato e) throws DAOException
	{
		elaboratoDAO.delete(e);
		listaElaborati.remove(e);
	}
	
	public int assegnaElaborato(richiestaAssegnazione r, int CFUmin) throws DAOException
	{	
		elaborato elab = null;
		
		int CFUstu = r.studente.CFU; 
		
		if(CFUstu >= CFUmin)
		{
			Set<elaborato> preferenzeSet = new TreeSet<elaborato>();
			
			preferenzeSet = r.getPreferenze();
			Iterator<elaborato> it = preferenzeSet.iterator();
    		    
		    while(it.hasNext()) 
		    {
		    	elaborato e = it.next();
		    	    		    		    	
		    	if(!e.assegnato)
		    	{		    		
		    		if (e.docente.numElaboratiAssegnati <= 10)
		    		{
		    			//Caso in cui viene assegnato l'elaborato richiesto
		    			
		    			elab = elaboratoDAO.read(e.idElaborato);
		    			
		    			System.out.println("NUMERO CFU RICHIESTO: " + CFUmin + "\n" +
								   		   "NUMERO CFU POSSEDUTI: " + CFUstu + "\n\n" +
								           "NUMERO SUFFICIENTE PER ASSEGNAZIONE ELABORATO\n\n" +
								           "L'elaborato che ti è stato assegnato è:\n" + elab.toString());    			
		    			return 2;
		    		}
		    	}
		    	
		    }

		    //Caso in cui gli elaborati richiesti non sono disponibili
				    
		    Iterator<elaborato> iter = listaElaborati.iterator();
		    
		    while(iter.hasNext())
		    {
		    	
		    elaborato el = iter.next();
		    	
		    	if(el.assegnato == false && el.docente.numElaboratiAssegnati <= 10)
		    	{	
		    	elab = elaboratoDAO.read(el.idElaborato);	
		    	
		    	System.out.println("NUMERO CFU RICHIESTO: " + CFUmin + "\n" +
		    			           "NUMERO CFU POSSEDUTI: " + CFUstu + "\n\n" +
			                       "NUMERO SUFFICIENTE PER ASSEGNAZIONE ELABORATO\n\n" +
			   		               "Non è stato possibile assegnare uno degli elaborati richiesti. Ti è stato automaticamente assegnato l'elaborato:\n\n" + elab.toString());   			
    			return 1;
		    	}
		    }
	    	
		    //Caso in cui non ci sono elaborati disponibili nemmeno tra i rimanenti
		    
		    System.out.println("NUMERO CFU RICHIESTO: " + CFUmin + "\n" +
	    					   "NUMERO CFU POSSEDUTI: " + CFUstu + "\n\n" +
                               "NUMERO SUFFICIENTE PER ASSEGNAZIONE ELABORATO\n\n" +
		                       "Non è stato possibile assegnare uno degli elaborati richiesti. Né sono stati trovati elaborati disponibili.");
		    
		    return 0;
		}
		
		//Caso in cui il numero di CFU non è sufficiente
		
		else
		{
			System.out.println("NUMERO CFU RICHIESTO: " + CFUmin + "\n" +
					   		   "NUMERO CFU POSSEDUTI: " + CFUstu + "\n\n" +
					           "NUMERO INSUFFICIENTE PER ASSEGNAZIONE ELABORATO");
		return -1;
		}
	}	
}