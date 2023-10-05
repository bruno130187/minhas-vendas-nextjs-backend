package io.github.bruno130187.vendasapi.service;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class RelatorioVendasService {

    @Value("classpath:reports/relatorio-vendas.jrxml")
    private Resource relatorioVendasSource;

    @Value("classpath:reports/relatorio-vendas.jasper")
    private Resource relatorioVendasCompilado;

    @Autowired
    private DataSource dataSource;

    public byte[] gerarRelatorioVendasCompilando() {
        try(
                Connection connection = dataSource.getConnection();
        ) {
            JasperReport jasperReport = JasperCompileManager.compileReport(relatorioVendasSource.getInputStream());
            Map<String, Object> parametros = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, connection);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] gerarRelatorioVendasCompilado() {
        try(
                Connection connection = dataSource.getConnection();
        ) {
            Map<String, Object> parametros = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioVendasCompilado.getInputStream(), parametros, connection);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] gerarRelatorioVendas(Long idCliente, Timestamp dataInicio, Timestamp dataFim) {
        try(
                Connection connection = dataSource.getConnection();
        ) {
            Integer idConvertido = idCliente.intValue();
            Map<String, Object> parametros = new HashMap<>();
            if (idConvertido.equals(0)) {
                idConvertido = null;
            }
            parametros.put("ID_CLIENTE", idConvertido);
            parametros.put("DATA_INICIO", dataInicio);
            parametros.put("DATA_FIM", dataFim);
            return JasperRunManager.runReportToPdf(relatorioVendasCompilado.getInputStream(), parametros, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
