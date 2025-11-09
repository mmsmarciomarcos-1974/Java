package br.com.portofoliodomarcio.erp.moduloproduto;

// Importações de tudo que vamos usar
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // 1. Diz ao Spring: "Esta classe é um Controller de API REST"
@RequestMapping("/api/produtos") // 2. O "TRANID": Todos os métodos aqui serão acessados pela URL base "/api/produtos"
public class ProdutoController {

    // 3. Injeção de Dependência:
    // Pede ao Spring: "Me dê uma instância de ProdutoRepository pronta para usar"
    @Autowired 
    private ProdutoRepository repository;

    // --- Endpoint para CRIAR um novo produto ---
    // Responde a requisições POST para /api/produtos
    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        // @RequestBody: Pega o JSON do corpo da requisição e transforma em um objeto Produto
        return repository.save(produto); // Salva o produto no banco e retorna o produto salvo (com o ID)
    }

    // --- Endpoint para LISTAR TODOS os produtos ---
    // Responde a requisições GET para /api/produtos
    @GetMapping
    public List<Produto> listarTodosProdutos() {
        return repository.findAll(); // Busca todos os registros no banco e retorna como uma lista JSON
    }

    // --- Endpoint para BUSCAR UM produto específico pelo ID ---
    // Responde a requisições GET para /api/produtos/1 (ou /2, /3, etc.)
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        // @PathVariable: Pega o 'id' que veio na URL (ex: /1) e coloca na variável 'id'
        
        return repository.findById(id) // Tenta buscar o produto pelo ID
                .map(produtoEncontrado -> ResponseEntity.ok(produtoEncontrado)) // 4. Se achar, retorna 200 OK com o produto
                .orElse(ResponseEntity.notFound().build()); // 5. Se não achar, retorna um 404 Not Found (Erro)
    }

    // (Poderíamos adicionar @PutMapping para Atualizar e @DeleteMapping para Deletar, mas estes 3 são um ótimo começo)
}