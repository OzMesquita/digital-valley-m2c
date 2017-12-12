package util;

import model.Solicitacao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import dao.DAOFactory;
import dao.DAOFactoryM2C;
import dao.SolicitacaoDAO;
import model.Aluno;
import model.Curso;
import model.Email;
import model.EnumSolicitacao;
import model.Professor;

public class FacadeSolicitacoes {
	private FacadeSolicitacoes() {
		//
	}

	public static void gerarPDFDaSolicitacao(Solicitacao solicitacao, String localArquivo) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(localArquivo));
			document.open();
			// cabecalho
			// logo
			Image image = Image.getInstance(Constantes.getLOGO_UFC());
			image.setAlignment(Image.MIDDLE);
			image.scaleAbsoluteWidth(90);
			image.scaleAbsoluteHeight(60);
			document.add(image);
			// conteudo
			Aluno aluno = solicitacao.getAluno();
			LocalDateTime dataEHoraProva = solicitacao.getDataEHoraProva();
			String dataProvaString = FacadeSolicitacoes.converterLocalDateToString(dataEHoraProva.toLocalDate());
			Curso curso = solicitacao.getAluno().getCurso();
			// campus
			Paragraph cabecalho = new Paragraph(
					"CAMPUS DA UFC DE RUSSAS\nCOORDENAÇÃO DO CURSO DE " + curso.getNome().toUpperCase() + "\n\n\n\n\n");
			cabecalho.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(cabecalho);
			Paragraph conteudo = null;
			if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.RECORRECAO)) {
				conteudo = new Paragraph("Eu, " + aluno.getNome() + ", aluno(a) matriculado(a) no " + "Curso de "
						+ aluno.getCurso().getNome() + ", no de matrícula " + aluno.getMatricula() + ", vem mui "
						+ "respeitosamente, perante Vossa Senhoria requerer recorreção da prova da disciplina "
						+ solicitacao.getDisciplina().getNome() + ", que realizou-se no dia " + dataProvaString
						+ ", no " + "horário de "
						+ FacadeSolicitacoes.converterLocalTimeToString(dataEHoraProva.toLocalTime())
						+ ", e tive conhecimento do resultado no dia "
						+ solicitacao.getDataDivulgacaoResultadoProva()
								.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"))
						+ ". Apresento este " + "requerimento devido ao(s) seguinte(s) motivo(s):\n"
						+ solicitacao.getJustificativa() + ".");
			} else if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.SEGUNDA_CHAMADA)) {
				conteudo = new Paragraph("Prof.(a): " + solicitacao.getProfessor().getNome() + "\r\n"
						+ "Coordenador do Curso/Prof.(a): "
						+ solicitacao.getAluno().getCurso().getCoordenador().getNome() + "\r\n" + "Nome do aluno: "
						+ solicitacao.getAluno().getNome() + "  Matrícula N°: " + solicitacao.getAluno().getMatricula()
						+ "\r\n" + "Curso: " + curso.getNome() + " Data da prova: " + dataProvaString + "\r\n"
						+ "Disciplina: " + solicitacao.getDisciplina().getNome() + " Data deste requerimento: "
						+ FacadeSolicitacoes.converterLocalDateToString(dataEHoraProva.toLocalDate()) + "\r\n"
						+ "E-mail do aluno: " + solicitacao.getAluno().getEmail() + "\r\n" + "Motivo da falta: "
						+ solicitacao.getJustificativa() + "\r\n"
						+ "Venho por meio do presente, solicitar a realização da prova de segunda chamada da disciplina acima "
						+ "indicada, levando em conta a previsão do § 3o do Art. 110 do Regimento Geral da UFC, abaixo"
						+ " transcrito:\n" + "Art. 110...\r\n"
						+ "§ 3o. - Será assegurada ao aluno a segunda chamada das provas, desde que solicitada, por escrito, até"
						+ "03 (três) dias úteis decorridos após a realização da prova em primeira chamada.\n\n\n\n"
						+ "Atenciosamente,");
			}
			document.add(conteudo);
			Paragraph assAluno = new Paragraph(
					"\n\n\n\n\n\n_______________________________________________\n(Assinatura do Aluno)");
			assAluno.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(assAluno);
			document.close();
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void enviarEmailSolicitacao(Solicitacao solicitacao, String localArquivo, String nomeArquivo) {
		if (solicitacao != null) {
			String msg = "";
			if (solicitacao.getTipoSolicitacao() != null) {
				if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.SEGUNDA_CHAMADA)) {
					msg = "O(A) Aluno(a) " + solicitacao.getAluno().getNome()
							+ " solicitou a Segunda Chamada da prova de " + solicitacao.getDisciplina().getNome()
							+ " realizada na data de: "
							+ FacadeSolicitacoes.converterLocalDateTimeToString(solicitacao.getDataEHoraProva())
							+ " com a justificativa de : \"" + solicitacao.getJustificativa() + "\"";

					Email e = new Email();
					e.sendEmailWithAttachment("Solicitação de Segunda chamada", msg,
							solicitacao.getProfessor().getEmail(), solicitacao.getProfessor().getNome(), localArquivo, nomeArquivo);

				} else if (solicitacao.getTipoSolicitacao().equals(EnumSolicitacao.RECORRECAO)) {
					msg = "O(A) Aluno(a) " + solicitacao.getAluno().getNome() + " solicitou a Recorreção da prova de "
							+ solicitacao.getDisciplina().getNome() + " realizada na data de: "
							+ FacadeSolicitacoes.converterLocalDateTimeToString(solicitacao.getDataEHoraProva()) + ", "
							+ "que teve o resultado divulgado em "
							+ FacadeSolicitacoes
									.converterLocalDateToString(solicitacao.getDataDivulgacaoResultadoProva())
							+ " com a justificativa de : \"" + solicitacao.getJustificativa() + "\"";
					Email e = new Email();
					e.sendEmailWithAttachment("Solicitação de Recorreção", msg,
							solicitacao.getDisciplina().getCurso().getCoordenador().getEmail(),
							solicitacao.getProfessor().getNome(), localArquivo, nomeArquivo);
				}
			} else {
				throw new IllegalArgumentException("Tipo de solicitação não informado corretamente, valor informado: "
						+ solicitacao.getTipoSolicitacao());

			}
			if (msg.equals("")) {
				throw new IllegalArgumentException("Erro ao enviar o email");
			}
		} else {
			throw new NullPointerException("Solicitação nula.");
		}

	}

	public static boolean verificarDias(LocalDate dataProva) {
		LocalDate hoje = LocalDate.now();
		if (hoje.getDayOfWeek().name().equals("TUESDAY")) {
			hoje = hoje.minusDays(2);
		} else if (hoje.getDayOfWeek().name().equals("MONDAY")) {
			hoje = hoje.minusDays(2);
		} else if (hoje.getDayOfWeek().name().equals("SUNDAY")) {
			hoje = hoje.minusDays(1);
		}

		if (dataProva.plusDays(4).isAfter(hoje)) {
			return true;
		} else {
			return false;
		}

	}

	public static void salvarSolicitacao(Solicitacao solicitacao) {
		SolicitacaoDAO sDAO = DAOFactoryM2C.criarSolicitacaoDAO();
		sDAO.cadastrar(solicitacao);
	}

	public static Solicitacao buscarPorId(int id) {
		Solicitacao solicitacao = DAOFactoryM2C.criarSolicitacaoDAO().buscarPorId(id);
		solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(solicitacao.getAluno().getId()));
		solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(solicitacao.getProfessor().getId()));
		solicitacao.setDisciplina(DAOFactoryM2C.criarDisciplinaDAO().getById(solicitacao.getDisciplina().getId()));
		return solicitacao;
	}

	public static ArrayList<Solicitacao> buscarPorAluno(Aluno aluno, int inicio, int fim) {
		ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) DAOFactoryM2C.criarSolicitacaoDAO()
				.buscarPorAluno(aluno, inicio, fim);
		for (Solicitacao solicitacao : solicitacoes) {
			solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(solicitacao.getProfessor().getId()));
			solicitacao.setDisciplina(DAOFactoryM2C.criarDisciplinaDAO().getById(solicitacao.getDisciplina().getId()));
		}
		return solicitacoes;
	}

	public static ArrayList<Solicitacao> buscarPorProfessor(Professor professor, int inicio, int fim) {
		ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) DAOFactoryM2C.criarSolicitacaoDAO()
				.buscarPorProfessor(professor, inicio, fim);
		for (Solicitacao solicitacao : solicitacoes) {
			solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(solicitacao.getAluno().getId()));
			solicitacao.setDisciplina(DAOFactoryM2C.criarDisciplinaDAO().getById(solicitacao.getDisciplina().getId()));
		}
		return solicitacoes;
	}

	public static ArrayList<Solicitacao> listar(int inicio, int fim) {
		ArrayList<Solicitacao> solicitacoes = (ArrayList<Solicitacao>) DAOFactoryM2C.criarSolicitacaoDAO()
				.listar(inicio, fim);
		for (Solicitacao solicitacao : solicitacoes) {
			solicitacao.setAluno(DAOFactory.criarAlunoDAO().buscar(solicitacao.getAluno().getId()));
			solicitacao.setProfessor(DAOFactory.criarProfessorDAO().buscar(solicitacao.getProfessor().getId()));
			solicitacao.setDisciplina(DAOFactoryM2C.criarDisciplinaDAO().getById(solicitacao.getDisciplina().getId()));
		}
		return solicitacoes;
	}

	public static String converterLocalDateTimeToString(LocalDateTime dataHora) {
		return dataHora.format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm"));
	}

	public static String converterLocalDateToString(LocalDate data) {
		return data.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
	}

	public static String converterLocalTimeToString(LocalTime hora) {
		return hora.format(DateTimeFormatter.ofPattern("HH:mm"));
	}

	public static LocalTime converterStringToLocalTime(String horaProva) {
		String[] hora = horaProva.split(":");
		if (hora.length == 2) {
			try {
				return LocalTime.of(Integer.valueOf(hora[0]), Integer.valueOf(hora[1]));
			} catch (Exception e) {
				throw new RuntimeException("Horário inválido, valor informado: " + horaProva);
			}
		} else {
			throw new RuntimeException("Horário inválido, valor informado: " + horaProva);
		}
	}

}
