package com;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PaymentAPI")
public class PaymentAPI extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Payment payment=new Payment();
	
	public PaymentAPI() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		resp.getWriter().append("Served at: ").append(req.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		System.out.print(req.getParameter("cvv"));
		String output = payment.StoreOnlinePayment(
				req.getParameter("userid"),
				req.getParameter("orderid"),
				req.getParameter("researchid"),
				req.getParameter("fundingid"),
				req.getParameter("paymentmethod"),
				req.getParameter("purpose"),
				req.getParameter("amount"),
				req.getParameter("statues"),
				req.getParameter("cardnumber"),
				req.getParameter("cvv"),
				req.getParameter("cardtype"),
				req.getParameter("expiredate"));
		resp.getWriter().write(output);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
		Map paras = getParasMap(req);
		String output = payment.deletecardDetails(paras.get("paymentid").toString());
		System.out.println(paras.get("paymentid").toString());
		resp.getWriter().write(output);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//super.doPut(req, resp);
		Map paras = getParasMap(req);
		//System.out.println("Testing 1: "+paras.get("hidpaymentIdUpdate"));
		System.out.println(paras.get("paymentmethod").toString()+ paras.get("purpose").toString()+paras.get("statues").toString()+paras.get("amount").toString()+paras.get("hidpaymentIdSave").toString());
		//System.out.println("Testing 1: "+paras.get("hidpaymentIdUpdate"));
		String output = payment.updatePaymentdetails(
				paras.get("paymentmethod").toString(),
				paras.get("purpose").toString(),
				paras.get("statues").toString(),  
				paras.get("amount").toString(),
				paras.get("hidpaymentIdSave").toString());
				
				resp.getWriter().write(output);
				
	}

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	

}
