package br.com.webapp.implementacao.crud;

import java.io.Serializable;

/*
 * Nome do caminho do JNDI datasource Tomcat
 * 
 * */
public class VariableConectionUtil implements Serializable {
	
	private static final long serialVersionUid = 1L;
	
	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";
	
	

}
