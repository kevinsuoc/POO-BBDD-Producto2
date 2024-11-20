package modelo;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SocioAdulto.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class SocioAdulto_ extends modelo.Socio_ {

	public static final String NIF = "nif";

	
	/**
	 * @see modelo.SocioAdulto#nif
	 **/
	public static volatile SingularAttribute<SocioAdulto, String> nif;
	
	/**
	 * @see modelo.SocioAdulto
	 **/
	public static volatile EntityType<SocioAdulto> class_;

}

