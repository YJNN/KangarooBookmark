package bookmark.cmd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookmark.biz.BookmarkBiz;
import bookmark.entity.UserEntity;

/**
 * Servlet implementation class LoginBookmark
 */
@WebServlet("/LoginBookmark")
public class LoginBookmark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginBookmark() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("inputText");
		String userPw = request.getParameter("inputPW");
		System.out.println(userId);
		System.out.println(userPw);
		//response.setContentType("html/text;charset=UTF-8");
		
		try{
			BookmarkBiz biz = new BookmarkBiz();
			UserEntity user = biz.login(userId, userPw);			
			
			if(user!=null){
				session.setAttribute("loginUserInfo", user);
				response.sendRedirect("/Kangaroo2/BookmarkList");
				//RequestDispatcher rd = request.getRequestDispatcher("/BookmarkList");
				//rd.forward(request, response);
			}
			else{
				RequestDispatcher rd = request.getRequestDispatcher( "/common/message.jsp" );
				request.setAttribute( "message", "아이디나 비밀번호가 틀렸습니다. 다시 입력해주세요." );
				rd.forward( request, response );
			}
		
		}catch(Exception e){
		     e.printStackTrace();
		}

	}

}
