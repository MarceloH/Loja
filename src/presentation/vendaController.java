package presentation;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.ClienteRepository;
import dataAccess.ProdutoRepository;
import domainModel.Cliente;
import domainModel.Produto;

/**
 * Servlet implementation class vendaController
 */
//@WebServlet("/Venda")
public class vendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public vendaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		ClienteRepository rep_cliente = new ClienteRepository();
		
	
		
		try {
			
			
			
			// Carrega o cliente do BD 
			Cliente cliente  = rep_cliente.Open(1);

			// Passa o cliente para a pagina JSP 
			request.setAttribute("cliente", cliente);
			
			// Gera uma listagem de clientes
			List<Cliente> clientes = rep_cliente.getTop10ByName();
			
			// Passa a listagem para a página JSP
			request.setAttribute("clientes", clientes);
			
			RequestDispatcher listagem = request.getRequestDispatcher("efetuarVenda.jsp");
			listagem.forward(request, response);
			return;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			
			//RequestDispatcher listagem = request.getRequestDispatcher("efetuarVenda.jsp");
			//listagem.forward(request, response);
			
			//System.out.print(cli);
		

	}

}
