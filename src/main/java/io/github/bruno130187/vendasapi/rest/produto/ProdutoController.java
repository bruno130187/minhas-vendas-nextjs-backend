package io.github.bruno130187.vendasapi.rest.produto;

import io.github.bruno130187.vendasapi.model.Produto;
import io.github.bruno130187.vendasapi.repository.ProdutoRepository;
import io.github.bruno130187.vendasapi.rest.cliente.ClienteFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ProdutoFormRequest salvar(@RequestBody ProdutoFormRequest produtoFormRequest) {

        Produto produto = produtoFormRequest.toModel();
        produtoRepository.save(produto);
        return ProdutoFormRequest.fromModel(produto);

    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ProdutoFormRequest produtoFormRequest) {

        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (!produtoExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoFormRequest.toModel();
        produto.setId(id);
        produto.setDataCadastro(LocalDate.now());
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public Page<ProdutoFormRequest> listar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "descricao", required = false, defaultValue = "") String descricao,
            Pageable pageable
    ) {
        return produtoRepository
                .buscarPorNomeDescricao("%"+nome+"%", "%"+descricao+"%", pageable)
                .map(ProdutoFormRequest::fromModel);
    }

    @GetMapping(value = "/autocomplete")
    public List<ProdutoFormRequest> listarAutoComplete() {
        return produtoRepository.findAll().stream()
                .map(ProdutoFormRequest::fromModel)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoFormRequest> porid(@PathVariable Long id) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var produto = produtoExistente.map(ProdutoFormRequest::fromModel).get();
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (!produtoExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        produtoRepository.delete(produtoExistente.get());

        return ResponseEntity.noContent().build();
    }

}
