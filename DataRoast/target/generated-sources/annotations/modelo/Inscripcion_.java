package modelo;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Inscripcion.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Inscripcion_ {

	public static final String NUMERO_INSCRIPCION = "numeroInscripcion";

	
	/**
	 * @see modelo.Inscripcion#numeroInscripcion
	 **/
	public static volatile SingularAttribute<Inscripcion, Integer> numeroInscripcion;
	
	/**
	 * @see modelo.Inscripcion
	 **/
	public static volatile EntityType<Inscripcion> class_;

}

