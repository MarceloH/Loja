package presentation;

import dataAccess.ClienteRepository;

import domainModel.Cliente;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jmx.mbeanserver.Repository;

import java.util.List;

/**
 * Servlet implementation class clienteController
 */
//@WebServlet("/Clientes")
public class clienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    // Declaracao do repositorio
	ClienteRepository repositorio;

	// Construtor do servlet
    public clienteController() {
        super();
        
        // Inicializacao do repositorio
        repositorio = new ClienteRepository();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Verifica se o parametro edit foi passado
		String edit = request.getParameter("edit");

		
		//Codigo que executa quando o parametro edit passado
		if(edit != null){
			if(!edit.equalsIgnoreCase("new")){
				try {
					// Carrega o cliente do BD 
					Cliente cliente = repositorio.Open(Integer.parseInt(edit));

					// Passa o cliente para a pagina JSP 
					request.setAttribute("cliente", cliente);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// Chamar p�gina JSP
			RequestDispatcher editar = request.getRequestDispatcher("clientesEditar.jsp");
			editar.forward(request, response);
			return;
		}
		//Verifica se o par�metro del foi passado
		String del = request.getParameter("del");
		if(del != null){
				try {
					// Carrega o cliente do BD 
					Cliente cliente = repositorio.Open(Integer.parseInt(del));
					
					// Apaga cliente carregado da base 
					repositorio.Delete(cliente);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		// Gera uma listagem de clientes
		List<Cliente> clientes = repositorio.getTop10ByName();
		
		// Passa a listagem para a p�gina JSP
		request.setAttribute("clientes", clientes);
		
		// Chamar a p�gina JSP
		RequestDispatcher listagem = request.getRequestDispatcher("clientesListagem.jsp");
		listagem.forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			// Recebe os parametros do formulario
			String cod = request.getParameter("cod");
			String nome = request.getParameter("nome");
			
			Cliente cliente;
			
			// Carrega o objeto do banco de dados
			if(cod != null && cod.length() != 0)
				cliente = repositorio.Open(Integer.parseInt(cod));
			else
				cliente = new Cliente();
			
			cliente.setNome(nome);
			
			repositorio.Save(cliente);

			// Gera uma listagem de clientes
			List<Cliente> clientes = repositorio.getTop10ByName();

			// Passa a listagem para a pagina JSP
			request.setAttribute("clientes", clientes);

			// Chamar a pagina JSP
			RequestDispatcher listagem = request.getRequestDispatcher("clientesListagem.jsp");
			listagem.forward(request, response);
		}
		catch(Exception ex){
			RequestDispatcher listagem = request.getRequestDispatcher("clientesListagem.jsp");
			listagem.forward(request, response);
		}
	}

}

