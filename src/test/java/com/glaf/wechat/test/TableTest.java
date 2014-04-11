package com.glaf.wechat.test;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.glaf.core.jdbc.DBConnectionFactory;
import com.glaf.core.util.DBUtils;
import com.glaf.core.util.JdbcUtils;

public class TableTest {

	public static void main(String[] args) {
		System.setProperty("devMode", "true");
		List<String> tables = DBUtils.getTables();
		if (tables != null && !tables.isEmpty()) {
			Connection con = null;
			try {
				con = DBConnectionFactory.getConnection();
				for (String table : tables) {
					if (StringUtils.startsWith(table.toUpperCase(), "WX_LOG_")) {
						con.setAutoCommit(false);
						DBUtils.dropTable(con, table);
						con.commit();
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				JdbcUtils.close(con);
			}
		}
	}

}
