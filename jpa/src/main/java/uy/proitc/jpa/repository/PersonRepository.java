package uy.proitc.jpa.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import uy.proitc.jpa.entity.Person;

@Stateless
public class PersonRepository {

  private EntityManager entityManager;

  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<Person> findAll() {
    CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
    criteriaQuery.select(criteriaQuery.from(Person.class));
    Stream<Person> persons = entityManager.createQuery(criteriaQuery)
        .getResultStream();
    return persons.collect(Collectors.toList());
  }
}
