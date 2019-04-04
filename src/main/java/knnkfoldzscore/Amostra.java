/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knnkfoldzscore;

import java.text.DecimalFormat;

/**
 *
 * @author Heitor Nunes, Marcelo Prado, Steffan Martins
 */
public class Amostra {

    private final double[] atributos;
    private final int classe;

    public Amostra(int classe, double[] atributos) {
        this.atributos = atributos;
        this.classe = classe;
    }

    public int getClasse() {
        return classe;
    }

    public double[] getAtributos() {
        return atributos;
    }

    public double getAtributo(int num) {
        return atributos[num];
    }

    @Override
    public String toString() {
        DecimalFormat decimal = new DecimalFormat("0.00 ");
        String atts = "";
        for (double a : atributos) {
            if (a >= 0) {
                atts += " ";
            }
            atts += decimal.format(a);
        }
        return atts + classe;
    }

}
