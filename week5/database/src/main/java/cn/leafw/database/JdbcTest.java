package cn.leafw.database;

import java.sql.*;

/**
 * 10-1,10-2 JDBC
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/5/31
 */
public class JdbcTest {

    private static final String ADDR = "jdbc:mysql://localhost:3306/geekbang";
    private static final String USER = "root";
    private static final String PWD = "root";

    private static final String INSERT = "insert into t_user(name) values(?)";
    private static final String QUERY_ONE = "select id, name from t_user where id = (?)";
    private static final String UPDATE_ONE = "update t_user set name = (?) where id = (?)";
    private static final String DELETE = "delete from t_user where id = (?)";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        //注册数据库驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //取得数据库连接
            conn = DriverManager.getConnection(ADDR, USER, PWD);

            // 增
//            preparedStatement = conn.prepareStatement(INSERT);
//            User user = new User();
//            user.setName("abc");
//            int insert = insert(user, preparedStatement);
//            System.out.println("新增了" + insert + "条数据");

//            // 删
//            preparedStatement = conn.prepareStatement(DELETE);
//            int delete = deleteOne(1L, preparedStatement);
//            System.out.println("删除了" + delete + "条数据");

//
//            // 改
//            preparedStatement = conn.prepareStatement(UPDATE_ONE);
//            User user = new User();
//            user.setName("测试");
//            user.setId(2L);
//            int i = updateOne(user, preparedStatement);
//            System.out.println("修改了" + i + "条数据");
//
//            // 查
            preparedStatement = conn.prepareStatement(QUERY_ONE);
            rs = queryOne(3L, preparedStatement);
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (null != preparedStatement) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (null != conn) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private static int insert(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getName());
        return preparedStatement.executeUpdate();
    }

    private static int deleteOne(Long id, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate();
    }

    private static int updateOne(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setLong(2, user.getId());
        return preparedStatement.executeUpdate();
    }

    private static ResultSet queryOne(Long id,  PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, id);
        return preparedStatement.executeQuery();
    }

}

