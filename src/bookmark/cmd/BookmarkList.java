package bookmark.cmd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookmark.biz.BookmarkBiz;
import bookmark.entity.BookmarkEntity;

@WebServlet("/BookmarkList")
public class BookmarkList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 입력 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		
		String searchType = request.getParameter("selectSel");
		String search = request.getParameter("select");
		
		ArrayList<BookmarkEntity> bookmarks = new ArrayList<BookmarkEntity>();
		
		BookmarkBiz biz = new BookmarkBiz();
		
		try {
			
			bookmarks = biz.selectBookmarks(searchType, search);
			
			RequestDispatcher rd = request.getRequestDispatcher("/bookmarkList.jsp");
			request.setAttribute("bookmarkList", bookmarks);
			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
