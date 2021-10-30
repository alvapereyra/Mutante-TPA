package com.example.mutante.controller;

import com.example.mutante.service.MutantService;
import com.example.mutante.entidades.Mutant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/v1/mutant")

public class MutantController {
    private MutantService mutantService;

    public MutantController (MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping("")
    public ResponseEntity<?> adnMutante (@RequestBody Mutant adn){
        try{
            if (mutantService.corroboracion(adn)){
                System.out.println("ENTRO AL IF");
                return ResponseEntity.status(HttpStatus.OK).body("");
            }else{
                System.out.println("entro al else");
                throw new Exception();
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("error:"+e.getStackTrace());
        }
    }
    @GetMapping("/stats")
    public ResponseEntity<?> ContarMutante(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mutantService.ContarMutante());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }
}
