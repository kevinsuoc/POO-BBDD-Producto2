package modelo;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Seguro.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Seguro_ {

	public static final String PRECIO = "precio";
	public static final String TIPO_SEGURO = "tipoSeguro";

	
	/**
	 * @see modelo.Seguro#precio
	 **/
	public static volatile SingularAttribute<Seguro, Double> precio;
	
	/**
	 * @see modelo.Seguro#tipoSeguro
	 **/
	public static volatile SingularAttribute<Seguro, TipoSeguro> tipoSeguro;
	
	/**
	 * @see modelo.Seguro
	 **/
	public static volatile EntityType<Seguro> class_;

}

