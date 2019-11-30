package ServerP1.ServerP1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import database.AlunoDAO;
import database.ProfessorDAO;
import model.Aluno;
import model.Professor;

public class Cliente implements Runnable {

	private Socket socket;

	public Cliente(Socket socket) {
		super();
		this.socket = socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public String leitura() {
		String menssagem = "Sem menssagem";

		try {
			Scanner scanner = new Scanner(getSocket().getInputStream());
			if (scanner.hasNextLine()) {
				menssagem = scanner.nextLine().toString();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return menssagem;

	}

	public void enviaResposta(String resposta) {

		
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		OutputStream output;
		System.out.println(resposta);
		try {
			output = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(output, true);
			printWriter.println(resposta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		String[] menssagem;

		menssagem = leitura().split("@");
		System.out.println(menssagem[0] + " " + Thread.currentThread().getId());
		String resposta;
		String[] menssagemFormatada;
		switch (menssagem[0]) {

		case "novo-aluno":

			String[] alunoFormatado = menssagem[1].split("-");

			Aluno aluno = new Aluno(alunoFormatado[0], alunoFormatado[1], alunoFormatado[2], alunoFormatado[3],
					alunoFormatado[4]);
			new AlunoDAO().add(aluno);

			break;
		case "updateProfessor":
			String[] professorAtualizado = menssagem[1].split("-");

			Professor professorAtua = new Professor(professorAtualizado[0], professorAtualizado[1], professorAtualizado[2], professorAtualizado[3],
					professorAtualizado[4]);
			new ProfessorDAO().update(professorAtua);
			
			break;
			
	
		case "updateAluno":
			String[] alunoAtualizado = menssagem[1].split("-");

			Aluno alunoAtua = new Aluno(alunoAtualizado[0], alunoAtualizado[1], alunoAtualizado[2], alunoAtualizado[3],
					alunoAtualizado[4]);
			new AlunoDAO().update(alunoAtua);
			
			break;
			
		case "apagarAluno":
			String alunoApagado = menssagem[1];
			Aluno alunoBusca = new AlunoDAO().get(alunoApagado);
			new AlunoDAO().delete(alunoBusca);
			
			break;
			
		case "apagarProfessor":
			String professorApagado = menssagem[1];
			Professor professorBusca = new ProfessorDAO().get(professorApagado);
			new ProfessorDAO().delete(professorBusca);
			
			break;
			
		case "novo-professor":

			String[] professorFormatado = menssagem[1].split("-");

			Professor professor = new Professor(professorFormatado[0], professorFormatado[1], professorFormatado[2],
					professorFormatado[3], professorFormatado[4]);
			new ProfessorDAO().add(professor);

			break;
		case "Cursos":

			resposta = "0001-Luciano-ADS/0002-Lucas-ADS/";
			enviaResposta(resposta);

			break;
		case "Login-Professor":

			menssagemFormatada = menssagem[1].split("-");

			if (menssagemFormatada[0].equals("rojie") && menssagemFormatada[1].equals("12345678")) {
				resposta = "ok@POO/PPI/";

			} else {
				resposta = "errado";
			}
			enviaResposta(resposta);
			break;

		case "Login-Aluno":

			menssagemFormatada = menssagem[1].split("-");

			if (menssagemFormatada[0].equals("rojie") && menssagemFormatada[1].equals("12345678")) {
				resposta = "ok";

			} else {
				resposta = "errado";
			}
			enviaResposta(resposta);
			break;

		case "minhasTarefas":

			resposta = "TarefaReal1-123/@TarefaEspera1-123/";
			enviaResposta(resposta);

			break;

		case "minhasTurmas":

			resposta = "POO-Lucas/PPI-Alexandre/";
			enviaResposta(resposta);

			break;

		case "minhaLista":

			resposta = "Lucas-123-45678-88-9999/@rojie-3126-888-99-999/";
			enviaResposta(resposta);

			break;

		case "minhaChamada":

			resposta = "Lucas-123-45678-88-9999/rojie-3126-888-99-999/";
			enviaResposta(resposta);

			break;

		default:
			break;
		}

	}

}
