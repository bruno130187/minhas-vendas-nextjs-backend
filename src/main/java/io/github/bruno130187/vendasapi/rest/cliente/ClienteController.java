package io.github.bruno130187.vendasapi.rest.cliente;

import io.github.bruno130187.vendasapi.model.Cliente;
import io.github.bruno130187.vendasapi.model.Produto;
import io.github.bruno130187.vendasapi.repository.ClienteRepository;
import io.github.bruno130187.vendasapi.rest.produto.ProdutoFormRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping()
    public ResponseEntity salvar(@RequestBody ClienteFormRequest clienteFormRequest) {
        Cliente cliente = clienteFormRequest.toModel();
        cliente.setDataCadastro(LocalDate.now());
        clienteRepository.save(cliente);
        return ResponseEntity.ok(ClienteFormRequest.fromModel(cliente));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ClienteFormRequest clienteFormRequest) {

        Optional<Cliente> clienteExistente =  clienteRepository.findById(id);

        if (!clienteExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Cliente cliente = clienteFormRequest.toModel();
        cliente.setId(id);
        cliente.setDataCadastro(LocalDate.now());
        clienteRepository.save(cliente);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<ClienteFormRequest> listar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "cpf", required = false, defaultValue = "") String cpf,
            Pageable pageable
    ) {
        return clienteRepository
                .buscarPorNomeCpf("%"+nome+"%", "%"+cpf+"%", pageable)
                .map(ClienteFormRequest::fromModel);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteFormRequest> porid(@PathVariable Long id) {
        Optional<Cliente> clienteoExistente = clienteRepository.findById(id);
        if (clienteoExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var cliente = clienteoExistente.map(ClienteFormRequest::fromModel).get();
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (!clienteExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        clienteRepository.delete(clienteExistente.get());
        return ResponseEntity.noContent().build();
    }

}
