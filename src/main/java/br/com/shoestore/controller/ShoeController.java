package br.com.shoestore.controller;

import br.com.shoestore.model.Shoe;
import br.com.shoestore.service.ShoeService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shoes")
public class ShoeController {

    private final ShoeService shoeService;

    public ShoeController(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("shoes", shoeService.findAll());
        return "shoes/list";
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newShoeForm(Model model) {
        model.addAttribute("shoe", new Shoe());
        return "shoes/form";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String save(@Valid @ModelAttribute Shoe shoe, BindingResult result) {
        if (result.hasErrors()) {
            return "shoes/form";
        }
        shoeService.save(shoe);
        return "redirect:/shoes";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("shoe", shoeService.findById(id));
        return "shoes/form";
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@RequestParam Long id) {
        shoeService.deleteById(id);
        return "redirect:/shoes";
    }
}