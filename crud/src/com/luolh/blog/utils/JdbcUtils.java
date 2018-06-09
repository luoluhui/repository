package com.luolh.blog.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.beanutils.BeanUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * jdbc通用工具类
 * 
 * @author LuoLH
 * @since 20180528
 */
public class JdbcUtils {

	private static DataSource datasource = null;
	/**
	 * 获取数据库连接
	 * 
	 * @return Connection 数据库连接
	 */
	public static Connection getConnection() throws Exception {
		//
		if(datasource == null) {
			datasource = new ComboPooledDataSource("c3p0");
		}
		// 返回连接
		return datasource.getConnection();
	}

	/**
	 * 添加、修改、删除操作
	 * 
	 * @param sql sql语句
	 * @param value sql语句通配符对应的参数
	 *
	 */
	public static void update(String sql, Object... value) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < value.length; i++) {
				preparedStatement.setObject(i + 1, value[i]);
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(null, preparedStatement, connection);
		}
	}

	/**
	 * 查询操作
	 * 
	 * @param <T> 任意实体类
	 * @param sql sql语句
	 * @param value sql语句通配符对应的参数
	 * @return 当前实体类
	 */
	public static <T> T query(Class<T> clazz, String sql, Object... value) {
		List<T> list = queryAll(clazz, sql, value);
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询总条数
	 * @param clazz
	 * @param sql
	 * @param value
	 * @return 总条数
	 */
	public static int totalCount(String sql, Object...value) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int resultCount = 0;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < value.length; i++) {
				preparedStatement.setObject(i + 1, value[i]);
			}
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				resultCount = resultSet.getInt(1);
			}
			return resultCount;
		} catch (Exception e) {
			e.printStackTrace();
			return resultCount;
		} finally {
			release(resultSet, preparedStatement, connection);
		}
	}
	
	/**
	 * 查询操作
	 * 
	 * @param <T> 任意实体类
	 * @param sql sql语句
	 * @param value sql语句通配符对应的参数
	 * @return 当前实体类
	 */
	public static <T> List<T> queryAll(Class<T> clazz, String sql, Object... value) {
		List<T> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
//			System.out.println(sql);
			//sql语句拼接
			for (int i = 0; i < value.length; i++) {
				preparedStatement.setObject(i + 1, value[i]);
			}
			resultSet = preparedStatement.executeQuery();

			//获取解释resultSet含义的数据
			ResultSetMetaData metaData = resultSet.getMetaData();
			//List中每一个map对应一条记录
			List<Map<String, Object>> allList = new ArrayList<>();
			Map<String, Object> map = null;
			//遍历结果集
			while(resultSet.next()) {
				map = new HashMap<>();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					//获取列的别名（无别名获取列名）
					String columnLabel = metaData.getColumnLabel(i + 1);
					//获取列的值
					Object columnValue = resultSet.getObject(i + 1);
					map.put(columnLabel, columnValue);
				}
				allList.add(map);
			}
			T object = null;
			if (allList.size() > 0) {
				for(Map<String, Object> listMap:allList) {
					object = clazz.newInstance();
					for (Map.Entry<String, Object> entry : listMap.entrySet()) {
						String entryKey = entry.getKey();
						Object entryValue = entry.getValue();
						BeanUtils.setProperty(object, entryKey, entryValue);
					}
					list.add(object);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(resultSet, preparedStatement, connection);
		}
		return list;
	}
	/**
	 * 关闭连接释放资源
	 * 
	 * @param resultSet 查询结果集
	 * @param preparedStatement
	 * @connection 连接
	 */
	public static void release(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
