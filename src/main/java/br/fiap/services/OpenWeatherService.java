package br.fiap.services;

import br.fiap.dtos.DadosClimaticosDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenWeatherService {

    private static final String API_KEY = "4e11386ef5603b381c1481bd50e496e3";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public DadosClimaticosDTO getClimaPorCidade(String cidade) {
        try {
            String urlString = BASE_URL + "?q=" + cidade + "&appid=" + API_KEY + "&units=metric&lang=pt_br";
            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                resposta.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(resposta.toString());

            // Extraindo os dados principais
            JSONObject main = json.getJSONObject("main");
            JSONArray weatherArray = json.getJSONArray("weather");
            JSONObject weather = weatherArray.getJSONObject(0);

            double temperatura = main.getDouble("temp");
            double umidade = main.getDouble("humidity");
            double pressao = main.getDouble("pressure");
            String descricao = weather.getString("description");

            // Simulação: Nível do rio baseado na umidade (ajustável futuramente)
            double nivelRioEstimado = calcularNivelRio(umidade);

            return new DadosClimaticosDTO(temperatura, umidade, pressao, descricao, nivelRioEstimado);

        } catch (Exception e) {
            System.err.println("Erro ao acessar OpenWeather: " + e.getMessage());
            return null;
        }
    }

    private double calcularNivelRio(double umidade) {
        // Lógica fictícia simples: quanto maior a umidade, maior o nível estimado
        return (umidade / 100.0) * 5.0; // nível de 0.0 até 5.0 metros
    }
}
