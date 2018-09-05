package bookmark.cmd;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookmark.biz.BookmarkBiz;
import bookmark.entity.UserEntity;

/**
 * Servlet implementation class RegisterBookmark
 */
@WebServlet("/RegisterBookmark")
public class RegisterBookmark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterBookmark() {
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
		request.setCharacterEncoding("UTF-8");
    	String userId = request.getParameter("userId");
    	String userPwd = request.getParameter("userPwd1");

    	UserEntity user = new UserEntity();
    	user.setId(userId);
    	user.setPw(userPwd);
    	BookmarkBiz biz = new BookmarkBiz();
    	
    	RequestDispatcher rd = null;

    	try {
        	
        	
    		int result = biz.insertUser(user);
        	        	
        	if(result > 0){
        		rd = request.getRequestDispatcher("/RegisterSuccess.jsp");
        		request.setAttribute("success","회원가입에 성공했습니다.");
        		rd.forward(request, response);
        	} else {
        		throw new Exception();
        	}

        } catch ( SQLException e ) {
            e.printStackTrace();
            rd = request.getRequestDispatcher( "/common/message.jsp" );
            request.setAttribute( "message", "[ERROR] 이미 사용중인 아이디입니다. 다른 아이디를 입력하세요." );
            rd.forward( request, response );
        } catch ( Exception e ) {
            e.printStackTrace();
            rd = request.getRequestDispatcher( "/common/message.jsp" );
            request.setAttribute( "message", "[ERROR] 회원가입 도중 예상치 못한 문제가 발생하였습니다." );
            rd.forward( request, response );
        }
		
	}

}
