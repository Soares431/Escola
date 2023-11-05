package br.com.mildevs.escola;

import java.time.LocalDate;
import java.util.*;

import org.hibernate.mapping.Array;

import br.com.mildevs.dao.*;
import br.com.mildevs.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class programa {
	public static void main(String[] args) {
		
		professorDao professorDao = new professorDao();
		turmaDao turmaDao = new turmaDao();
		alunoDao alunoDao = new alunoDao();
		
		professor professor = new professor();
		professor.setDisciplina("Lógica de Programação");
		professor.setNivelGraduacao("DOUTORADO");
		professor.setNome("Joao");
		professor.setSalario(2000);
		professor.setTelefone("56497894984");
		professorDao.insirarProfessor(professor);
		
		professor professorDb = professorDao.consultaProfessor(1);
		System.out.println("Professor encontrado -> " + professorDb);
		
		
		List<professor> professoresNoDb = professorDao.mostraProfessor();
		
		if(professoresNoDb != null) {
			for (professor professorEncontradoNaListagem : professoresNoDb) {
				System.out.println(professorEncontradoNaListagem);
			}
		}
		
		
		
		sala sala = new sala();
		sala.setAltura(10);
		sala.setComprimento(15);
		sala.setLargura(345);
		
		
		turma turmaCriada = turmaDao.criaTurma(sala);
		turmaDao.adicionarProfessor(professorDb, turmaCriada.getCodTurma());
		
		
		aluno aluno = new aluno();
		
		aluno.setNome("Lucas");
		aluno.setSerie("1 serie");
		aluno.setDataNascimento(LocalDate.now());
		alunoDao.insirarAluno(aluno);
		
		aluno aluno2 = new aluno();
		aluno2.setDataNascimento(LocalDate.now());
		aluno2.setNome("Ana");
		aluno2.setSerie("2 serie");
		alunoDao.insirarAluno(aluno2);
		
		
		aluno aluno3 = new aluno();
		aluno3.setDataNascimento(LocalDate.now());
		aluno3.setNome("pedro");
		aluno3.setSerie("2 serie");
		alunoDao.insirarAluno(aluno3);
		
		turmaDao.adicionaraluno(aluno, turmaCriada.getCodTurma());
		turmaDao.adicionaraluno(aluno2, turmaCriada.getCodTurma());
		turmaDao.adicionaraluno(aluno3, turmaCriada.getCodTurma());
		
	
		
		List<turma> turmas = turmaDao.listaTurma();
		
		for (turma turmaNoDb : turmas) {
			System.out.println(turmaNoDb);
		}
		
		System.out.println("-------------------------------------");
		
		if(turmaCriada.getAlunos() != null)
			turmaDao.listaChamada(turmaCriada.getCodTurma());
		
		
		
	}
}
