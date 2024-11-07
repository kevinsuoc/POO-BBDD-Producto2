package dataroast.Test;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelSocioEstandarTest {
    @BeforeAll
    void init(){

    }

    @Test
    @Order(1)
    void insert(){

    }

    @Test
    @Order(2)
    void update(){

    }

    @Test
    @Order(3)
    void get(){

    }

    @Test
    @Order(4)
    void getAll(){

    }

    @AfterAll
    void cleanup(){

    }
}
