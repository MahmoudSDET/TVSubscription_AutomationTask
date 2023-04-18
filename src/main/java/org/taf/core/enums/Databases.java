
package org.taf.core.enums;

public enum Databases {
	/**
	 * MYSQL Database.
	 */
	MYSQL("mysql", "com.mysql.jdbc.Driver", "jdbc:mysql://"),
	/**
	 * MSSQL Database.
	 */
	MSSQL("sql", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver:"),
	/**
	 * MONGO Database
	 */
	MONGO("mongo", "mongodb.jdbc.MongoDriver", "jdbc:mongo://");

	private String value;
	private String dbDriverName;
	private String urlPrefix;

	Databases(String value, String dbDriverName, String urlPrefix) {
		this.value = value;
		this.dbDriverName = dbDriverName;
		this.urlPrefix = urlPrefix;
	}

	public String getValue() {
		return value;
	}

	public String getDbDriverClassName() {
		return dbDriverName;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public static Databases fromString(String type) {
		for (Databases dbType : values()) {
			if (dbType.getValue().equals(type)) {
				return dbType;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return value;
	}
}
