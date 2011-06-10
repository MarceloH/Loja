package presentation;


import domainModel.Produto;
import dataAccess.ProdutoRepository;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jmx.mbeanserver.Repository;

import java.util.List;

//@WebServlet("/Produtos")
public class produtoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ProdutoRepository repository;
    
    public produtoController() {
        super();
        repository = new ProdutoRepository();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String edit = request.getParameter("edit");
		
		if(edit != null){
			if(!edit.equalsIgnoreCase("new")){
				try{
					Produto produto = repository.Open(Integer.parseInt(edit));
					request.setAttribute("produto", produto);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			RequestDispatcher editar = request.getRequestDispatcher("produtoEditar.jsp");
			editar.forward(request, response);
			return;
		}
        String del = request.getParameter("del");
		
		if(del != null){
			
				try{
					Produto produto = repository.Open(Integer.parseInt(del));
					repository.Delete(produto);
				}catch(Exception ex){
					ex.printStackTrace();
				}
		}
		// Gera uma listagem de clientes
		List<Produto> produtos = repository.getTop10ByName();
		
		// Passa a listagem para a página JSP
		request.setAttribute("produtos", produtos);
		
		// Chamar a página JSP
		RequestDispatcher listagem = request.getRequestDispatcher("produtosListagem.jsp");
		listagem.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
			
			// Recebe os parametros do formulario
			String cod = request.getParameter("cod");
			String nome = request.getParameter("nome");
			String preco = request.getParameter("preco");
			
			Produto produto;
			
			// Carrega o objeto do banco de dados
			if(cod != null && cod.length() != 0)
				produto = repository.Open(Integer.parseInt(cod));
			else
				produto = new Produto();
			
			produto.setNome(nome);
			produto.setPrecoProduto(Float.parseFloat(preco));
			
			repository.Save(produto);

			// Gera uma listagem de clientes
			List<Produto> produtos = repository.getTop10ByName();

			// Passa a listagem para a pagina JSP
			request.setAttribute("produtos", produtos);

			// Chamar a pagina JSP
			RequestDispatcher listagem = request.getRequestDispatcher("produtosListagem.jsp");
			listagem.forward(request, response);
		}
		catch(Exception ex){
			RequestDispatcher listagem = request.getRequestDispatcher("produtosListagem.jsp");
			listagem.forward(request, response);
		}
	}

}
