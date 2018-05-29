package com.emplogin.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.competence.model.CompetenceService;
import com.competence.model.CompetenceVO;
import com.employee.model.EmployeeVO;
import com.fun.model.FunService;
import com.mem.model.MemVO;

public class EmpLoginFilter implements Filter {
	private FilterConfig config;

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
//		ServletContext context = req.getServletContext();

		EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");
		List<CompetenceVO> list = (List) session.getAttribute("list");
//		Integer fun = null;
//		Integer empno = null;
//		for (CompetenceVO competencevo : list) {
//			fun = competencevo.getFunNo();
//			List<CompetenceVO> funlist = new CompetenceService().getFunCompetece(fun);
//			for (CompetenceVO emp : funlist) {
//				empno = emp.getEmpNo();
//			}
//		}
//		if (!employeeVO.getEmpno().equals(empno)) {
//			context.setAttribute("NoGrant", req.getRequestURI());
//			res.sendRedirect(req.getContextPath() + "/front/member/addEmpRegistered.jsp");
//			return;
//		} else {
//			chain.doFilter(request, response);
//		}
//		if (!employeeVO.getEmpno().equals(empno)) {
//			context.setAttribute("NoGrant1", req.getRequestURI());
//			res.sendRedirect(req.getContextPath() + "/front/member/selectEmp.jsp.jsp");
//			return;
//
//		} else {
//			chain.doFilter(request, response);
//		}

		if (employeeVO == null && list == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/empLogin.jsp");
			//因為Fileter把整個Back-End資料夾都過濾了，如果把empLogin放在Back-End內會造成無限迴圈
			//所以暫把empLogin放在WebContent根目錄
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
