package modelo;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Federacion.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Federacion_ {

	public static final String CODIGO = "codigo";
	public static final String NOMBRE = "nombre";

	
	/**
	 * @see modelo.Federacion#codigo
	 **/
	public static volatile SingularAttribute<Federacion, String> codigo;
	
	/**
	 * @see modelo.Federacion
	 **/
	public static volatile EntityType<Federacion> class_;
	
	/**
	 * @see modelo.Federacion#nombre
	 **/
	public static volatile SingularAttribute<Federacion, String> nombre;

}

