package br.com.mildevs.dao;

import java.util.List;

import br.com.mildevs.entity.professor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class professorDao {
	
private EntityManager manager;
	
	public professorDao(){
		this.manager = Persistence.createEntityManagerFactory("escola").createEntityManager();
	}
	
	public boolean insirarProfessor(professor professor) {
		manager.getTransaction().begin();
		manager.persist(professor);
		manager.getTransaction().commit();
		
		return true;
	}
	
	public professor consultaProfessor(int codFuncionario) {
		return manager.find(professor.class, codFuncionario);
	}
	
	public List<professor> mostraProfessor(){
		
		Query query = manager.createQuery("select p from professor as p");
		return query.getResultList();
	}
	
	public boolean removeProfessor(int codFuncionario) {
		
		professor professorASerRemovido = manager.find(professor.class, codFuncionario);
		
		if(professorASerRemovido == null)
			return false;
		
		manager.getTransaction().begin();
		manager.remove(professorASerRemovido);
		manager.getTransaction().commit();
		
		return true;
	}
	
}
