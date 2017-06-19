package com.oracle.cloud.acc.mongodb;

import java.util.List;
import javax.persistence.EntityManager;

public class EmployeeRepository {
    
    public void create(String empId, String name) {
        EntityManager em = null;
        try {
            em = JPAFacade.getEM();
            em.getTransaction().begin();
            em.persist(new Employee(Integer.valueOf(empId), name, name+"@mycompany.com"));
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public Employee get(String id) {
        EntityManager em = null;
        Employee emp = null;
        try {
            em = JPAFacade.getEM();
            emp = em.find(Employee.class, Integer.valueOf(id));
        } catch (Exception e) {
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return emp;
    }

    public List<Employee> all() {
        EntityManager em = null;
        List<Employee> employees = null;
        try {
            em = JPAFacade.getEM();
            employees = em.createQuery("SELECT c FROM Employee c").getResultList();
        } catch (Exception e) {
            throw e;
        } finally {

            if (em != null) {
                em.close();
            }

        }

        return employees;
    }

}
