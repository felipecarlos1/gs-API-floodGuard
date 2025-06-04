package br.fiap.services;

import br.fiap.dtos.DadosClimaticosDTO;
import br.fiap.entities.HistoricoEnchente;
import br.fiap.entities.LeituraNivel;
import br.fiap.entities.Usuario;
import br.fiap.repositories.HistoricoEnchenteRepositoryBD;
import br.fiap.repositories.LeituraNivelRepositoryBD;

import java.time.LocalDateTime;

public class LeituraNivelService {

    private OpenWeatherService openWeatherService = new OpenWeatherService();
    private LeituraNivelRepositoryBD leituraNivelRepository = new LeituraNivelRepositoryBD();
    private HistoricoEnchenteRepositoryBD historicoRepository = new HistoricoEnchenteRepositoryBD();

    public void gerarDadosMeteorologicos(Usuario usuario) {
        if (usuario == null || usuario.getNome() == null) {
            System.err.println("Usuário inválido para gerar leitura de nível.");
            return;
        }

        String cidade = usuario.getCidade(); // ou bairro, se preferir mais precisão

        DadosClimaticosDTO dados = openWeatherService.getClimaPorCidade(cidade);
        if (dados == null) {
            System.err.println("Falha ao obter dados climáticos.");
            return;
        }

        // Criar LeituraNivel
        LeituraNivel leitura = new LeituraNivel();
        leitura.setDataHora(LocalDateTime.now());
        leitura.setLocalizacao(cidade);
        leitura.setNivel(dados.getNivelRioEstimado());

        leituraNivelRepository.add(leitura);

        // Criar HistoricoEnchente se o nível estiver crítico
        if (dados.getNivelRioEstimado() >= 3.0) {
            HistoricoEnchente historico = new HistoricoEnchente();
            historico.setDataHora(LocalDateTime.now());
            historico.setDescricao("Alerta: Nível elevado detectado");
            historico.setLocalizacao(cidade);

            historicoRepository.add(historico);
        }
    }
}
