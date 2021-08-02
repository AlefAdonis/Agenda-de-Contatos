package agenda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class AgendaTest {

	private Agenda agenda;
	
	@BeforeEach
	void preparaAgenda() {
		this.agenda = new Agenda();
	}
	
	@Test
	void testCadastraContatoValido() {
		assertEquals("CONTATO CADASTRADO", agenda.cadastraContato(1, "alef", "adonis", "9831311213"));
	}
	
	@Test
	void testCadastraContatosDiff() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		assertEquals("CONTATO CADASTRADO", agenda.cadastraContato(2, "matheus", "gaudencio", "9821721334"));
	}
	
	@Test
	void testCadastraContatosDiffMesmaPosicao() {
		agenda.cadastraContato(10, "alef", "adonis", "9831311213");
		assertEquals("CONTATO CADASTRADO", agenda.cadastraContato(1, "matheus", "gaudencio", "9821721334"));
	}
	
	@Test
	void testCadastraContatoJaCadastrado() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		assertEquals("CONTATO JÁ CADASTRADO", agenda.cadastraContato(1, "alef", "adonis", "9831311213"));
	}
	
	@Test
	void testCadastraContatosLimiteInferior() {
		try {
			agenda.cadastraContato(0, "alef", "adonis", "9831311213");
			fail("Deveria haver uma excecao aqui");
		} catch (ArrayIndexOutOfBoundsException aiobe) {}
	}
	
	@Test
	void testCadastraContatosLimiteSuperior() {
		try {
			agenda.cadastraContato(101, "alef", "adonis", "9831311213");
			fail("Deveria haver uma excecao aqui");
		} catch (ArrayIndexOutOfBoundsException aiobe) {}
	}
	
	@Test
	void testCadastraContatoNomeInvalido() {
		try {
			agenda.cadastraContato(1, "", "adonis", "9831311213");
			fail("Deveria haver uma excecao aqui");
		} catch (Exception e) {}
	}

	@Test
	void testCadastraContatoTelefoneInvalido() {
		try {
			agenda.cadastraContato(1, "alef", "adonis", "");
			fail("Deveria haver uma excecao aqui");
		} catch (Exception e) {}
	}
	
	@Test
	void testEhPosicaoInvalida() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		assertFalse(agenda.ehPosicaoInvalida(1));
	}
	
	@Test
	void testEhPosicaoInvalidaContatoNulo() {
		assertFalse(agenda.ehPosicaoInvalida(1));
	}
	
	@Test
	void testEhPosicaoInvalidaLimiteInferior() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		assertEquals(true, agenda.ehPosicaoInvalida(0));
	}
	
	@Test
	void testEhPosicaoInvalidaLimiteSuperior() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		assertEquals(true, agenda.ehPosicaoInvalida(101));
	}
	
	@Test
	void testExibeContato() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		assertEquals("\nalef adonis\n9831311213", agenda.exibeContato(1));
	}
	
	@Test
	void testExibeContatoNulo() {
		assertEquals("POSICAO INVÁLIDA", agenda.exibeContato(1));
	}
	
	@Test
	void testExibeContatoLimiteInfeiror() {
		assertEquals("POSICAO INVÁLIDA", agenda.exibeContato(0));
	}
	
	@Test
	void testExibeContatoLimiteSuperior() {
		assertEquals("POSICAO INVÁLIDA", agenda.exibeContato(101));
	}
	
	@Test
	void testAdicionaFavorito() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		assertEquals("CONTATO FAVORITADO NA POSIÇÃO 1", agenda.adicionaFavorito(1, 1));		
	}
	
	
	
	@Test
	void testAdicionaFavoritoContatoNulo() {
		try {
			agenda.adicionaFavorito(1, 1);
			fail("Deveria haver uma excecao aqui");
		} catch (NullPointerException npe) {}	
	}
	
	@Test
	void testAdicionaFavoritoContatoJaFavoritado() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		agenda.adicionaFavorito(1, 1);
		assertEquals("CONTATO JÁ FAVORITADO", agenda.adicionaFavorito(1, 2));		
	}
	
	@Test
	void testAdicionaFavoritoContatoLimiteSuperior() {
		try {
			agenda.adicionaFavorito(101, 1);
			fail("Deveria haver uma excecao aqui");
		} catch (ArrayIndexOutOfBoundsException aiobe) {}	
	}
	
	@Test
	void testAdicionaFavoritoContatoLimiteInfeiror() {
		try {
			agenda.adicionaFavorito(0, 1);
			fail("Deveria haver uma excecao aqui");
		} catch (ArrayIndexOutOfBoundsException aiobe) {}	
	}
	
	@Test
	void testExibeContatoFavoritado() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		agenda.adicionaFavorito(1, 1);
		assertEquals("\n❤️ alef adonis\n9831311213", agenda.exibeContato(1));
	}
	
	@Test
	void testExibeContatoDesfavoritado() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		agenda.adicionaFavorito(1, 1);
		agenda.cadastraContato(2, "matheus", "gaudencio", "99999-0000");
		agenda.adicionaFavorito(2, 1);
		assertEquals("\nalef adonis\n9831311213", agenda.exibeContato(1));
	}
	
	@Test
	void testAdicionaTag() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		int[] posicaoContato = {1}; 
		agenda.adicionaTag(posicaoContato, "lindo", 1);
		
		assertEquals("\nalef adonis\n9831311213\nlindo ", agenda.exibeContato(1));

	}
	
	@Test
	void testAdicionaTagLimiteInferiorDeTag() {
	
		try {
			agenda.cadastraContato(1, "alef", "adonis", "9831311213");
			int[] posicaoContato = {1};
			agenda.adicionaTag(posicaoContato, "lindo", 0);
			fail("Deveria haver uma excecao aqui");
		} catch(ArrayIndexOutOfBoundsException aiobe) {}
 
	}
	
	@Test
	void testAdicionaTagLimiteSuperiorDeTag() {
	
		try {
			agenda.cadastraContato(1, "alef", "adonis", "9831311213");
			int[] posicaoContato = {1};
			agenda.adicionaTag(posicaoContato, "lindo", 6);
			fail("Deveria haver uma excecao aqui");
		} catch(ArrayIndexOutOfBoundsException aiobe) {}

	}
	
	@Test
	void testAdicionaTagSemContatosPassados() {
	
		try {
			agenda.cadastraContato(1, "alef", "adonis", "9831311213");
			int[] posicaoContato = {};
			agenda.adicionaTag(posicaoContato, "lindo", 1);
			fail("Deveria haver uma excecao aqui");
		} catch(IllegalArgumentException iae) {}

	}
	
	@Test
	void testAdicionaTagInvalida() {
	
		try {
			agenda.cadastraContato(1, "alef", "adonis", "9831311213");
			int[] posicaoContato = {1};
			agenda.adicionaTag(posicaoContato, "", 1);
			fail("Deveria haver uma excecao aqui");
		} catch(IllegalArgumentException iae) {}

	}
	
	@Test
	void testListaContatos() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		assertEquals("1 - alef adonis\n", agenda.listaContatos());
	}
	
	@Test
	void testListaFavoritos() {
		agenda.cadastraContato(1, "alef", "adonis", "9831311213");
		agenda.adicionaFavorito(1, 1);
		assertEquals("1 - alef adonis\n", agenda.listaFavoritos());
	}
	
}
