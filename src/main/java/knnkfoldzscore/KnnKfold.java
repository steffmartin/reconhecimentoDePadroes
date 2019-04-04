/*
 * To change this license header, choose License Headers in ProparticaoTreinamentoect Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knnkfoldzscore;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author Heitor Nunes, Marcelo Prado, Steffan Martins
 */
public class KnnKfold {

    private ArrayList<ArrayList<Amostra>> particoes = new ArrayList<>();
    private TreeMap<Double, ArrayList<Amostra>> distancias;
    private int n_amostras, n_vizinhos, n_particoes;
    DecimalFormat percentual = new DecimalFormat("0.00 %");

    public KnnKfold(ArrayList<Amostra> amostras, int n_amostras, int n_vizinhos, int n_particoes) {
        this.n_amostras = n_amostras;
        this.n_vizinhos = n_vizinhos;
        this.n_particoes = n_particoes;

        //Particionar (K-FOLD) direto no construtor para não ter que armazenar o ArrayList original
        for (int i = 0; i < n_particoes; i++) {
            this.particoes.add(new ArrayList<>());
        }
        for (int i = 0; i < n_amostras; i++) {
            particoes.get(i % n_particoes).add(amostras.get(i));
        }

    }

    public void classificar() {
        double erro = 0;

        for (int particaoTeste = 0; particaoTeste < n_particoes; particaoTeste++) {                             //Pega uma partição como teste por vez            
            //Parte 1: calcular as distâncias da amostra de teste com as de treinamento
            for (Amostra a_teste : particoes.get(particaoTeste)) {                                              //E Para cada amostra de teste dessa partição
                distancias = new TreeMap<>();
                for (int particaoTreinamento = 0; particaoTreinamento < n_particoes; particaoTreinamento++) {   //Em cada partição
                    if (particaoTreinamento != particaoTeste) {                                                 //Que não for a partição de teste
                        for (Amostra a_treino : particoes.get(particaoTreinamento)) {                           //E Para cada amostra de treino
                            double dist = distEuclidiana(a_teste, a_treino);                                    //Calcular a distância entre a amostra de teste
                            if (distancias.containsKey(dist)) {                                                 //E armazenar em uma árvore ordenada
                                distancias.get(dist).add(a_treino);
                            } else {
                                ArrayList<Amostra> aux = new ArrayList<>();
                                aux.add(a_treino);
                                distancias.put(dist, aux);
                            }
                        }
                    }
                }
                //Aqui já temos as distâncias de a_teste calculada para todas a_treinamento de todas as partições

                //Parte 2: pegar os k vizinhos mais próximos e contar a classe destes
                int resultado = 0;
                int ajuste = 0;
                boolean ok = false;

                do {
                    int cont = 0;
                    int maior = 0;
                    HashMap<Integer, Integer> n_vezes = new HashMap<>();                //Armazenar a classe e quantas vezes ela aparece entre os vizinhos
                    ArrayList<Double> menores = new ArrayList<>(distancias.keySet());   //Pega as distâncias calculadas, ordenadas de forma crescente

                    while (cont < n_vizinhos - ajuste) {                                //Até a quantidade de vizinhos informada (menos o ajuste, se já houve empate)
                        double dist = menores.remove(0);                                //Pega a menor distância
                        for (Amostra v : distancias.get(dist)) {                        //Pega a(s) amostra(s) com aquela distância
                            int v_classe = v.getClasse();
                            //System.out.println("Vizinho " + v.toString() + " Distância " + dist);//Impressão para conferência
                            if (n_vezes.containsKey(v_classe)) {                        //E adiciona a classe desse vizinho para fins de contagem
                                n_vezes.replace(v_classe, n_vezes.get(v_classe) + 1);
                            } else {
                                n_vezes.put(v_classe, 1);
                            }
                            if (n_vezes.get(v_classe) > maior) {                        //Vai atualizando a maior quantidade de vezes que uma mesma classe repete
                                maior = n_vezes.get(v_classe);
                            }
                            if (++cont == n_vizinhos - ajuste) {
                                break;
                            }
                        }
                    }
                    for (int r : n_vezes.keySet()) {
                        if (n_vezes.get(r) == maior) {                                  //Confere cada classe e só trata a que repetiu mais vezes
                            resultado = r;
                            if (!ok) {
                                ok = true;
                            } else {
                                ok = false;                                             //Se achou uma segunda classe com o máximo de vezes que repetiu, para e reinicia o processo com um vizinho a menos
                                ajuste++;
                                break;
                            }
                        }
                    }
                } while (!ok);
                //Ao sair do laço temos o resultado!

                //Parte 3: conferir resultado com a classe da amostra e atualizar a taxa de acerto
                /*
                System.out.print("Partição " + (particaoTeste + 1) + ", Amostra " + a_teste.toString() + ", Resultado: " + resultado + " (");
                if (a_teste.getClasse() == resultado) {
                    System.out.println("CERTO)");
                } else {
                    System.out.println("ERROR)");
                    erro++;
                }
                */
            }
            //Fim de uma amostra, o laço pega a próxima amostra da partição e repete
        }
        //Fim de uma partição, o laço pega a próxima partição e repete

        //Parte final: imprimir taxas de erro totais
        System.out.println("Taxa de erro total: " + percentual.format(erro / n_amostras));
    }

    public double distEuclidiana(Amostra x, Amostra y) {
        double dist = 0;
        int cont = 0;
        for (double atributo : x.getAtributos()) {
            dist += Math.pow((atributo - y.getAtributo(cont++)), 2);
        }
        return Math.sqrt(dist);
    }
}
