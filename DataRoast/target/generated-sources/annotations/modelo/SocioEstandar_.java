package modelo;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SocioEstandar.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class SocioEstandar_ extends modelo.SocioAdulto_ {

	public static final String SEGURO = "seguro";

	
	/**
	 * @see modelo.SocioEstandar#seguro
	 **/
	public static volatile SingularAttribute<SocioEstandar, Seguro> seguro;
	
	/**
	 * @see modelo.SocioEstandar
	 **/
	public static volatile EntityType<SocioEstandar> class_;

}

