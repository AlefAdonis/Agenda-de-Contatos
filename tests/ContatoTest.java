import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agenda.Contato;

class ContatoTest {

	private Contato contatoBase;
	
	@BeforeEach
	void preparaContato() {
		this.contatoBase = new Contato("Matheus", "Gaudencio", "555-5551");
	}
	
	@Test
	void testNomeCompleto() {
		String msg = "Esperando obter o nome completo";
		assertEquals( "Matheus Gaudencio", this.contatoBase.getNomeCompleto(), msg);
	}
	
	@Test
	void testFavoritado() {
		this.contatoBase.setFavorito();
		assertEquals("\n❤️ Matheus Gaudencio/n555-5551", this.contatoBase.toString());
	}
}
