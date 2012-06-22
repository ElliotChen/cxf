package com.sforce.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.task.TaskExecutor;

import com.sforce.domain.Job;
import com.sforce.intf.Component;
import com.sforce.service.JobManager;
import com.sforce.util.ContextHolder;

/**
 * Servlet implementation class ComponentServlet
 */
public class ComponentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ComponentServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String cpname = request.getParameter("cpname");
		List<Job> jobs = new ArrayList<Job>();
		request.setAttribute("components", ContextHolder.getAllConComponents());
		request.setAttribute("jobs", jobs);
		if ("trigger".equals(action)) {
			Component bean = ContextHolder.context.getBean(cpname, Component.class);
			TaskExecutor taskExecutor = ContextHolder.context.getBean("taskExecutor", TaskExecutor.class);
			if (null != bean) {
				taskExecutor.execute(bean);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/components.jsp").forward(request, response);
		} else if ("listjob".equals(action)) {
			JobManager jobManager = ContextHolder.context.getBean("jobManager", JobManager.class);
			Job example = new Job();
			example.setComponent(cpname);
			jobs.addAll(jobManager.listByExample(example, null, null, null, new String[] {"createdDate"}));
			request.getRequestDispatcher("/WEB-INF/jsp/jobs.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
		}
		
		
	}

}
