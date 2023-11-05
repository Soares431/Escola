package br.com.mildevs.dao;

import java.util.*;

import br.com.mildevs.entity.*;
import jakarta.persistence.*;

public class turmaDao {
	
	private EntityManager manager;

	public turmaDao() {
		this.manager = Persistence.createEntityManagerFactory("escola").createEntityManager();
	}
	
	public turma criaTurma(sala sala) {
		
		
		turma turma = new turma();
		turma.setSala(sala);
		sala.setTurma(turma);
		
		this.manager.getTransaction().begin();
		this.manager.persist(turma);
		this.manager.getTransaction().commit();
		
		return turma;
	}
	
	public boolean adicionarProfessor(professor professor, int codturma) {
		turma turma = this.manager.find(turma.class, codturma);
		
		if(turma == null)
			return false;
		if(turma.getProfessor() != null)
			return false;
		
		turma.setProfessor(professor);
		
		
		this.manager.getTransaction().begin();
		this.manager.merge(turma);
		this.manager.getTransaction().commit();
		
		return true;
		
	}
	
	public boolean adicionaraluno(aluno aluno, int codTurma) {
		
		turma turma = manager.find(turma.class, codTurma);
		
		if(turma == null)
			return false;
		
		if(turma.getProfessor() == null)
			return false;
		
		List<aluno> alunoTurma = turma.getAlunos();
		
		if(alunoTurma == null)
			alunoTurma = new ArrayList<aluno>();
		
		alunoTurma.add(aluno);
		turma.setAlunos(alunoTurma);
		
		
		
		List<turma> turmaAluno = aluno.getTurmas();
		
		if(turmaAluno == null)
			turmaAluno = new ArrayList<turma>();
			
		turmaAluno.add(turma);
		aluno.setTurmas(turmaAluno);
		
		this.manager.getTransaction().begin();
		this.manager.merge(turma);
		this.manager.getTransaction().commit();
		
		return true;
	}
	
	public void listaChamada(int codTurma) {
		
		turma turma = this.manager.find(turma.class, codTurma);
		
		if(turma == null)
				return;
			
		for(aluno aluno : turma.getAlunos()) {
			System.out.println(aluno);
		}
	}
	
	public List<turma> listaTurma(){
		
		Query query = this.manager.createQuery("select t from turma as t");
		
		return query.getResultList();
	}
	
	public boolean removeTurma(int codTurma) {
		
		turma turma = this.manager.find(turma.class, codTurma);
		
		if(turma == null)
			return false;
		
		this.manager.getTransaction().begin();
		this.manager.remove(turma);
		this.manager.getTransaction().commit();
		
		return true;
	}
	
}
