import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class EstabelecimentoService {
    private List<Estabelecimento> lista = new ArrayList<>();

    public void carregar(String caminho) throws IOException {
        lista.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 9) {
                    String nome = dados[0];
                    String categoria = dados[1];
                    String tipoLogradouro = dados[3];
                    String logradouro = dados[4];
                    String numero = dados[5];
                    String bairro = dados[6];
                    String cep = dados[7];
                    String telefone = dados[8];

                    String enderecoCompleto = tipoLogradouro + " " + logradouro + ", " + numero +
                                              " - " + bairro + " - CEP " + cep;
                    lista.add(new Estabelecimento(nome, enderecoCompleto, telefone, categoria));
                }
            }
        }
    }

    public List<Estabelecimento> buscar(String termo) {
        return lista.stream()
            .filter(e -> e.getNome().toLowerCase().contains(termo.toLowerCase()) || 
                         e.getCategoria().toLowerCase().contains(termo.toLowerCase()))
            .collect(Collectors.toList());
    }
}