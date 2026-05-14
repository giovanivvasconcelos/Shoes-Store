package br.com.shoestore.controller.api;

import br.com.shoestore.model.Shoe;
import br.com.shoestore.service.ShoeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoes")
public class ShoeApiController {

    private final ShoeService shoeService;

    public ShoeApiController(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @GetMapping
    public ResponseEntity<List<Shoe>> findAll() {
        return ResponseEntity.ok(shoeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shoe> findById(@PathVariable Long id) {
        return ResponseEntity.ok(shoeService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Shoe> save(@Valid @RequestBody Shoe shoe) {
        return ResponseEntity.ok(shoeService.save(shoe));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Shoe> update(@PathVariable Long id, @Valid @RequestBody Shoe shoe) {
        shoe.setId(id);
        return ResponseEntity.ok(shoeService.save(shoe));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        shoeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}