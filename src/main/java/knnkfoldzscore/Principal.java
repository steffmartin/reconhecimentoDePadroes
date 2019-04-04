/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knnkfoldzscore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Heitor Nunes, Marcelo Prado, Steffan Martins
 */
public class Principal {

	private static final Scanner lerTeclado = new Scanner(System.in);

	public static void main(String[] args) {

		int n_vizinhos, n_particoes, n_amostras, n_atributos; // Contadores
		ArrayList<Amostra> amostras; // Lista com as amostras

		do {
			try {
				// Leitura do arquivo
				System.out.print("Nome do arquivo com amostras: ");
				Scanner lerArquivo = new Scanner(new FileInputStream(lerTeclado.nextLine())).useDelimiter("(\r\n)|( )");
				n_amostras = Integer.parseInt(lerArquivo.next());
				n_atributos = Integer.parseInt(lerArquivo.next());
				amostras = new ArrayList<>();

				for (int i = 0; i < n_amostras; i++) {
					double atributos[] = new double[n_atributos];
					for (int j = 0; j < n_atributos; j++) {
						atributos[j] = Double.parseDouble(lerArquivo.next());
					}
					int classe = Integer.parseInt(lerArquivo.next());
					Amostra a = new Amostra(classe, atributos);
					amostras.add(a);
				}
				lerArquivo.close();
				// Arquivo lido

				System.out.print("Número de partições (K-FOLD): ");
				n_particoes = lerInteiroEntre(2, n_amostras); // Máximo de partições = n_amostras
				System.out.print("Número de vizinhos (K-NN): ");
				n_vizinhos = lerInteiroEntre(1, n_amostras - (n_amostras / n_particoes) - (n_amostras % n_particoes)); // Máximo
																														// de
																														// vizinhos
																														// =
																														// n_amostras
																														// nas
																														// partições
																														// de
																														// treinamento

				// Normaliza as amostras com Z-SCORE
				System.out.print("Normalizar dados (Z-SCORE): ");
				if (lerBoolean()) {
					amostras = new Zscore(amostras, n_amostras, n_atributos).normalizar();
				}
				// Normalizado

				// Embaralhar
				System.out.print("Aleatorizar amostras: ");
				if (lerBoolean()) {
					Collections.shuffle(amostras);
				}
				// Embaralhado. Mesmo que o usuário não as misture, elas serão misturadas na
				// classificação

				// Classificar e exibir as taxas
				new KnnKfold(amostras, n_amostras, n_vizinhos, n_particoes).classificar();
			} catch (FileNotFoundException ex) {
				System.out.println("O arquivo informado não foi localizado.");
			} catch (NumberFormatException ex) {
				System.out.println("O arquivo informado não está com o padrão de formatação esperado.");
			}
			System.out.print("Fazer outro teste: ");
		} while (lerBoolean());
		lerTeclado.close();
		System.out.println("Programa encerrado.");

	}

	public static int lerInteiroEntre(int x, int y) {
		int opcao;

		try {
			opcao = lerTeclado.nextInt();
			lerTeclado.nextLine();
		} catch (RuntimeException e) {
			System.out.print("Digite um número entre " + x + " e " + y + ": ");
			lerTeclado.nextLine();
			opcao = lerInteiroEntre(x, y);
		}
		if (opcao < x || opcao > y) {
			System.out.print("Digite um número entre " + x + " e " + y + ": ");
			opcao = lerInteiroEntre(x, y);
		}
		return opcao;
	}

	public static boolean lerBoolean() {
		boolean opcao;

		try {
			opcao = lerTeclado.nextBoolean();
			lerTeclado.nextLine();
		} catch (RuntimeException e) {
			System.out.print("Digite 'true' ou 'false': ");
			lerTeclado.nextLine();
			opcao = lerBoolean();
		}
		return opcao;
	}

}
