package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Medico;
import com.example.demo.Repository.MedicoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medici")
public class MedicoController {
   
    private final MedicoRepository medicoRepository;
	
	public MedicoController (MedicoRepository medicoRepository) {
		this.medicoRepository = medicoRepository;
	}

    @GetMapping("/error")
    public String errore(){
        return "Funziono ma maleh!";
    }
	
	@GetMapping("/all")
	public ResponseEntity<List<Medico>> getAllMedici (){
        List<Medico> medici = (List<Medico>) medicoRepository.findAll();
        //Ritorno sia la lista di tutti i medici ma anche uno stato OK
        return new ResponseEntity<>(medici, HttpStatus.OK);
    }
	
	@PostMapping("/add")
	public ResponseEntity<Medico> addMedico (@RequestBody Medico newMedico){
        medicoRepository.save(newMedico);
        //Ritorno sia il medico aggiunto ma anche uno stato OK
        return new ResponseEntity<>( newMedico, HttpStatus.CREATED);
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Medico> deleteMedico (@PathVariable("id") Long id){
        medicoRepository.deleteById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
	
	
}
