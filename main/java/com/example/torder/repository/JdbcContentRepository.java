package com.example.torder.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import com.example.torder.controller.MatchingForm;
import com.example.torder.domain.Category;
import com.example.torder.domain.Content;

public class JdbcContentRepository implements ContentRepository {
    private final DataSource dataSource;

    public JdbcContentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /* 전체 게시글 내용 추출 */
    @Override
    public List<Content> getTotalContent() {
        String sql = "select * from contents";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            rs = pstmt.executeQuery();
            List<Content> list = new ArrayList<>();
            while (rs.next()) {
                Content content = new Content();
                content.setPK_content_id(rs.getInt("PK_content_id"));
                content.setCategory_id(rs.getInt("category_id"));
                content.setTitle(rs.getString("title"));
                content.setBody(rs.getString("body"));
                content.setLocation(rs.getString("location"));
                content.setEnd_time(rs.getString("end_time"));
                content.setContent_state(rs.getBoolean("content_state"));
                list.add(content);
            }
            return list;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    /* 카테고리 id가 동일한 카테고리 이름 추출 */
    @Override
    public List<Category> getTotalCategory() {
        String sql = "select * from category ";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            rs = pstmt.executeQuery();
            List<Category> list = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setPK_category_id(rs.getString("PK_category_id"));
                category.setCategory_name(rs.getString("category_name"));
                list.add(category);
            }
            return list;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    /* 받은 category_id 이용해서 해당 모든 게시글 정보 가져오기 */
    @Override
    public List<Content> getCategoryContentInfo(Category category) {
        String sql = "select * from contents where category_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, Integer.valueOf(category.getPK_category_id()));
            rs = pstmt.executeQuery();
            List<Content> list = new ArrayList<>();
            while (rs.next()) {
                Content content = new Content();
                content.setPK_content_id(rs.getInt("PK_content_id"));
                content.setCategory_id(rs.getInt("category_id"));
                content.setTitle(rs.getString("title"));
                content.setBody(rs.getString("body"));
                content.setLocation(rs.getString("location"));
                content.setEnd_time(rs.getString("end_time"));
                content.setContent_state(rs.getBoolean("content_state"));
                list.add(content);
            }
            return list;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    /* 받은 content_id 이용해서 게시글 정보 가져오기 */
    /* category_id는 여러개가 존재할 수 있음, 내가 고른 게시글 정보만 필요 */
    @Override
    public Content getContentInfo(MatchingForm matchingForm) {
        String sql = "select * from contents where PK_content_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, Integer.valueOf(matchingForm.getContentid()));
            rs = pstmt.executeQuery();
            Content content = new Content();
            if (rs.next()) {
                content.setPK_content_id(rs.getInt("PK_content_id"));
                content.setCategory_id(rs.getInt("category_id"));
                content.setTitle(rs.getString("title"));
                content.setBody(rs.getString("body"));
                content.setLocation(rs.getString("location"));
                content.setEnd_time(rs.getString("end_time"));
                content.setContent_state(rs.getBoolean("content_state"));
            }
            return content;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
