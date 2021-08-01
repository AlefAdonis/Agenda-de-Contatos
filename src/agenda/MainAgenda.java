package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author nazarenoandrade e Alef Adonis dos Santos Carlos
 *
 */
public class MainAgenda {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();

		System.out.println("Carregando agenda inicial");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, agenda, scanner);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.println(
				"\n---\nMENU\n" + 
						"(C)adastrar Contato\n" + 
						"(L)istar Contatos\n" + 
						"(E)xibir Contato\n" + 
						"(F)avoritos\n" + 
						"(A)dicionar Favorito\n" +
						"(T)ags\n"+
						"(S)air\n" + 
						"\n" + 
						"Opção> ");
		return scanner.next().toUpperCase();
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "C":
			System.out.println(cadastraContato(agenda, scanner));
			break;
		case "L":
			listaContatos(agenda);
			break;
		case "E":
			System.out.println(exibeContato(agenda, scanner));
			break;
		case "F":
			listaFavorito(agenda);
			break;
		case "A":
			System.out.println(adicionaFavorito(agenda, scanner));
			break;
		case "T":
			adicionaTag(agenda, scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("OPÇÃO INVÁLIDA!");
		}
	}

	/**
	 * Imprime lista de contatos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	*/
	private static void listaContatos(Agenda agenda) {
		System.out.println("\nLista de contatos: ");
		System.out.println("\n"+ agenda.listaContatos());
	}

	/**
	 * Pega os dados de um contato e exibe.
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 * 
	 * @return string com os dados de um contato
	 */
	private static String exibeContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nContato> ");
		int posicao = scanner.nextInt();
		
		return agenda.exibeContato(posicao);
		
	}

	/**
	 * Menu para cadastro de um contato na agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 * 
	 * @return mensagem de erro caso a entrada esteja errado ou uma mensagem 
	 * de sucesso.
	 */
	private static String cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nPosição> ");
		int posicao = scanner.nextInt();
		if(agenda.ehPosicaoInvalida(posicao)) {
			return "POSIÇÃO INVÁLIDA";
		}
		scanner.nextLine();
		
		System.out.print("\nNome> ");
		String nome = scanner.nextLine();
		if(nome.isBlank()) {
			return "CADASTRO INVÁLIDO";
		}
		
		System.out.print("\nSobrenome> ");
		String sobrenome = scanner.nextLine();
		if(sobrenome.isBlank()) {
			return "CADASTRO INVÁLIDO";
		}
		
		System.out.print("\nTelefone> ");
		String telefone = scanner.next();
		if(telefone.isBlank()) {
			return "CADASTRO INVÁLIDO";
		}
		scanner.nextLine();
		return agenda.cadastraContato(posicao, nome, sobrenome, telefone);
	}

	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * Lê uma agenda de um arquivo csv. 
	 * 
	 * @param arquivoContatos O caminho para o arquivo.
	 * @param agenda A agenda que deve ser populada com os dados. 
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		
		int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registros.");
	}
	
	/**
	 * Imprime a lista de favoritos
	 * @param agenda agenda que está sendo usada
	 */
	private static void listaFavorito(Agenda agenda) {
		System.out.println("\n"+ agenda.listaFavoritos());
	}
	
	/**
	 * Menu para adicionar um contato a lista de favoritos
	 * 
	 * @param agenda agenda sendo utilizada
	 * @param scanner scanner para leitura das entradas
	 * 
	 * @return mensagem de erro ou uma mensagem de sucesso 
	 */
	private static String adicionaFavorito(Agenda agenda, Scanner scanner) {	
		try {
		System.out.println("\nContato> ");
		int posicaoContato = scanner.nextInt();
		
		System.out.println("\nPosição> ");
		int posicaoFavorito = scanner.nextInt();
		
		return agenda.adicionaFavorito(posicaoContato, posicaoFavorito);		
		} catch (NumberFormatException nfe) {
			return "Número Inválido";
		}
	}
	
	/**
	 * Menu para adicionar uma taga a um ou mais contatos
	 * 
	 * @param agenda agenda que está sendo utilizada
	 * @param scanner scanner para leitura das entradas
	 */
	private static void adicionaTag(Agenda agenda, Scanner scanner) {
		System.out.println("\nContato(s)>");

		String[] entradas = scanner.next().split(" ");
		int[] contatosPassados = new int[entradas.length];

		for(int i = 0; i < entradas.length; i++){
			contatosPassados[i] = Integer.parseInt(entradas[i]);
		}
		scanner.nextLine();
		
		System.out.println("\nTag> ");
		String tag = scanner.nextLine();
		if(tag.isBlank()) {
			System.out.println("\nTag Inválida");
		}
		
		System.out.println("Posicao tag> ");
		int posicaoTag = scanner.nextInt();
		if(posicaoTag < 0 || posicaoTag > 5) {
			System.out.println("Posicao da Tag Inválida");
		
			agenda.adicionaTag(contatosPassados, tag, posicaoTag);
		}
	}
}
