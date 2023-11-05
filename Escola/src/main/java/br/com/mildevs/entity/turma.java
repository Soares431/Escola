package br.com.mildevs.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class turma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_turma")
	private int codTurma;
	
	@ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	@JoinColumn(name = "cod_funcionario_fk", referencedColumnName = "cod_funcionario")
	private professor professor;

	@ManyToMany(mappedBy = "turmas", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<aluno> alunos;
	
	@OneToOne(mappedBy = "turma", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private sala sala;
	
	public professor getProfessor() {
		return professor;
	}

	public void setProfessor(professor professor) {
		this.professor = professor;
	}

	public int getCodTurma() {
		return codTurma;
	}

	public void setCodTurma(int codTurma) {
		this.codTurma = codTurma;
	}

	public List<aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<aluno> alunos) {
		this.alunos = alunos;
	}

	public sala getSala() {
		return sala;
	}

	public void setSala(sala sala) {
		this.sala = sala;
	}

	@Override
	public String toString() {
		return "Turma [codTurma=" + codTurma + ", getProfessor()=" + getProfessor() + ", getAlunos()=" + getAlunos()
				+ ", getSala()=" + getSala() + "]";
	}
	
	
	
}
