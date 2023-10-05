package io.github.bruno130187.vendasapi.rest.dashboard;

import io.github.bruno130187.vendasapi.repository.ClienteRepository;
import io.github.bruno130187.vendasapi.repository.ProdutoRepository;
import io.github.bruno130187.vendasapi.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private VendaRepository vendasRepository;
    @Autowired
    private ClienteRepository clientesRepository;
    @Autowired
    private ProdutoRepository produtosRepository;

    @GetMapping
    public DashboardData getDashBoard() {
        long vendasCount = vendasRepository.count();
        long clientesCount = clientesRepository.count();
        long produtosCount = produtosRepository.count();

        var anoCorrente = LocalDate.now().getYear();
        var vendasPorMes = vendasRepository.obterSomatoriaVendasPorMes(anoCorrente);

        return new DashboardData(produtosCount, clientesCount, vendasCount, vendasPorMes);
    }

}
