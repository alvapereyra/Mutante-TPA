package com.example.mutante.service;

import com.example.mutante.entidades.Mutant;
import com.example.mutante.repositories.MutantRepositories;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MutantService {

    MutantRepositories repositorio;

    public MutantService() {
    }
    public boolean corroboracion (Mutant mutantes){
        String[] dna = new String[mutantes.getDna().size()];
        dna = mutantes.getDna().toArray(dna);
        boolean resultadoMutante = this.isMutant(dna);
        mutantes.setMutant(resultadoMutante);
        mutantes.setDnaPersistido(dna.toString());
        repositorio.save(mutantes);
        return resultadoMutante;
    }

    public Object ContarMutante(){
        List<Mutant> mutantes = repositorio.findAll();
        double contar_mutante = 0;
        double contar_humano = mutantes.size();
        double ratio = 0;
        for (Mutant mutant: mutantes) {
            if (mutant.isMutant()){
                contar_mutante++;
            }
        }
        ratio = contar_mutante/contar_humano;
        JSONObject json = new JSONObject();
        json.put("contar_mutante_dna", contar_mutante);
        json.put("contar_humano_dna", contar_humano);
        json.put("ratio", ratio);
        return json;
    }

    public boolean isMutant(String[] dna) {
        int dimension = dna.length;
        int contador = 0;
        String letra;
        String[][] matriz = new String[dimension][dimension];
        rellenarMatriz(dna, matriz);
        long startTime = System.currentTimeMillis();
        long endTime;


        //recorremos verticalmente
        for (int i = 0; i < dimension - 3; i++) {
            for (int j = 0; j < dimension; j++) {
                letra=matriz[i][j];
                if (matriz[i][j].equals(letra) && matriz[i+1][j].equals(letra) && matriz[i+2][j].equals(letra) && matriz[i+3][j].equals(letra)) {
                    contador++;
                    System.out.println("vertical");
                }
                if (contador >= 2) {
                    endTime = System.currentTimeMillis();
                    System.out.println("Tiempo:" + (endTime-startTime) + " milisegundos");
                    return true;
                }
            }
        }

        //recorremos horizontalmente
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension - 3; j++) {
                letra=matriz[i][j];
                if (matriz[i][j].equals(letra) && matriz[i][j+1].equals(letra) && matriz[i][j+2].equals(letra) && matriz[i][j+3].equals(letra)) {
                    contador++;
                    System.out.println("horizontal");
                }
                if (contador >= 2) {
                    endTime = System.currentTimeMillis();
                    System.out.println("Tiempo:" + (endTime-startTime) + " milisegundos");
                    return true;
                }
            }
        }

        //recorremos diagonalmente
        for (int i = 0; i < dimension - 3; i++) {
            for (int j = 0; j < dimension - 3; j++) {
                letra=matriz[i][j];
                if (matriz[i][j].equals(letra) && matriz[i+1][j+1].equals(letra) && matriz[i+2][j+2].equals(letra) && matriz[i+3][j+3].equals(letra)) {
                    contador++;
                    System.out.println("diagonal");
                }
                if (contador >= 2) {
                    endTime = System.currentTimeMillis();
                    System.out.println("Tiempo:" + (endTime-startTime) + " milisegundos");
                    return true;
                }
            }
        }

        //recorremos contradiagonalmente
        for (int i = 0; i < dimension - 3 ; i++) {
            for (int j = 3; j < dimension; j++) {
                letra=matriz[i][j];
                if (matriz[i][j].equals(letra) && matriz[i+1][j-1].equals(letra) && matriz[i+2][j-2].equals(letra) && matriz[i+3][j-3].equals(letra)) {
                    contador++;
                    System.out.println("contradiagonal");
                }
                if (contador >= 2) {
                    endTime = System.currentTimeMillis();
                    System.out.println("Tiempo:" + (endTime-startTime) + " milisegundos");
                    return true;
                }
            }
        }
        System.out.println(contador);
        endTime = System.currentTimeMillis();
        System.out.println("Tiempo:" + (endTime-startTime) + " milisegundos");
        return false;
    }
    private void rellenarMatriz(String[] dna, String[][] matriz) {
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna.length; j++) {
                matriz[i][j] = Character.toString(dna[i].charAt(j));
            }
        }
        //MOSTRAR MATRIZ
        /*for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna.length; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.println();
        }*/
    }
}
