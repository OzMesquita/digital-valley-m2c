package model;

import java.io.Serializable;
import java.time.LocalDate;
import model.Disciplina;
public class Solicitacao implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private EnumSolicitacao tipoSolicitacao;
	private Aluno aluno;
	private Professor professor;
	private Disciplina disciplina;
	private LocalDate dataProva;
	private LocalDate dataSolicitacao;
	private String justificativa;

	public Solicitacao(){}
	
	public Solicitacao(Aluno aluno, Professor professor, LocalDate dataProva,LocalDate dataSolicitacao, String justificativa){
		this.aluno = aluno;
		this.professor = professor;
		this.dataProva = dataProva;
		this.dataSolicitacao = dataSolicitacao;
		this.justificativa = justificativa;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
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
		if(aluno==null)
			throw new IllegalArgumentException("Aluno inválido");
		this.aluno = aluno;		
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		if(professor==null)
			throw new IllegalArgumentException("Professor inválido");
		this.professor = professor;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	public void setDisciplina(Disciplina disciplina) {
		if(disciplina==null)
			throw new IllegalArgumentException("Disciplina inválida");	
		this.disciplina = disciplina;
	}

	public LocalDate getDataProva() {
		return dataProva;
	}

	public void setDataProva(LocalDate dataProva) {
		if(dataProva==null)
			throw new IllegalArgumentException("Data inválida");	
		this.dataProva = dataProva;
	}
	
	public void setDataProva(String dataProva) {
		String[] data = dataProva.split("/");
		if (data.length == 3) {
			this.setDataProva(
					LocalDate.of(Integer.valueOf(data[2]), Integer.valueOf(data[1]), Integer.valueOf(data[0])));
		} else {
			throw new RuntimeException(
					"Erro: A data não está no formato correto, valor informado " + dataProva);
		}
	}
	
	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}
	
	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		if(dataSolicitacao==null)
			throw new IllegalArgumentException("Data inválida");
		this.dataSolicitacao = dataSolicitacao;
	}
	
	public String getJustificativa() {
		return justificativa;
	}
	
	public void setJustificativa(String justificativa) {
		if(justificativa==null || justificativa.length()<1)
			throw new IllegalArgumentException("Justificativa não pode ser vazia");
		this.justificativa = justificativa;
	}
	
	
}
