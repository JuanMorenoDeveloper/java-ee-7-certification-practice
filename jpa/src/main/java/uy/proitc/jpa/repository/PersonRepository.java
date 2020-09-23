package uy.proitc.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;
import uy.proitc.jpa.entity.Person;

@Stateless
public class PersonRepository {

  private EntityManager entityManager;

  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<Person> findAll() {
    CriteriaQuery<Person> criteriaQuery = entityManager.getCriteriaBuilder()
        .createQuery(Person.class);
    criteriaQuery.select(criteriaQuery.from(Person.class));
    Stream<Person> persons = entityManager.createQuery(criteriaQuery)
        .getResultStream();
    return persons.collect(Collectors.toList());
  }

  public Optional<Person> findById(int id) {
    TypedQuery<Person> query = entityManager.createNamedQuery(Person.findById, Person.class);
    query.setParameter("id", id);
    return Optional.ofNullable(query.getSingleResult());
  }

  public void save(@Valid Person person) {
    entityManager.persist(person);
  }
}
