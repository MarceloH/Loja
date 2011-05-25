package presentation;

import domainModel.Cliente;
import dataAccess.ClienteRepository;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Servlet implementation class clienteController
 */
public class clienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    // Declaração do repositório
	ClienteRepository repositorio;
	
	// Construtor do servlet
    public clienteController() {
        super();
        
        // Inicialização do repositório
        repositorio = new ClienteRepository();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Verifica se o parâmetro edit foi passado
		String edit = request.getParameter("edit");
		
		//Código que executa quando o parâmetro edit é passado
		if(edit != null){
			if(!edit.equalsIgnoreCase("new")){
				try {
					// Carrega o cliente do BD 
					Cliente cliente = repositorio.Open(Integer.parseInt(edit));
					
					// Passa o cliente para a página JSP 
					request.setAttribute("cliente", cliente);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// Chamar página JSP
			RequestDispatcher editar = request.getRequestDispatcher("clientesEditar.jsp");
			editar.forward(request, response);
			return;
		}
		
		// Gera uma listagem de clientes
		List<Cliente> clientes = repositorio.getTop10ByName();
		
		// Passa a listagem para a página JSP
		request.setAttribute("clientes", clientes);
		
		// Chamar a página JSP
		RequestDispatcher listagem = request.getRequestDispatcher("clientesListagem.jsp");
		listagem.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Recebe os parâmetros do formulário
			
			String cod = request.getParameter("cod");
			String nome = request.getParameter("nome");
			
			// Carrega o objeto do banco de dados
			Cliente cliente = repositorio.Open(Integer.parseInt(cod));
			
			cliente.setNome(nome);
			
			repositorio.Save(cliente);
			
			// Gera uma listagem de clientes
			List<Cliente> clientes = repositorio.getTop10ByName();
			
			// Passa a listagem para a página JSP
			request.setAttribute("clientes", clientes);
			
			// Chamar a página JSP
			RequestDispatcher listagem = request.getRequestDispatcher("clientesListagem.jsp");
			listagem.forward(request, response);
		}
		catch(Exception ex){
			
		}
	}

}
