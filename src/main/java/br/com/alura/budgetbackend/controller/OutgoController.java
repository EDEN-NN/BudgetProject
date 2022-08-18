package br.com.alura.budgetbackend.controller;

import br.com.alura.budgetbackend.model.Income;
import br.com.alura.budgetbackend.model.Outgo;
import br.com.alura.budgetbackend.service.OutgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OutgoController {

    @Autowired
    private OutgoService outgoService;

    @PostMapping("/despesas")
    public ResponseEntity<Outgo> saveOutgo(@Valid @RequestBody Outgo outgo) {
               outgo = outgoService.saveOutgo(outgo);
               URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(outgo.getId()).toUri();
               return ResponseEntity.created(uri).build();
    }

    @GetMapping("/despesas")
    public ResponseEntity<List<Outgo>> getAllOutgo(@RequestParam(required = false) String descricao) {
        List<Outgo> outgoing = descricao == null ? outgoService.findAll() : outgoService.findByDescription(descricao);
        return ResponseEntity.ok().body(outgoing);
    }

    @GetMapping("/despesas/{id}")
    public ResponseEntity<Outgo> getById(@Valid @PathVariable Long id) {
       Outgo outgo = outgoService.findById(id);
       return ResponseEntity.ok().body(outgo);
    }

    @PutMapping("/despesas/{id}")
    public ResponseEntity<Outgo> updateOutgo(@Valid @PathVariable Long id, @Valid @RequestBody Outgo outgo) {
        outgo = outgoService.updateOutgo(id, outgo);
        return ResponseEntity.ok().body(outgo);
    }

    @DeleteMapping("/despesas/{id}")
    public ResponseEntity<Void> deleteOutgo(@Valid @PathVariable Long id) {
        outgoService.deleteOutgo(id);
        return ResponseEntity.accepted().build();
    }

}
