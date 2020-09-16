package uy.proitc.jpa.repository;

import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import uy.proitc.jpa.domain.Person;

@Stateless
public class PersonRepository {

  private EntityManager entityManager;

  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Stream<Person> findAll() {
    CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
    criteriaQuery.select(criteriaQuery.from(Person.class));
    return entityManager.createQuery(criteriaQuery).getResultStream();
  }
}
