package com.example.mutante;

import com.example.mutante.service.MutantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IsMutantTest {

    MutantService mutService;
    boolean resultado;

    @BeforeEach
    public void mut(){
        mutService = new MutantService();
    }

    @Test
    public void mutantTestVertical() {

        String[] dna = {"TTGCGA","TAGTGC","TTATGT","TGAAGG","CGCCTA","TCACTG"};
        resultado = mutService.isMutant(dna);
        Assertions.assertTrue(resultado);
        System.out.println("El resultado del test es: "+ resultado);
    }

    @Test
    public void mutantTestHorizontal() {

        String[] dna = {"TTGCAA","CAGTGC","TTATGT","AAAAGG","CCCCTA","TCACTG"};
        resultado = mutService.isMutant(dna);
        Assertions.assertTrue(resultado);
        System.out.println("El resultado del test es: "+ resultado);
    }

    @Test
    public void mutantTestDiagonal(){

        String[] dna = {"ATGCGA","CATTCC","TTATGA","AGAATG","CTCATA","TCCCTG"};
        resultado = mutService.isMutant(dna);
        Assertions.assertTrue(resultado);
        System.out.println("El resultado del test es: "+ resultado);
    }

    @Test
    public void mutantTestContradiagonal(){

        String[] dna = {"CTGCGA","CAGTCC","TTATCA","AGACAG","CTCATA","TCACTG"};
        resultado = mutService.isMutant(dna);
        Assertions.assertTrue(resultado);
        System.out.println("El resultado del test es: "+ resultado);
    }

    @Test
    public void mutantTestVacío(){

        String[] dna = {"CTGCGA","CAGTCC","TTATGG","AGACAG","CTCATA","TCACTG"};
        resultado = mutService.isMutant(dna);
        Assertions.assertFalse(resultado);
        System.out.println("El resultado del test es: "+ resultado);
    }
}
