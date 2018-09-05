package bookmark.dao;

import static common.Util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bookmark.entity.UserEntity;
import bookmark.entity.BookmarkEntity;

public class BookmarkDao {
    
	public int insertBookmark(BookmarkEntity bookmark, Connection conn) throws SQLException{
    	
		PreparedStatement pstmt = null;
    	int result=0;
    	
    	String sql = " INSERT INTO TB_BOOKMARK "
    			   + " VALUES ("
    			   + " 'BM_' || (SELECT LPAD(NVL(MAX(SUBSTR(BOOKMARK_ID, -3))+1, 1), 3, 0) FROM TB_BOOKMARK ), "
    			   + " ?, ?, ?, ?, ?, ?) ";

    	
    	try {
    		
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bookmark.getBookmark_title());
			pstmt.setString(2, bookmark.getBookmark_url());
			pstmt.setString(3, bookmark.getBookmark_img());
			pstmt.setString(4, bookmark.getBookmark_description());
			pstmt.setString(5, bookmark.getBookmark_interest());
			pstmt.setString(6, bookmark.getBookmark_video());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally{
			close(pstmt);
		}
    	
    	return result;
    }
	
	public int deleteBookmark(String bookmarkId, Connection conn) throws SQLException{
    	int result = 0;
    	PreparedStatement pstmt = null;
    	String sql = "DELETE FROM TB_BOOKMARK WHERE BOOKMARK_ID = ?";
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookmarkId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			close(pstmt);
		}
    	
    	return result;     
    }
	
	
	public ArrayList<BookmarkEntity> selectBookmarks(String searchType, String search, Connection conn) throws SQLException {
		
		ArrayList<BookmarkEntity> bookmarks = new ArrayList<BookmarkEntity>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = " SELECT * "
				   + " FROM TB_BOOKMARK "
				   + " WHERE 1=1 ";
		
		if(search != null && !"".equals(search)){
            sql += " AND " + searchType + " LIKE '%" + search + "%'";
        }
        
        sql += " ORDER BY BOOKMARK_ID DESC ";
        
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				BookmarkEntity bookmark = new BookmarkEntity();
				
				bookmark.setBookmark_id(rset.getString(1));
				bookmark.setBookmark_title(rset.getString(2));
				bookmark.setBookmark_url(rset.getString(3));
				bookmark.setBookmark_img(rset.getString(4));
				bookmark.setBookmark_description(rset.getString(5));
				bookmark.setBookmark_interest(rset.getString(6));
				bookmark.setBookmark_video(rset.getString(7));
				
				bookmarks.add(bookmark);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bookmarks;
	}
	
	public UserEntity login(String id,String pwd, Connection conn) throws SQLException{
	    
    	// TODO 
    	//로그인
    	UserEntity user = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String sql = "SELECT * FROM TB_BOOKMARKUSERS WHERE BM_USERID = ? AND BM_USERPW = ?";
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				user = new UserEntity();
				user.setId(rs.getString(1));
				user.setPw(rs.getString(2));
			}

			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(pstmt);
		}
    	
    }
	
	public BookmarkEntity selectBookmark(String bookmarkId, Connection conn) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BookmarkEntity bookmark = null;
		
		String sql = " SELECT * FROM TB_BOOKMARK WHERE BOOKMARK_ID = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookmarkId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				bookmark = new BookmarkEntity();
				
				bookmark.setBookmark_id(rset.getString(1));
				bookmark.setBookmark_title(rset.getString(2));
				bookmark.setBookmark_url(rset.getString(3));
				bookmark.setBookmark_img(rset.getString(4));
				bookmark.setBookmark_description(rset.getString(5));
				bookmark.setBookmark_video(rset.getString(6));
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bookmark;
	}
	
	 public int insertMember(UserEntity user, Connection conn) throws SQLException{
	       
	    	// TODO 
	    	//회원 가입
	    	
	    	PreparedStatement pstmt = null;
	    	int result=0;
	    	String sql = "INSERT INTO TB_BOOKMARKUSERS VALUES (?,?) ";
	    	
	    	try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, user.getId());
				pstmt.setString(2, user.getPw());
			
				result = pstmt.executeUpdate();
				
				if(result < 1 ){
					throw new SQLException();
	            }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw e;
			} finally{
				close(pstmt);
			}
	    	
	    	return result;
	        
	    }
}
