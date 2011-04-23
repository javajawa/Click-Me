/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harcourtprogramming.clickme;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.SortedSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Benedict
 */
@WebServlet(name = "GameServlet", urlPatterns =
{
	"/play"
})
public final class ScoreServlet extends HttpServlet
{
	private static ClickMeGame game;
	private static SortedSet<Map.Entry<String, Integer>> lastestScores;
		
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try
		{
			out.write("<table>");
			out.write("<tr><h1>Latest Scores</h1></tr>");
			for (Map.Entry<String, Integer> score : lastestScores)
			{
				out.write("<tr>");
				out.write("<td>" +  score.getKey()  + "</td>");
				out.write("<td>" + score.getValue() + "</td>");
				out.write("</tr>");
			}
			out.write("</table>");
		}
		finally
		{			
			out.close();
		}
	}

	public ScoreServlet()
	{
		game = ClickMeGame.getInstance();
		lastestScores = game.getScores();
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
		processRequest(request, response);
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
		return "Servlet that gives the current high scores for a click me game";
	}// </editor-fold>

}
