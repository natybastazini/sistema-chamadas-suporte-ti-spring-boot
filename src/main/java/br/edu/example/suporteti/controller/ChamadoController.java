package br.edu.example.suporteti.controller;

import br.edu.example.suporteti.model.Chamado;
import br.edu.example.suporteti.model.Prioridade;
import br.edu.example.suporteti.repository.ChamadoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chamados")
public class ChamadoController {

    private final ChamadoRepository repository;

    public ChamadoController(ChamadoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listarTodos(Model model){

        model.addAttribute("chamados", repository.listarTodos());

        return "chamados/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {

        model.addAttribute("chamado", new Chamado());
        model.addAttribute("prioridade", Prioridade.values());

        return "chamados/formulario";
    }

    @PostMapping
    public String criarChamado(@Valid @ModelAttribute("chamado") Chamado chamado, BindingResult result, Model model) {

        if (result.hasErrors()){
            model.addAttribute("prioridade", Prioridade.values());
            return "chamados/formulario";
        }

        repository.salvar(chamado);

        return "redirect:/chamados";
    }

    @GetMapping("/{id}")
    public String buscarPorId(@PathVariable Long id, Model model) {

        model.addAttribute("chamado", repository.buscarPorId(id).orElseThrow());

        return "chamado/detalhes";
    }

    @PostMapping("/{id}/iniciar")
    public String iniciar(@PathVariable Long id) {

        Chamado chamado = repository.buscarPorId(id).orElseThrow();
        chamado.iniciarAtendimento();

        return "redirect:/chamados";
    }

    @PostMapping("/{id}/resolver")
    public String resolver(@PathVariable Long id) {

        Chamado chamado = repository.buscarPorId(id).orElseThrow();
        chamado.resolver();

        return "redirect:/chamados";
    }

    @PostMapping("/{id}/cancelar")
    public String cancelar(@PathVariable Long id) {

        Chamado chamado = repository.buscarPorId(id).orElseThrow();
        chamado.cancelar();

        return "redirect:/chamados";
    }


}
