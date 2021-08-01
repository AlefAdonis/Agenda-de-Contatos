package agenda;

/**
 * Representação de um contato na agenda que possui nome, sobrenome e
 * telefone.
 * 
 * @author Álef Ádonis dos Santos Carlos
 *
 */
public class Contato {
	
	/**
	 * Nome do contato.
	 */
	private String nome;
	
	/**
	 * Sobrenome do contato
	 */
	private String sobrenome;
	
	/**
	 * Telefone do contato
	 */
	private String telefone;
	
	/**
	 * Array de strings contendo as tagas relacionadas ao contato
	 */
	private String[] tags;
	
	/**
	 * Determina se um contato é favorito. Por padrão, todos
	 * começam sem estarem favoritados.
	 */
	private boolean favorito = false;
	
	/**
	 * Constrói um contato a partir de seu nome, sobrenome e telefone.
	 * Inicializa o array de tags.
	 * 
	 * @throws NullPointerException caso seja passado um valor nulo ao nome
	 * @throws IllegalArgumentException caso seja passado um valor vazio ao nome
	 * @throws NullPointerException caso seja passado um valor nulo ao telefone
	 * @throws IllegalArgumentException caso seja passado um valor vazio ao telefone
	 * 
	 * @param nome nome do contato
	 * @param sobrenome sobrenome do contato
	 * @param telefone telefone do contato
	 */
	public Contato(String nome, String sobrenome, String telefone) {
		if(nome == null) {
			throw new NullPointerException("Nome Inválido");
		}
		
		if(nome.isBlank()) {
			throw new IllegalArgumentException("Nome Inválido");
		}
		
		if(telefone == null) {
			throw new NullPointerException("Telefone Inválido");
		}
		
		if(telefone.isBlank()) {
			throw new IllegalArgumentException("Telefone Inválido");
		}
		
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.tags = new String[5];
	}
	
	/**
	 * Junta os atributos nome e sobrenome para formar
	 * o nome completo.
	 * 
	 * @return string com o nome completo do contato
	 */
	public String getNomeCompleto() {
		return this.nome + " " + this.sobrenome;
	}
	
	/**
	 * Muda o valor do atributo favorito.
	 */
	public void setFavorito() {
		this.favorito = !(this.favorito);
	}
	
	/**
	 * Cria para cada objeto Contato um hash de identificação.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		return result;
	}

	/**
	 * Determina se um objeto é igual a outro.
	 * 
	 * @param obj o objeto a ser comparado
	 * 
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (other.nome.equals(this.nome) && other.sobrenome.equals(this.sobrenome)) {
			return true;
		}
		
		return false;
	}

	/**
	 * Adiciona uma nova tag para o array de tags.
	 * 
	 * @throws IllegalArgumentException caso a posicao da tag seja maior que 4 ou menor que 0.
	 * @throws NullPointerException caso o apontamento para a tag passada seja nulo.
	 * @throws IllegalArgumentException caso a tag esteja vazia
	 * 
	 * @param posicaoTag posicao da tag no array de tags
	 * @param tag string contendo a mensagem da tag
	 */
	public void setNovaTag(int posicaoTag, String tag){
		if(posicaoTag < 0 || posicaoTag > 4) {
			throw new IllegalArgumentException("POSICAO DE TAG INVÁLIDA");
		}
		if(tag == null) {
			throw new NullPointerException("TAG INVÁLIDA");
		}
		if(tag.isBlank()) {
			throw new IllegalArgumentException("TAG INVÁLIDA");
		}
		this.tags[posicaoTag] = tag;
	}
	
	/**
	 * Retorna uma mensagem contendo os dados do contato. Podem ser no 
	 * formato : <br>
	 * FAVORITADO NOMECOMPLETO TELEFONE TAGS <br>
	 * FAVORITADO NOMECOMPLETO TELEFONE <br>
	 * NOMECOMPLETO TELEFONE TAGS <br>
	 * NOMECOMPLETO TELEFONE
	 * 
	 * @return string com os dados do contato
	 */
	public String toString() {

		String msgTags = "";
		for(int i = 0; i<tags.length; i++){
			if(!(tags[i] == null)){
				msgTags += tags[i] + " ";
			}
		}


		if(favorito == true) {
			if(!(msgTags.equals(""))) {
				return "\n" + "❤️ " + this.getNomeCompleto() + "\n" +
						this.telefone + "\n" +
						msgTags;
			}
			else {
				return "\n" + "❤️ " + this.getNomeCompleto() + "\n" +
						this.telefone;
			}
		}

		if(!(msgTags.equals(""))) {
			return "\n" + this.getNomeCompleto()+ "\n" +
					this.telefone + "\n" +
					msgTags;
		}
		return "\n" + this.getNomeCompleto() + "\n" +
				this.telefone; 
		
	}
}
