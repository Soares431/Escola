package br.com.mildevs.dao;

import java.util.*;

import br.com.mildevs.entity.aluno;
import jakarta.persistence.*;

public class alunoDao {
	private EntityManager manager;
	
	public alunoDao(){
		this.manager = Persistence.createEntityManagerFactory("escola").createEntityManager();
	}
	
	public boolean insirarAluno(aluno aluno) {
		manager.getTransaction().begin();
		manager.persist(aluno);
		manager.getTransaction().commit();
		
		return true;
	}
	
	public aluno consultaAluno(int matricula) {
		return manager.find(aluno.class, matricula);
	}
	
	public List<aluno> mostraAlunos(){
		
		Query query = manager.createQuery("select a from aluno as a ");
		return query.getResultList(); 
	}
	
	public boolean removeAluno(int matricula) {
		
		aluno alunoASerRemovido = manager.find(aluno.class, matricula);
		
		if(alunoASerRemovido == null)
			return false;
		
		manager.getTransaction().begin();
		manager.remove(alunoASerRemovido);
		manager.getTransaction().commit();
		
		return true;
	}
}
