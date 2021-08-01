package agenda;

/**
 * Uma agenda responsável por administrar os contatos, podendo
 * cadastrar, exibir, favoritar e adicionar tags. Podem existir 100 contatos. 
 * 
 * @author Alef Adonis dos Santos Carlos
 *
 */
public class Agenda {
	
	/**
	 * Tamanho constante que a agenda pode ter, e consequentemente,
	 * número máximo de contatos a serem cadastrados.
	 */
	private static final int TAMANHO_AGENDA = 100;
	
	/**
	 * Tamanho constante que a lista de favoritos pode ter, e consequentemente,
	 * número máximo de contatos a serem cadastrados.
	 */
	private static final int TAMANHO_FAVORITOS = 10;
	
	/**
	 * Array contendo os favoritos do tipo contato.
	 */
	private Contato[] favoritos;
	
	/**
	 * Array contendo os contatos do tipo contato.
	 */
	private Contato[] contatos;
	

	/**
	 * Constrói uma agenda, inicializando o array de contatos
	 * e o de favoritos.
	 */
	public Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
		this.favoritos = new Contato[TAMANHO_FAVORITOS];
	}
	
	/**
	 * Cria uma string contendo os contatos em forma de lista.
	 * Possui o formato: POSICAODOCONTATO - NOMECOMPLETODOCONTATO.
	 * 
	 * @return uma string com a lista de contatos cadastrados.
	 */
	public String listaContatos() {
		String msg = "";
		
		for(int i = 0; i < contatos.length; i++) {
			if(!(contatos[i] == null)) {
				msg += (i + 1) + " - " + contatos[i].getNomeCompleto() + "\n";
			}
		}
		
		return msg;
	}

	/**
	 * Exibe os dados do contato no formato :
	 * FAVORITO(Opcional) NOME SOBRENOME
	 * TELEFONE TAGS(Opcional). 
	 * 
	 * @param posicao posicao do contato na agenda
	 * 
	 * @return string com mensagem POSICAO INVALIDA caso a posicao seja inválida
	 * ou string com os dados do contato.
	 */
	public String exibeContato(int posicao) {
		posicao -= 1;
		if(posicao < 0 || posicao > 99) {
			return "POSICAO INVÁLIDA";
		}
		
		if(contatos[posicao] == null) {
			return "POSICAO INVÁLIDA";
		}
	
		return contatos[posicao].toString();
	}

	/**
	 * Faz o cadastro de um novo contato na agenda.
	 * 
	 * @throws ArrayIndexOutOfBoundsException Inválida caso a posicao seja maior que 99 ou menor que 0
	 * 
	 * @param posicao posicao do contato na agenda
	 * @param nome nome do contato
	 * @param sobrenome sobrenome do contato
	 * @param telefone telefone do contato
	 * 
	 * @return uma mensagem de sucesso (CONTATO CADASTRADO) ou de fracasso 
	 * (CONTATO JA CADASTRADO).
	 */
	public String cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
		posicao -= 1;
		if(posicao < 0 || posicao > 99) {
			throw new ArrayIndexOutOfBoundsException("Posição Inválida");
		}
		
		Contato novoContato = new Contato(nome, sobrenome, telefone);
		
		if(!(novoContato.equals(this.contatos[posicao]))) {
			this.contatos[posicao] = novoContato;
			
			return "CONTATO CADASTRADO";
		}
		
		return "CONTATO JÁ CADASTRADO";
	}
	
	/**
	 * Determina se a posição passada é inválida.
	 * 
	 * @param posicao posicao do contato
	 * 
	 * @return boolean
	 */
	public boolean ehPosicaoInvalida(int posicao) {
		posicao -= 1;
		if(posicao > 99 || posicao < 0){
			return true;
		}
		if(contatos[posicao] == null) {
			return false;
		}
		return false;	
	}
	
	/**
	 * Adiciona um contato ja cadastrado numa lista de favoritos
	 * 
	 * @throws ArrayIndexOutOfBoundsException caso a posicao seja maior que 99 ou menor que 0
	 * @throws NullPointerException caso não exista contato na posicaoContato
	 * 
	 * @param posicaoContato posicao do contato a ser cadastrado
	 * @param posicaoFavorito posicao a adicionar na lista de favoritos
	 * 
	 * @return uma mensagem de sucesso (CONTATO FAVORITADO NA POSIÇÃO POSICAOFAVORITO) 
	 * ou uma mensagem de fracasso (CONTATO JA CADASTRADO)
	 */
	public String adicionaFavorito(int posicaoContato, int posicaoFavorito) {
		posicaoFavorito -= 1;
		posicaoContato -= 1;
		
		if(posicaoContato < 0 || posicaoContato > 99) {
			throw new ArrayIndexOutOfBoundsException("POSICAO INVÁLIDA");
		}
		
		if(contatos[posicaoContato] == null) {
			throw new NullPointerException("CONTATO NÃO CADASTRADO");
		}
		
		if(!(this.favoritos[posicaoFavorito] == null)) {
			this.favoritos[posicaoFavorito].setFavorito();
		}
		
		if(!(this.favoritoCadastrado(posicaoFavorito, posicaoContato))){
				this.contatos[posicaoContato].setFavorito();
				this.favoritos[posicaoFavorito] = this.contatos[posicaoContato];
				
				return "CONTATO FAVORITADO NA POSIÇÃO " + (posicaoFavorito + 1);
		}
		
		return "CONTATO JÁ FAVORITADO";
	}

	/**
	 * Determina se um contato já foi favoritado.
	 * 
	 * @throws ArrayIndexOutOfBoundsException caso a posicao seja maior que 9 ou menor que 0
	 * @throws ArrayIndexOutOfBoundsException caso a posicao seja maior que 99 ou menor que 0
	 * 
	 * @param posicaoFavorito posicao do contato na lista de favoritos
	 * @param posicaoContato posicao do contato na agenda
	 * 
	 * @return boolean
	 */
	private boolean favoritoCadastrado(int posicaoFavorito, int posicaoContato) {
		
		if(posicaoFavorito < 0 || posicaoFavorito > 9) {
			throw new ArrayIndexOutOfBoundsException("POSICAO INVÁLIDA");
		}
		if(posicaoContato < 0 || posicaoContato > 99) {
			throw new ArrayIndexOutOfBoundsException("POSICAO INVÁLIDA");
		}
		
		for(int it = 0; it <favoritos.length; it++) {
			if(contatos[posicaoContato].equals(favoritos[it]) && !((posicaoFavorito) == it)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Cria uma string contendo os contatos da lista de favoritos
	 * em forma de lista.
	 * Possui o formato: POSICAODOCONTATO - NOMECOMPLETODOCONTATO.
	 * 
	 * @return uma string com a lista de favoritos cadastrados.
	 */
	public String listaFavoritos() {
		String msg = "";
		
		for(int i = 0; i < favoritos.length; i++) {
			if(!(favoritos[i] == null)) {
				msg += (i + 1) + " - " + favoritos[i].getNomeCompleto() + "\n";
			}
		}
		
		return msg;
	}

	/**
	 * Adiciona uma tag a um ou mais contatos.
	 * 
	 * @throws IllegalArgumentException caso não sejam passados contatos 
	 * @throws ArrayIndexOutOfBoundsException caso a posicao da tag seja maior que 4 ou menor que 0
	 * @throws IllegalArgumentException caso seja passado uma tag vazia
	 * 
	 * @param contatosPassados array contendo as posicoes dos contatos
	 * @param tag string contendo a mensagem da tag
	 * @param posicaoTag posicao da tag no contato
	 */
	public void adicionaTag(int[] contatosPassados, String tag, int posicaoTag) {
		if(contatosPassados.length == 0) {
			throw new IllegalArgumentException("NAO FORAM PASSADOS CONTATOS");
		}
		
		posicaoTag -= 1;
		if(posicaoTag < 0 || posicaoTag > 4) {
			throw new ArrayIndexOutOfBoundsException("POSICAO DA TAG INVÁLIDA");
		}
		if(tag.isBlank()) {
			throw new IllegalArgumentException("TAG INVÁLIDA");
		}
		
		int setado = 0;
		for (int it = 0; it < contatosPassados.length; it++) {
			for (int i = 0; i < contatos.length; i++) {
				if (contatosPassados[it] == (i + 1) && !(contatos[i] == null)) {
					contatos[i].setNovaTag(posicaoTag, tag);
					setado++;
				}
			}
		} 
	}
}
