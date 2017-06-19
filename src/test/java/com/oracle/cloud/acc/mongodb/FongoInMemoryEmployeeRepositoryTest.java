package com.oracle.cloud.acc.mongodb;

import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FongoInMemoryEmployeeRepositoryTest {

    public FongoInMemoryEmployeeRepositoryTest() {
    }
    final static String PU_NAME = "fongo-ogm";

    @BeforeClass
    public static void setUpClass() {

        JPAFacade.bootstrapEMF(PU_NAME, Collections.emptyMap());

    }

    @AfterClass
    public static void tearDownClass() {
        JPAFacade.closeEMF();
    }

    EmployeeRepository cut;

    @Before
    public void setUp() {
        cut = new EmployeeRepository();
    }

    @Test
    public void createSingleEmployeeTest() {
        String id = "42";
        String name = "abhirockzz";
        cut.create(id, name);
    }

    @Test
    public void getSingleEmployeeTest() {
        String id = "43";
        String name = "linus";
        cut.create(id, name);

        Employee emp = cut.get(id);
        assertNotNull("Employee was null!", emp);
        assertEquals("Wrong employee id", emp.getEmpId(), Integer.valueOf(id));
        assertEquals("Wrong employee name", emp.getName(), name);
        assertEquals("Wrong employee email", emp.getEmail(), name + "@mycompany.com");
    }

    @Test
    public void getAllEmployeesTest() {

        String id = "44";
        String name = "abhirockzz";
        cut.create(id, name);

        id = "45";
        name = "kafeene";

        cut.create(id, name);
        List<Employee> emps = cut.all();
        assertNotNull("Employee list was null!", emps);

        Employee current = emps.stream().filter((emp) -> emp.getEmpId().equals(Integer.valueOf("44"))).findFirst().get();
        assertNotNull("Employee 44 not present", current);
        assertEquals("Wrong employee name", current.getName(), "abhirockzz");
        assertEquals("Wrong employee email", current.getEmail(), "abhirockzz@mycompany.com");

        current = emps.stream().filter((emp) -> emp.getEmpId().equals(Integer.valueOf("45"))).findFirst().get();
        assertNotNull("Employee 45 not present", current);
        assertEquals("Wrong employee name", current.getName(), "kafeene");
        assertEquals("Wrong employee email", current.getEmail(), "kafeene@mycompany.com");
    }

}
