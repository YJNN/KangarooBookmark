package bookmark.biz;

import static common.Util.JdbcUtil.close;
import static common.Util.JdbcUtil.commit;
import static common.Util.JdbcUtil.getConnection;
import static common.Util.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


import bookmark.entity.UserEntity;
import bookmark.dao.BookmarkDao;
import bookmark.entity.BookmarkEntity;

public class BookmarkBiz {
	
	public int insertBookmark(BookmarkEntity entity) throws SQLException {
		
		Connection conn = null;
		BookmarkDao dao = new BookmarkDao();
		
		int result = 0;

		try {
			
			conn = getConnection();
			result = dao.insertBookmark(entity, conn);
			
			commit(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}

		return result;
	}
	
	public ArrayList<BookmarkEntity> selectBookmarks(String searchType, String search) throws SQLException {
		
		Connection conn = null;
		BookmarkDao dao = new BookmarkDao();
		
		try {
			conn = getConnection();
			return dao.selectBookmarks(searchType, search, conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}
	}
	
	public int deleteBookmark(String bookmakrId) throws SQLException{
	     
	    BookmarkDao dao = new BookmarkDao();
    	Connection conn = null;
    	int result = 0;
    	
    	try {
    		conn = getConnection();
			result = dao.deleteBookmark(bookmakrId, conn);
			commit(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
    	
    	return result;
    }
	
    public UserEntity login(String id,String pwd) throws SQLException{

    	// TODO 
    	//로그인
    	UserEntity user = null;    	
    	Connection conn = null;
    	BookmarkDao dao = new BookmarkDao();

    	try {
    		conn = getConnection();
			user = dao.login(id, pwd, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}
    	 
    	return user;
    }
    
	public BookmarkEntity selectBookmark(String bookmakrId) throws SQLException {
		
		Connection conn = null;
		BookmarkDao dao = new BookmarkDao();
		
		try {
			conn = getConnection();
			return dao.selectBookmark(bookmakrId, conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}
	}

	
	public int insertUser(UserEntity user) throws SQLException{
	       
    	// TODO 
    	//회원가입
    	Connection conn = null;
    	BookmarkDao dao = new BookmarkDao();
    	int result = 0;
    	
    	try {
			
    		conn = getConnection();
			result = dao.insertMember(user, conn);
			
			if(result>0) commit(conn);		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			rollback(conn);
			throw e;
		} finally {
			close(conn);			
		}
    	
    	return result;
    }
}
