package com.example.torder.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import com.example.torder.domain.Member;

public class JdbcMemberRepository implements MemberRepository {
    private final DataSource dataSource;
    private static Map<String, Member> Id_db = new HashMap<>();
    private static Map<String, Member> Nick_db = new HashMap<>();

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /* 중복 id 판단 */
    @Override
    public boolean findById(String id) {
        return Id_db.containsKey(id);
    }

    /* 중복 nickname 판단 */
    @Override
    public boolean findByNickname(String nickname) {
        return Nick_db.containsKey(nickname);
    }

    /* 기존 회원 정보 불러와서 저장 */
    @Override
    public void existMembersave() {
        String sql = "select * from users";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getString("user_id"));
                member.setNickname(rs.getString("nickname"));
                Id_db.put(member.getId(), member);
                Nick_db.put(member.getNickname(), member);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    /* 아이디, 비밀번호 일치 확인 */
    @Override
    public boolean loginCheck(Member member) {
        String sql = "select * from users where user_id = ? and password = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            rs = pstmt.executeQuery();
            if (rs.next() && rs.getString("user_id").equals(member.getId())
                    && rs.getString("password").equals(member.getPassword())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    /* 회원 DB저장 */
    @Override
    public Member save(Member member) {
        Id_db.put(member.getId(), member);
        Nick_db.put(member.getNickname(), member);

        String sql = "insert into users(user_id, password, nickname, user_status) values (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getNickname());
            pstmt.setBoolean(4, false);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                member.setId(rs.getString(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
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
