package modelo;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SocioInfantil.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class SocioInfantil_ extends modelo.Socio_ {

	public static final String NUMERO_SOCIO_TUTOR = "numeroSocioTutor";

	
	/**
	 * @see modelo.SocioInfantil#numeroSocioTutor
	 **/
	public static volatile SingularAttribute<SocioInfantil, Integer> numeroSocioTutor;
	
	/**
	 * @see modelo.SocioInfantil
	 **/
	public static volatile EntityType<SocioInfantil> class_;

}

