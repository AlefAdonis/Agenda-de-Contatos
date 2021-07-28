package agenda;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos. 
 * 
 * @author nazareno
 *
 */
public class Agenda {
	
	private static final int TAMANHO_AGENDA = 100;
	private static final int TAMANHO_FAVORITOS = 10;
	private Contato[] favoritos;
	private Contato[] contatos;
	

	/**
	 * Cria uma agenda.
	 */
	public Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
		this.favoritos = new Contato[TAMANHO_FAVORITOS];
	}
	
	/**
	 * Acessa a lista de contatos mantida.
	 * @return O array de contatos.
	 */
	public String getListaContatos() {
		String msg = "";
		
		for(int i = 0; i < contatos.length; i++) {
			if(!(contatos[i] == null)) {
				msg += (i + 1) + " - " + contatos[i].getNomeCompleto() + "\n";
			}
		}
		
		return msg;
	}

	/**
	 * Acessa os dados de um contato específico.
	 * @param posicao Posição do contato na agenda.
	 * @return Dados do contato. Null se não há contato na posição.
	 */
	public String getContato(int posicao) {
		posicao -= 1;
		if(!(this.getPosicaoInvalida(posicao))) {
			return "POSIÇÃO INVÁLIDA";
		}
		
		return contatos[posicao].toString();
	}

	/**
	 * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior. 
	 * @param posicao Posição do contato.
	 * @param nome Nome do contato.
	 * @param sobrenome Sobrenome do contato.
	 * @param telefone Telefone do contato.
	 */
	public String cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
		posicao -= 1;
		Contato novoContato = new Contato(nome, sobrenome, telefone);
		
		if(!(novoContato.equals(this.contatos[posicao]))) {
			this.contatos[posicao] = novoContato;
			
			return "CONTATO CADASTRADO";
		}
		
		return "CONTATO JÁ CADASTRADO";
	}
	
	public boolean getPosicaoInvalida(int posicao) {
		
		if(posicao >= 100 || posicao <= 0) {
			return true;
		}
		
		if(contatos[posicao] == null) {
			return false;
		}
		
		return true;
	}
	
	public String adicionaFavorito(int posicaoContato, int posicaoFavorito) {
		if((this.getPosicaoInvalida(posicaoContato)) && posicaoFavorito > 0 && posicaoFavorito <= 11) {
			
			if(!(this.favoritoCadastrado(posicaoFavorito, posicaoContato))){
				this.contatos[posicaoContato - 1].setFavorito();
				this.favoritos[posicaoFavorito - 1] = this.contatos[posicaoContato - 1];
				
				return "CONTATO FAVORITADO NA POSIÇÃO " + posicaoFavorito;
			}
		}
		
		return "CONTATO JÁ FAVORITADO";
	}

	private boolean favoritoCadastrado(int posicaoFavorito, int posicaoContato) {
		for(int it = 0; it <favoritos.length; it++) {
			if(contatos[posicaoContato].equals(favoritos[it]) && !((posicaoFavorito -1) == it)) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getListaFavoritos() {
		String msg = "";
		
		for(int i = 0; i < favoritos.length; i++) {
			if(!(favoritos[i] == null)) {
				msg += (i + 1) + " - " + favoritos[i].getNomeCompleto() + "\n";
			}
		}
		
		return msg;
	}

	public void adiconaTag(int[] contatosPassados, String tag, int posicaoTag) {

		for(int it = 0; it < contatosPassados.length; it++){
			for(int i = 0; i < contatos.length; i++) {
				if(contatosPassados[it] == (i+1) && !(contatos[i] == null)){
					contatos[i].setNovaTag(posicaoTag, tag);
				}
		}
		}
	}
}
