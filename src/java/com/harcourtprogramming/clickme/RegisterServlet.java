package com.harcourtprogramming.clickme;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Benedict
 */
public final class RegisterServlet extends HttpServlet
{
	private ClickMeGame game;
	
	/** 
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private void processRequest(HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException
	{
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies)
		{
			if (cookie.getName().equals("click-me-player"))
			{
				try
				{
					int score = Integer.parseInt(request.getParameter("score"));
					if (score <= 25)
					{
						game.addScore(cookie.getValue(), score);
					}
				}
				catch (NumberFormatException ex)
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				}
				return;
			}
		}
		
		// No cookie sent :(
		response.sendError(HttpServletResponse.SC_CONFLICT);
	}

	public RegisterServlet()
	{
		game = ClickMeGame.getInstance();
	}
	
	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/** 
	 * Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException
	{
		//processRequest(request, response);
	}

	/** 
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException
	{
		processRequest(request, response);
	}

	/** 
	 * Returns a short description of the servlet.
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo()
	{
		return "Short description";
	}// </editor-fold>

}
