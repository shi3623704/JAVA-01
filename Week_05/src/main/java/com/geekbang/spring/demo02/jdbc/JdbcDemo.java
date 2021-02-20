package com.geekbang.spring.demo02.jdbc;

import java.sql.*;
import java.util.Random;

/**
 * @Author 石天成
 * @Date 2021/2/20
 * @Time 11:41
 * @Despatch
 */
public class JdbcDemo {
    private static String INSERT_SQL = String.format("insert into student (student.id,student.username,student.age)values (%d,'%s',%d);", new Random().nextInt(10), "石天成", 1);
    private static String SELECT_SQL = "select * from student";
    private static String UPDATE_SQL = "update student set student.username = '石天成' where student.username='石朝哲'";
    private static String DELTER_SQL = "DELETE FROM `week_time`.`student`";

    public static void main(String[] args) throws SQLException {
        JdbcUtil jdbcUtil = new JdbcUtil("jdbc:mysql://localhost:3306/week_time", "root", "123456");
        Connection connection = jdbcUtil.getHikariDataSource().getConnection();
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
        Integer rs = preparedStatement.executeUpdate();
        System.out.println("插入Sql" + INSERT_SQL + "共变更内容:" + rs);
        get(statement);
        rs = statement.executeUpdate(UPDATE_SQL);
        System.out.println("修改Sql" + UPDATE_SQL + "共变更内容:" + rs);
        get(statement);
        rs = statement.executeUpdate(DELTER_SQL);
        System.out.println("删除Sql" + DELTER_SQL + "共变更内容:" + rs);
        get(statement);
        // 事务
        connection.setAutoCommit(false);
        try {
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            rs = preparedStatement.executeUpdate();
            System.out.println("插入Sql" + INSERT_SQL + "共变更内容:" + rs);
            // 手动模拟异常
            int i = 1/0;
            connection.commit();
        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
            System.out.println("发生异常导致回滚插入失败");
        }
        JdbcUtil.closeJDBCResourceQuiet(connection, statement, null);
    }

    public static void get(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(SELECT_SQL);
        new Student().getStudent(resultSet);
    }
}
