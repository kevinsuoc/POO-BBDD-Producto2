package modelo;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Socio.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Socio_ {

	public static final String NUMERO_SOCIO = "numeroSocio";
	public static final String NOMBRE = "nombre";

	
	/**
	 * @see modelo.Socio#numeroSocio
	 **/
	public static volatile SingularAttribute<Socio, Integer> numeroSocio;
	
	/**
	 * @see modelo.Socio
	 **/
	public static volatile EntityType<Socio> class_;
	
	/**
	 * @see modelo.Socio#nombre
	 **/
	public static volatile SingularAttribute<Socio, String> nombre;

}

