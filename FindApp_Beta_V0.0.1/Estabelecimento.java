public class Estabelecimento {
    private String nome;
    private String endereco;
    private String telefone;
    private String categoria;

    public Estabelecimento(String nome, String endereco, String telefone, String categoria) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.categoria = categoria;
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getTelefone() { return telefone; }
    public String getCategoria() { return categoria; }
}