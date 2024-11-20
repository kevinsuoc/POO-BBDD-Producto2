package modelo;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Excursion.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Excursion_ {

	public static final String DESCRIPCION = "descripcion";
	public static final String FECHA = "fecha";
	public static final String CODIGO = "codigo";
	public static final String PRECIO_INSCRIPCION = "precioInscripcion";
	public static final String NUM_DIAS = "numDias";

	
	/**
	 * @see modelo.Excursion#descripcion
	 **/
	public static volatile SingularAttribute<Excursion, String> descripcion;
	
	/**
	 * @see modelo.Excursion#fecha
	 **/
	public static volatile SingularAttribute<Excursion, LocalDate> fecha;
	
	/**
	 * @see modelo.Excursion#codigo
	 **/
	public static volatile SingularAttribute<Excursion, String> codigo;
	
	/**
	 * @see modelo.Excursion#precioInscripcion
	 **/
	public static volatile SingularAttribute<Excursion, Double> precioInscripcion;
	
	/**
	 * @see modelo.Excursion#numDias
	 **/
	public static volatile SingularAttribute<Excursion, Integer> numDias;
	
	/**
	 * @see modelo.Excursion
	 **/
	public static volatile EntityType<Excursion> class_;

}

