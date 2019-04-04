/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knnkfoldzscore;

import java.util.ArrayList;

/**
 *
 * @author Heitor Nunes, Marcelo Prado, Steffan Martins
 */
public class Zscore {

    private double[] medias, desvios;
    private ArrayList<Amostra> amostras;
    private int n_amostras, n_atributos;

    public Zscore(ArrayList<Amostra> amostras, int n_amostras, int n_atributos) {
        this.amostras = amostras;
        this.n_amostras = n_amostras;
        this.n_atributos = n_atributos;
        this.medias = new double[n_atributos];
        this.desvios = new double[n_atributos];
    }

    private void setMedia() {
        for (int i = 0; i < n_atributos; i++) {
            double media = 0;
            for (int j = 0; j < n_amostras; j++) {
                media += amostras.get(j).getAtributo(i);
            }
            this.medias[i] = media / n_amostras;
        }
    }

    private void setDesvioPadrao() {
        for (int i = 0; i < n_atributos; i++) {
            double desvio = 0;
            for (int j = 0; j < n_amostras; j++) {
                desvio += Math.pow((amostras.get(j).getAtributo(i) - this.medias[i]), 2);
            }
            this.desvios[i] = Math.sqrt(desvio / (n_amostras - 1)); // -1 pois é Desvio Padrão Amostral
        }
    }

    public ArrayList<Amostra> normalizar() {
        setMedia();
        setDesvioPadrao();

        for (int i = 0; i < n_amostras; i++) {
            double[] atributos = new double[n_atributos];
            for (int j = 0; j < n_atributos; j++) {
                atributos[j] = (amostras.get(i).getAtributo(j) - medias[j]) / desvios[j];
            }
            Amostra a = new Amostra(amostras.get(i).getClasse(), atributos);
            amostras.set(i, a);
        }
        return amostras;
    }

}
