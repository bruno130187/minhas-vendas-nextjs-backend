package io.github.bruno130187.vendasapi.rest.venda;

import io.github.bruno130187.vendasapi.repository.ItemVendaRepository;
import io.github.bruno130187.vendasapi.model.Venda;
import io.github.bruno130187.vendasapi.repository.VendaRepository;
import io.github.bruno130187.vendasapi.service.RelatorioVendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

import io.github.bruno130187.vendasapi.util.Utils;

@RestController
@RequestMapping("/api/vendas")
@CrossOrigin("*")
public class vendaController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private RelatorioVendasService relatorioVendasService;

    @PostMapping
    @Transactional
    public void realizarVenda(@RequestBody Venda venda) {
        vendaRepository.save(venda);
        venda.getItens().stream().forEach(itemVenda -> itemVenda.setVenda(venda));
        itemVendaRepository.saveAll(venda.getItens());
    }

    @GetMapping("/relatorio-vendas")
    public ResponseEntity<byte[]> relatorioVendas(
            @RequestParam(value = "id", required = false, defaultValue = "0") Long id,
            @RequestParam(value = "inicio", required = false, defaultValue = "") String inicio,
            @RequestParam(value = "fim", required = false, defaultValue = "") String fim
    ) {
        Timestamp dataInicio = Utils.convertStringToDate(inicio, false);
        Timestamp dataFim = Utils.convertStringToDate(fim, true);
        byte[] relatorioGerado = relatorioVendasService.gerarRelatorioVendas(id, dataInicio, dataFim);
        HttpHeaders httpHeaders = new HttpHeaders();
        String fileName = "relatorio-vandas.pdf";
        httpHeaders.setContentDispositionFormData("inline; filename=\"" +fileName+ "\"", fileName);
        httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(relatorioGerado, httpHeaders, HttpStatus.OK);
    }
}
