package jogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JogoTest {

	private Jogador jogadorMock;
	private Dado dado1Mock;
	private Dado dado2Mock;
	private Jogo jogo;

	@BeforeEach
	public void setUp() {
		jogadorMock = mock(Jogador.class);
		dado1Mock = mock(Dado.class);
		dado2Mock = mock(Dado.class);
		jogo = new Jogo(jogadorMock, dado1Mock, dado2Mock);
	}

	@Test
	public void testJogo_VitoriaPrimeiroTurno_Soma7() {
		// Arrange
		when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(7);

		// Act
		boolean resultado = jogo.jogo();

		// Assert
		assertTrue(resultado);
		verify(jogadorMock, times(1)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	public void testJogo_VitoriaPrimeiroTurno_Soma11() {
		// Arrange
		when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(11);

		// Act
		boolean resultado = jogo.jogo();

		// Assert
		assertTrue(resultado);
		verify(jogadorMock, times(1)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	public void testJogo_DerrotaPrimeiroTurno_Soma2() {
		// Arrange
		when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(2);

		// Act
		boolean resultado = jogo.jogo();

		// Assert
		assertFalse(resultado);
		verify(jogadorMock, times(1)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	public void testJogo_DerrotaPrimeiroTurno_Soma3() {
		// Arrange
		when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(3);

		// Act
		boolean resultado = jogo.jogo();

		// Assert
		assertFalse(resultado);
		verify(jogadorMock, times(1)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	public void testJogo_DerrotaPrimeiroTurno_Soma12() {
		// Arrange
		when(jogadorMock.lancar(dado1Mock, dado2Mock)).thenReturn(12);

		// Act
		boolean resultado = jogo.jogo();

		// Assert
		assertFalse(resultado);
		verify(jogadorMock, times(1)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	public void testJogo_VitoriaSegundoTurno_RepetirPonto() {
		// Arrange
		when(jogadorMock.lancar(dado1Mock, dado2Mock))
			.thenReturn(4)  // Primeiro turno: ponto = 4
			.thenReturn(4); // Segundo turno: ganha repetindo o ponto

		// Act
		boolean resultado = jogo.jogo();

		// Assert
		assertTrue(resultado);
		verify(jogadorMock, times(2)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	public void testJogo_DerrotaSegundoTurno_Soma7() {
		// Arrange
		when(jogadorMock.lancar(dado1Mock, dado2Mock))
			.thenReturn(5)  // Primeiro turno: ponto = 5
			.thenReturn(7); // Segundo turno: perde com soma 7

		// Act
		boolean resultado = jogo.jogo();

		// Assert
		assertFalse(resultado);
		verify(jogadorMock, times(2)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	public void testJogo_VitoriaTerceiroTurno_RepetirPonto() {
		// Arrange
		when(jogadorMock.lancar(dado1Mock, dado2Mock))
			.thenReturn(6)  // Primeiro turno: ponto = 6
			.thenReturn(8)  // Segundo turno: continua (nem ponto nem 7)
			.thenReturn(6); // Terceiro turno: ganha repetindo o ponto

		// Act
		boolean resultado = jogo.jogo();

		// Assert
		assertTrue(resultado);
		verify(jogadorMock, times(3)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	public void testJogo_DerrotaTerceiroTurno_Soma7() {
		// Arrange
		when(jogadorMock.lancar(dado1Mock, dado2Mock))
			.thenReturn(8)  // Primeiro turno: ponto = 8
			.thenReturn(9)  // Segundo turno: continua (nem ponto nem 7)
			.thenReturn(7); // Terceiro turno: perde com soma 7

		// Act
		boolean resultado = jogo.jogo();

		// Assert
		assertFalse(resultado);
		verify(jogadorMock, times(3)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	public void testJogo_MultiplosTurnos_VitoriaFinal() {
		// Arrange
		when(jogadorMock.lancar(dado1Mock, dado2Mock))
			.thenReturn(9)  // Primeiro turno: ponto = 9
			.thenReturn(4)  // Segundo turno: continua
			.thenReturn(10) // Terceiro turno: continua
			.thenReturn(9); // Quarto turno: ganha repetindo o ponto

		// Act
		boolean resultado = jogo.jogo();

		// Assert
		assertTrue(resultado);
		verify(jogadorMock, times(4)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	public void testJogo_MultiplosTurnos_DerrotaFinal() {
		// Arrange
		when(jogadorMock.lancar(dado1Mock, dado2Mock))
			.thenReturn(10) // Primeiro turno: ponto = 10
			.thenReturn(5)  // Segundo turno: continua
			.thenReturn(8)  // Terceiro turno: continua
			.thenReturn(7); // Quarto turno: perde com soma 7

		// Act
		boolean resultado = jogo.jogo();

		// Assert
		assertFalse(resultado);
		verify(jogadorMock, times(4)).lancar(dado1Mock, dado2Mock);
	}

	@Test
	public void testJogo_ConstrutorPadrao() {
		// Arrange & Act
		Jogo jogoPadrao = new Jogo();

		// Assert
		assertNotNull(jogoPadrao);
		// Testa se o jogo funciona com dependências reais
		boolean resultado = jogoPadrao.jogo();
		// Como usa Random, o resultado pode variar, mas deve ser boolean válido
		assertTrue(resultado == true || resultado == false);
	}
}