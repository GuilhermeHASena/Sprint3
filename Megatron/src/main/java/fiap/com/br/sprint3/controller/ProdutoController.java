package fiap.com.br.sprint3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiap.com.br.sprint3.model.ProdutoModel;
import fiap.com.br.sprint3.repository.ProdutoRepository;

@RestController
@RequestMapping("/v1/megatron/produto")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@PostMapping
	public ResponseEntity<ProdutoModel> cadastroProduto(@RequestBody ProdutoModel model){
		produtoRepository.save(model);
		return new ResponseEntity<ProdutoModel>(model, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<ProdutoModel> buscarProduto(@RequestBody String id){
		Optional<ProdutoModel> produto = produtoRepository.findById(id);
		return new ResponseEntity<ProdutoModel>(produto.get(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<ProdutoModel> atualizarProduto(@RequestBody ProdutoModel model){
		Optional<ProdutoModel> produtoAux = produtoRepository.findById(model.getId());
		produtoAux.get().setNome(model.getNome());
		produtoAux.get().setDescricao(model.getDescricao());
		produtoAux.get().setCor(model.getCor());
		produtoAux.get().setMarca(model.getMarca());
		produtoAux.get().setValor(model.getValor());
		
		produtoRepository.save(produtoAux.get());
		return new ResponseEntity<ProdutoModel>(produtoAux.get(), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<ProdutoModel> deletarProduto(@RequestBody String id){
		produtoRepository.deleteById(id);
		return new ResponseEntity<ProdutoModel>(HttpStatus.OK);
	}
}
