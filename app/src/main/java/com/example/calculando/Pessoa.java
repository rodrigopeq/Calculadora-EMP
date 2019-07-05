package com.example.calculando;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static java.lang.Math.abs;

/**
 *
 * @author rodri
 */
public abstract class Pessoa {
    
    private boolean sexualidade = true; //TRUE - HOMEM # FALSE - MULHER
    private double idade;
    private double altura;
    private double peso;
    private double alturaPai;
    private double alturaMae;
    private final double[] coeficientes;
    
    public Pessoa(double[] coeficientes){
        this.coeficientes = coeficientes;
    }

    public Pessoa(boolean sexualidade, int idade, double altura, double peso, double alturaPai, double alturaMae,double[] coeficientes){
        this.sexualidade = sexualidade;
        this.idade = idade;
        this.altura = altura;
        this.alturaPai = alturaPai;
        this.alturaMae = alturaMae;
        this.coeficientes = coeficientes;
    }
// get e set
    public void setSexualidade(String sexualidade){
        this.sexualidade = Boolean.parseBoolean(sexualidade);
    }

    public void setSexualidade(boolean sexualidade){
        this.sexualidade = sexualidade;
    }

    public boolean getSexualidade(){
        return sexualidade;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = abs(Double.parseDouble(idade));
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = abs(Double.parseDouble(String.valueOf(altura)));
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = abs(Double.parseDouble(String.valueOf(peso)));
    }

    public double getAlturaPai() {
        return alturaPai;
    }

    public void setAlturaPai(String alturaPai) {
        this.alturaPai = abs(Double.parseDouble(String.valueOf(alturaPai)));
    }

    public double getAlturaMae() {
        return alturaMae;
    }

    public void setAlturaMae(String alturaMae) {
        this.alturaMae = abs(Double.parseDouble(String.valueOf(alturaMae)));
    }
    
    //calculos
    
    public double transformaEmPolegadas(double alturaEmCm){
        return alturaEmCm/2.54;
    }

    public double transformaEmCm(double alturaEmIn){
        return alturaEmIn*2.54;
    }
    
    public double transformaEmLibras(double peso){
        return peso*2.205;
    }
    
    public double getMediaAlturaPais(){
        return (alturaPai+alturaMae)/2;
    }
    
    public double formulaEMP(double beta0,double EST, double PESO,double MEP){
        return beta0+(transformaEmPolegadas(altura)*EST)+(transformaEmLibras(peso)*PESO)+(transformaEmPolegadas(getMediaAlturaPais())*MEP);
    }
    
    public double[] age(double[] coeficiente){
        double[] auxiliar = new double[28];
        int j=0;
        for(int i = 0; i<140 ; i=i+5){
            auxiliar[j] = coeficiente[i];
            j++;
        }
        return auxiliar;
    }
    
    public int getIndex(double idade){
        double[] idades = age(coeficientes);
        int i=0;
        while(idades[i]<=idade){
            i++;
        }
        return (i-1)*5;
            
    }
    
    public double calcular(double idade) {
        double index1 = getValorCoeficiente(getIndex(idade)+1);
        double index2 = getValorCoeficiente(getIndex(idade)+2);
        double index3 = getValorCoeficiente(getIndex(idade)+3);
        double index4 = getValorCoeficiente(getIndex(idade)+4);

        return formulaEMP(index1,index2,index3,index4);
    }
    
    public abstract double getValorCoeficiente(int index);
   
    
}
