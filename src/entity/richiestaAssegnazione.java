package entity;

import java.util.Set;

public class richiestaAssegnazione 
{
	public Set<elaborato> preferenzeSpecificate;
	public statoRichiesta stato;
	private int codice;
	public studente studente;
	
	public richiestaAssegnazione(Set<elaborato> preferenzeSpecificate, studente studente)
	{
		this.preferenzeSpecificate = preferenzeSpecificate; 
		this.stato = statoRichiesta.PENDING;
		this.codice = -1;
		this.studente = studente;
	}
	
	public statoRichiesta getStato()
	{
		return stato;
	}
	
	public void setStato(statoRichiesta s)
	{
		this.stato = s;
	}
	
	public int getCodice()
	{
		return codice;
	}
	
	public void setCodice(int c)
	{
		this.codice = c;
	}
	
	public Set<elaborato> getPreferenze()
	{
		return preferenzeSpecificate;
	}
	
	public void setPreferenze(Set<elaborato> p)
	{
		this.preferenzeSpecificate = p;
	}
	
	public studente getStudente()
	{
		return studente;
	}
	
	public void setStudente(studente s)
	{
		this.studente = s;
	}
	
}
