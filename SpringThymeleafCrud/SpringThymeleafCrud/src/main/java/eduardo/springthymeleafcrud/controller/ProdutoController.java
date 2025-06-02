package eduardo.springthymeleafcrud.controller;

import eduardo.springthymeleafcrud.model.Produto;
import eduardo.springthymeleafcrud.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/form")
    public String novoForm(Model model){
        model.addAttribute("produto", new Produto());
        return "form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto){
        produtoRepository.save(produto);
        return "redirect:/produto/listar";
    }

    @GetMapping("/listar")
    public String listar(Model model){
        model.addAttribute("produtos", produtoRepository.findAll());
        return "lista";
    }

    //Método para editar um produto
    @GetMapping("/editar/{id}")
    public String formEditar(@PathVariable int id, Model model) {
        //Cria um novo objeto do tipo produto com as informações do banco e do id passado
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto Inválido: " + id));
        //Adiciona o objeto produto criado ao modelo (pagina html)
        model.addAttribute("produto", produto);
        return "form";
    }

    //Método para deletar um produto
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable int id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto Inválido: " + id));
        produtoRepository.delete(produto);
        return "redirect:/produto/listar";
    }

}
