package bookmark.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookmark.biz.BookmarkBiz;
import bookmark.entity.BookmarkEntity;

@WebServlet("/InsertBookmark")
public class InsertBookmark extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertBookmark() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 입력 처리
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String url = request.getParameter("url");
		String video = request.getParameter("video");
		String description = request.getParameter("description");
		String interest = request.getParameter("interest");
		
		String image = null;
		if(request.getParameter("image") == null || request.getParameter("image").equals(""))
			image = "img/default.jpg";
		else
			image = request.getParameter("image");
		
		// DB를 위한 객체 생성
		BookmarkBiz biz = null;
		BookmarkEntity bookmark = null;
		
		try {

			bookmark = new BookmarkEntity();
			bookmark.setBookmark_title(title);
			bookmark.setBookmark_url(url);
			bookmark.setBookmark_img(image);
			bookmark.setBookmark_video(video);
			bookmark.setBookmark_description(description);
			bookmark.setBookmark_interest(interest);

			biz = new BookmarkBiz();
			biz.insertBookmark(bookmark);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
