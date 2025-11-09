package br.com.portofoliodomarcio.erp.moduloproduto;

// Importações necessárias para o JPA (Banco de Dados) e Lombok
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 1. Diz ao Spring: "Esta classe é uma tabela no banco de dados"
@Getter   // 2. Lombok: Cria todos os métodos get (ex: getNome(), getPrecoCusto())
@Setter   // 3. Lombok: Cria todos os métodos set (ex: setNome(...))
@NoArgsConstructor // 4. Lombok: Cria um construtor vazio (exigido pelo JPA)
public class Produto {

    @Id // 5. Diz ao Spring: "Este campo é a Chave Primária (Primary Key)"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 6. Deixa o banco gerar o ID automaticamente (auto-incremento)
    private Long id;

    // --- Campos da nossa tabela ---
    
    private String nome;
    private String codigoSku; // O "SKU" do produto
    private Double precoCusto;
    private Integer quantidadeEstoque;

    // É SÓ ISSO!
    // Você não precisa escrever os getters/setters/construtores
    // porque o Lombok (@Getter, @Setter, @NoArgsConstructor) faz isso para você.
}