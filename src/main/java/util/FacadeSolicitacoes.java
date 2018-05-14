package util;

import model.Solicitacao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import dao.DAOFactory;
import dao.DAOFactoryM2C;
import dao.SolicitacaoDAO;
import model.Aluno;
import model.Curso;
import model.Disciplina;
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
				conteudo = new Paragraph("\tEu, " + aluno.getNome() + ", aluno(a) matriculado(a) no " + "Curso de "
						+ aluno.getCurso().getNome() + ", no de matrícula " + aluno.getMatricula() + ", venho "
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
				Font bold = new Font(FontFamily.UNDEFINED, 12, Font.BOLD, BaseColor.BLACK);
				Font normal = new Font(FontFamily.UNDEFINED, 12, Font.NORMAL, BaseColor.BLACK);
				conteudo = new Paragraph();
				conteudo.add(new Chunk("Prof.(a): ", bold));
				conteudo.add(new Chunk(solicitacao.getProfessor().getNome() + "\r\n", normal));
				conteudo.add(new Chunk("Coordenador do Curso/Prof.(a): ", bold));
				conteudo.add(new Chunk(solicitacao.getAluno().getCurso().getCoordenador().getNome() + "\r\n", normal));
				conteudo.add(new Chunk("Nome do aluno: ", bold));
				conteudo.add(new Chunk(solicitacao.getAluno().getNome() + "\r\n", normal));
				conteudo.add(new Chunk("Matrícula N°: ", bold));
				conteudo.add(new Chunk(solicitacao.getAluno().getMatricula() + "\r\n", normal));
				conteudo.add(new Chunk("Curso: ", bold));
				conteudo.add(new Chunk(curso.getNome() + "\r\n", normal));
				conteudo.add(new Chunk("Data da prova: ", bold));
				conteudo.add(new Chunk(dataProvaString + "\r\n", normal));
				conteudo.add(new Chunk("Disciplina: ", bold));
				conteudo.add(new Chunk(solicitacao.getDisciplina().getNome() + "\r\n", normal));
				conteudo.add(new Chunk("Data deste requerimento: ", bold));
				conteudo.add(new Chunk(
						FacadeSolicitacoes.converterLocalDateToString(dataEHoraProva.toLocalDate()) + "\r\n", normal));
				conteudo.add(new Chunk("E-mail do aluno: ", bold));
				conteudo.add(new Chunk(solicitacao.getAluno().getEmail() + "\r\n", normal));
				conteudo.add(new Chunk("Motivo da falta: ", bold));
				conteudo.add(new Chunk(solicitacao.getJustificativa() + "\r\n", normal));
				conteudo.add(new Chunk("\r\n\r\n\r\n\r\n\r\n", normal));
				conteudo.add(new Chunk(
						"Venho por meio do presente, solicitar a realização da prova de segunda chamada da disciplina acima "
								+ "indicada, levando em conta a previsão do § 3o do Art. 110 do Regimento Geral da UFC, abaixo"
								+ "transcrito:\n" + "Art. 110...\r\n"
								+ "§ 3o. - Será assegurada ao aluno a segunda chamada das provas, desde que solicitada, por escrito, até "
								+ "03 (três) dias úteis decorridos após a realização da prova em primeira chamada.\n\n\n\n"
								+ "Atenciosamente,",
						normal));
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
							solicitacao.getProfessor().getEmail(), solicitacao.getProfessor().getNome(), localArquivo,
							nomeArquivo);

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

		if (dataProva.plusDays(4).isAfter(hoje) && dataProva.isBefore(LocalDate.now().plusDays(1))) {
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

	public static List<Disciplina> buscarDiscPorCursoEProfDif(int idCurso, int idProfessor) {

		List<Disciplina> disciplinasDoCurso = DAOFactoryM2C.criarDisciplinaDAO().buscarPorCurso(idCurso);
		List<Disciplina> disciplinasfinais = new ArrayList<>();
		
		if (disciplinasDoCurso.size() > 0) {
			
			for (Disciplina dc : disciplinasDoCurso) {
				if (dc.getProfessor().getId() != idProfessor) {
					disciplinasfinais.add(dc);
				}
			}
		}else {
		}
		return disciplinasfinais;

	}

}
