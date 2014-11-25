package br.com.aceleradora.RegistroLivre.dao;

import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;


@Component
//@RequestScoped
public class SessionCreator implements ComponentFactory<Session>{

	private final SessionFactory factory;
	private Session session;
	
	public SessionCreator(SessionFactory factory) {
		this.factory = factory;
		create();
	}
	
	@Override
	public Session getInstance() {
		return session;
	}
	
//	@PostConstruct
	public void create(){
		this.session = factory.openSession();
	}

	@PreDestroy
	public void destroy(){
		System.out.println("SESSION CREATOR DESTROY 1");
		this.session.close();
		
		System.out.println("SESSION CREATOR DESTROY 2.");
	}
	
}
