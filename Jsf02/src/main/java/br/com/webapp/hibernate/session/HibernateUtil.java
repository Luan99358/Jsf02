package br.com.webapp.hibernate.session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;

import br.com.webapp.implementacao.crud.VariableConectionUtil;

/*
 * Clase responsavel por estabelecerconexao com o hibernate
 * 
 * 
 */


public class HibernateUtil implements Serializable{
	

	private static final long serialVersionUID = 1L;


	public static String JAVA_COMP_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";
	
	private static SessionFactory sessionFactory = buildSessionfactory();
	
	
	/*
	 * Responsavel por ler o arquivo de configuracao hibernate.cfg.xml 
	 */

	private static SessionFactory buildSessionfactory() {
		try {
			if(sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}
			return sessionFactory;
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar conexão SessionFactory");
		}
		
	}
		
	/*
	 * Retorna o session factory corrente 
	*/
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
		
	
	/*
	 * Retorna a sessao do SessionFactory
	 */
	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
	
	
	/*
	 *Abre uma nova sessão do SessionFactory 
	 */
	public static Session openSession() {
		if(sessionFactory == null) {
			buildSessionfactory();
		}
		return sessionFactory.openSession();
	}
	
	
	/**
	 * Obtema connection do provedor de conexoes configurado
	 * @return Connection Sql
	 * @throws SQLException
	 */
	public static Connection getConnectionProvider() throws SQLException {
		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();
		
	}
	
	
	/**
	 * 
	 * @return Connection no InitialContect java:/comp/env/jdbc/datasource
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(JAVA_COMP_ENV_JDBC_DATA_SOURCE);
		return ds.getConnection();
	}
	
	
	/**
	 * 
	 * @return DataSource JNDI Tomcat
	 * @throws NamingException
	 */
	public DataSource getDataSourceJndi() throws NamingException{
		InitialContext context = new InitialContext();
		return (DataSource) context.lookup(VariableConectionUtil.JAVA_COMP_ENV_JDBC_DATA_SOURCE);
	}
	
	
	
	
	
	
}
