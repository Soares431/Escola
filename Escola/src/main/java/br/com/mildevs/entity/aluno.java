package br.com.mildevs.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int matricula;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String serie;

	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;

	@ManyToMany(fetch = FetchType.EAGER, cascade =CascadeType.ALL)
	@JoinTable(name = "turmas_alunos", 
			joinColumns = @JoinColumn(name = "aluno_fk", referencedColumnName = "matricula"), 
			inverseJoinColumns = @JoinColumn(name = "turma_fk", referencedColumnName = "cod_turma"))
	private List<turma> turmas;

	
	public List<turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<turma> turmas) {
		this.turmas = turmas;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", serie=" + serie + ", dataNascimento="
				+ dataNascimento;
	}
	
}
