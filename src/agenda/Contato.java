package agenda;

public class Contato {
	
	private String nome;
	private String sobrenome;
	private String telefone;
	private String[] tags;
	private boolean favorito = false;
	
	public Contato(String nome, String sobrenome, String telefone) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.tags = new String[5];
	}
	
	public String getNomeCompleto() {
		return this.nome + " " + this.sobrenome;
	}
	
	public void setFavorito() {
		this.favorito = true;
	}
	
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(this == obj) {
			return true;
		}
		
		if(this.getClass() != obj.getClass())
	        return false;
		
		Contato contato = (Contato) obj;
		if(this.getNomeCompleto().equals(contato.getNomeCompleto())) {
			return true;
		}
		
		return false;
	}

	public void setNovaTag(int posicaoTag, String tag){
		this.tags[posicaoTag - 1] = tag;
	}
	
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
