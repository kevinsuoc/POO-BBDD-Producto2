package util;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ValidatorFactory validatorFactory;

    public static void initializeUtils(){
        sessionFactory = new Configuration().configure().buildSessionFactory();
        validatorFactory = Validation.byProvider(org.hibernate.validator.HibernateValidator.class)
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
    }

    public static void closeUtils(){
        sessionFactory.close();
        validatorFactory.close();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Validator getValidator(){return validatorFactory.getValidator();}
}
