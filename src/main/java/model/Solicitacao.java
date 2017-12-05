package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import model.Disciplina;

public class Solicitacao implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private EnumSolicitacao tipoSolicitacao;
	private Aluno aluno;
	private Professor professor;
	private Disciplina disciplina;
	private LocalDateTime dataEHoraProva;
	private LocalDate dataSolicitacao;
	private LocalDate dataDivulgacaoResultadoProva;
	private String justificativa;

	public Solicitacao() {
	}

	public Solicitacao(Aluno aluno, Professor professor, LocalDateTime dataEHoraProva, LocalDate dataSolicitacao,
			String justificativa, EnumSolicitacao tipoSolicitacao) {
		this.aluno = aluno;
		this.professor = professor;
		this.dataEHoraProva = dataEHoraProva;
		this.dataSolicitacao = dataSolicitacao;
		this.justificativa = justificativa;
		this.tipoSolicitacao = tipoSolicitacao;
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

	public void setTipoSolicitacao(String tipoSolicitacao) {
		switch (tipoSolicitacao) {
		case "Segunda Chamada":
			this.tipoSolicitacao = EnumSolicitacao.SEGUNDA_CHAMADA;
			break;
		case "Recorrecao":
			this.tipoSolicitacao = EnumSolicitacao.RECORRECAO;
			break;
		default:
			this.tipoSolicitacao = null;
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		if (aluno == null)
			throw new IllegalArgumentException("Aluno inválido");
		this.aluno = aluno;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		if (professor == null)
			throw new IllegalArgumentException("Professor inválido");
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		if (disciplina == null)
			throw new IllegalArgumentException("Disciplina inválida");
		this.disciplina = disciplina;
	}

	public LocalDateTime getDataEHoraProva() {
		return dataEHoraProva;
	}

	public void setDataEHoraProva(LocalDateTime dataEHoraProva) {
		if (dataEHoraProva == null)
			throw new IllegalArgumentException("Data inválida");
		this.dataEHoraProva = dataEHoraProva;
	}

	public void setDataEHoraProva(String dataEHoraProva) {
		String[] data = dataEHoraProva.split(" ");
		if (data.length == 2) {
			LocalDate dataProva = null;
			// data
			String[] dataP = data[0].split("-");
			if (dataP.length == 3) {
				dataProva = LocalDate.of(Integer.valueOf(dataP[0]), Integer.valueOf(dataP[1]),
						Integer.valueOf(dataP[2]));
				// hora
				LocalTime horaProva = null;
				String[] horaP = data[1].split(":");
				if (horaP.length == 3) {
					horaProva = LocalTime.of(Integer.valueOf(horaP[0]), Integer.valueOf(horaP[1]));
					// data e hora
					this.setDataEHoraProva(LocalDateTime.of(dataProva, horaProva));
					return;
				}
			} else {
				dataP = data[0].split("/");
				System.out.println("data p "+dataP.toString());
				System.out.println("data p "+dataP[0]);
				if (dataP.length == 3) {
					dataProva = LocalDate.of(Integer.valueOf(dataP[2]), Integer.valueOf(dataP[1]), Integer.valueOf(dataP[0]));
					// hora
					LocalTime horaProva = null;
					String[] horaP = data[1].split(":");
					if (horaP.length == 2) {
						horaProva = LocalTime.of(Integer.valueOf(horaP[0]), Integer.valueOf(horaP[1]));
						// data e hora
						this.setDataEHoraProva(LocalDateTime.of(dataProva, horaProva));
						return;
					}
				}
			}
		}
		throw new RuntimeException("Erro: A data não está no formato correto, valor informado " + dataEHoraProva);
	}

	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		if (dataSolicitacao == null)
			throw new IllegalArgumentException("Data inválida");
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		if (justificativa == null || justificativa.length() < 1)
			throw new IllegalArgumentException("Justificativa não pode ser vazia");
		this.justificativa = justificativa;
	}

	/**
	 * @return the dataDivulgacaoResultadoProva
	 */
	public LocalDate getDataDivulgacaoResultadoProva() {
		return dataDivulgacaoResultadoProva;
	}

	/**
	 * @param dataDivulgacaoResultadoProva
	 *            the dataDivulgacaoResultadoProva to set
	 */
	public void setDataDivulgacaoResultadoProva(LocalDate dataDivulgacaoResultadoProva) {
		this.dataDivulgacaoResultadoProva = dataDivulgacaoResultadoProva;
	}

}
