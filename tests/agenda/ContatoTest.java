package agenda;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class ContatoTest {
	
	private Contato contato;

	@BeforeEach
	void preparaContato() {
		this.contato = new Contato("alef", "adonis", "981264113");
	}

	@Test
	void testContatoInvalidoNomeVazio() {
		try {
			Contato contatoInvalido = new Contato("", "Adonis", "981264113");
			fail("Deveria ter lançado um erro");
		} catch (IllegalArgumentException iae) {
			
		}
		
	}
	
	@Test
	void testContatoInvalidoNomeNulo() {
		try {
			Contato contatoInvalido = new Contato(null, "Adonis", "981264113");
			fail("Deveria ter lançado um erro");
		} catch (NullPointerException npe) {
			
		}
		
	}
	
	@Test
	void testContatoTelefoneNulo() {
		try {
			Contato contatoInvalido = new Contato("Alef", "Adonis", null);
			fail("Deveria ter lançado um erro");
		} catch (NullPointerException npe) {
		}
	}
	
	@Test
	void testContatoTelefoneInvalido() {
		try {
			Contato contatoInvalido = new Contato("Alef", "Adonis", "");
			fail("Deveria ter lançado um erro");
		} catch (IllegalArgumentException iae) {
		}
	}

	@Test
	void testEquals() {
		Contato contato2 = new Contato("alef" , "adonis", "987201321");	
		assertEquals(contato, contato2);
	}
	
	@Test
	void testEqualsTelefoneDistinto() {
		Contato contato2 = new Contato("alef" , "adonis", "998189191");
		
		assertEquals(contato, contato2);
	}
	
	@Test
	void testEqualsSobrenomeDiferente() {
		Contato contato2 = new Contato("alef" , "oliveira", "998189191");
		
		assertNotEquals(contato, contato2);
	}
	
	@Test
	void testEqualsNomeDiferente() {
		Contato contato2 = new Contato("luis" , "adonis", "998189191");
		
		assertNotEquals(contato, contato2);
	}
	
	@Test
	void testNotEquals() {
		Contato contato2 = new Contato("luis" , "alberto", "998189191");
		
		assertNotEquals(contato, contato2);
	}
	
	@Test
	void testGetNomeCompleto() {
		assertEquals("alef adonis" , contato.getNomeCompleto());
	}	
	
	@Test
	void testSetNovaTag() {
		contato.setNovaTag(0, "lindo");
	}
	
	@Test
	void testSetNovaTagLimiteInferior() {
		try {
			contato.setNovaTag(-1, "legal");
			fail("Deveria haver uma excecao aqui");
		} catch (Exception iae) {}
	}
	
	@Test
	void testSetNovaTagLimiteSuperior() {
		try {
			contato.setNovaTag(5, "legal");
			fail("Deveria haver uma excecao aqui");
		} catch (Exception iae) {}
	}
	
	@Test
	void testSetNovaTagVazia() {
		try {
			contato.setNovaTag(1, "");
			fail("Deveria haver uma excecao aqui");
		} catch (Exception iae) {}
	}

	void testSetNovaTagNula() {
		try {
			contato.setNovaTag(1, null);
			fail("Deveria haver uma excecao aqui");
		} catch (NullPointerException npe) {}
	}
	
	@Test
	void testToString() {
		assertEquals("\nalef adonis\n981264113", contato.toString());
	}
	
	@Test
	void testToStringFavorito() {
		contato.setFavorito();
		assertEquals("\n❤️ alef adonis\n981264113", contato.toString());
	}
	
	@Test 
	void testToStringTag() {
		contato.setNovaTag(1, "lindo");
		assertEquals("\nalef adonis\n981264113\nlindo ", contato.toString());
	}
	
	@Test
	void testToStringFavoritoTag() {
		contato.setFavorito();
		contato.setNovaTag(1, "lindo");
		assertEquals("\n❤️ alef adonis\n981264113\nlindo ", contato.toString());
	}
	
	@Test
	void testHashCode() {
	 Contato contatoIgual = new Contato("alef", "adonis", "981264113");
	 assertTrue(this.contato.hashCode() == contatoIgual.hashCode());
	}
	
}
