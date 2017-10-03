package model;

import java.time.LocalDate;

public class Solicitacao {

	private EnumSolicitacao tipoSolicitacao;
	private Aluno aluno;
	private Professor professor;
	//private Disciplina disciplina;
	private LocalDate dataProva;
	private LocalDate dataSolicitacao;
	private String justificativa;

	
	
	
	public EnumSolicitacao getTipoSolicitacao() {
		return tipoSolicitacao;
	}
	public void setTipoSolicitacao(EnumSolicitacao tipoSolicitacao) {
		this.tipoSolicitacao = tipoSolicitacao;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		if(aluno!=null)
			this.aluno = aluno;
		throw new IllegalArgumentException("Aluno inválido");
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	/*public Disciplina getDisciplina() {
		return disciplina;
	}*/
	/*public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}*/
	public LocalDate getDataProva() {
		return dataProva;
	}
	public void setDataProva(LocalDate dataProva) {
		this.dataProva = dataProva;
	}
	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

}
