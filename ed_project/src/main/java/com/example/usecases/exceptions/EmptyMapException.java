package com.example.usecases.exceptions;

/**
 * A excepção EmptyMapException é lançada quando uma operação é realizada num
 * mapa que ainda não possui vértices definidos.
 * Pertence ao conjunto de exceções relacionadas aos casos de uso no domínio da
 * aplicação.
 * 
 * Esta exceção faz extend à classe Exception e fornece uma mensagem predefinida
 * indicando a necessidade de criar o mapa antes de prosseguir com a operação.
 * 
 * @author Luís Costa [8200737]
 * @version 1.0
 * @see java.lang.Exception
 */
public class EmptyMapException extends Exception {
	 /**
     * Constrói uma nova instância da excepção EmptyMapException com a mensagem pré-definida.
     */
	public EmptyMapException() {
		super("O mapa ainda não tem posições definidas!\nPor favor crie o mapa antes de posicionar a bandeira");
	}
}
