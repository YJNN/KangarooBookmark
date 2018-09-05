package bookmark.cmd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookmark.biz.BookmarkBiz;
import bookmark.entity.BookmarkEntity;

/**
 * Servlet implementation class DeletBookmark
 */
@WebServlet("/DeletBookmark")
public class DeletBookmark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletBookmark() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String bookmarkId = request.getParameter("bmId");
		
		System.out.println(bookmarkId);
				
		// DB를 위한 객체 생성
		BookmarkBiz biz = new BookmarkBiz();
		ArrayList<BookmarkEntity> bookmarks = new ArrayList<BookmarkEntity>();
				
		try {
			//삭제
			biz.deleteBookmark(bookmarkId);
			
			RequestDispatcher rd = request.getRequestDispatcher("/BookmarkList");
			rd.forward(request, response);
	

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
