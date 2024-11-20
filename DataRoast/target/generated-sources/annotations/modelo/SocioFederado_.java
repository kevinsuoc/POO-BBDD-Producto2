package modelo;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SocioFederado.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class SocioFederado_ extends modelo.SocioAdulto_ {

	public static final String FEDERACION = "federacion";

	
	/**
	 * @see modelo.SocioFederado
	 **/
	public static volatile EntityType<SocioFederado> class_;
	
	/**
	 * @see modelo.SocioFederado#federacion
	 **/
	public static volatile SingularAttribute<SocioFederado, Federacion> federacion;

}

