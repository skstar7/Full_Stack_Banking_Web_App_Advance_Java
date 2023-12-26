package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Bank_dao;
import Dto.Bank_account;
import java.util.List;
@WebServlet("/changestatus")
public class Change_Status extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String ac_no =  req.getParameter("acno");
	long acc_no = Long.parseLong(ac_no);
	Bank_dao bank_dao = new Bank_dao();
	
	     Bank_account bank_account= bank_dao.fetch_account_details(acc_no);
	     
	     if (bank_account.isStatus()) {
		bank_account.setStatus(false);	
		}else {
			bank_account.setStatus(true);
		}
	     
	     bank_dao.update_the_details(bank_account);
	     
	     resp.getWriter().print("<h1>Status get updated </>");
	     
	     
	     List<Bank_account> list =  bank_dao.fetchAll();
	     req.getSession().setAttribute("list", list);
	     req.getRequestDispatcher("adminhome.jsp").include(req, resp);
		
		
	}

}
