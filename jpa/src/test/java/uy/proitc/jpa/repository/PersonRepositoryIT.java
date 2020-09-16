package uy.proitc.jpa.repository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.EJB;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.StatelessBean;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Configuration;
import org.apache.openejb.testing.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.proitc.jpa.domain.Person;

@RunWith(ApplicationComposer.class)
public class PersonRepositoryIT {

  private static final Logger log = LoggerFactory.getLogger(PersonRepositoryIT.class);

  @Module
  public PersistenceUnit persistence() {
    PersistenceUnit unit = new PersistenceUnit("TEST_PU");
    unit.setJtaDataSource("movieDatabase");
    unit.setNonJtaDataSource("movieDatabaseUnmanaged");
    unit.setProperty("openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=true)");
    unit.setProperty("javax.persistence.sql-load-script-source", "META-INF/sql/insert.sql");
    unit.setProperty("javax.persistence.schema-generation.database.action", "drop-and-create");
    unit.getClazz().add(Person.class.getName());
    return unit;
  }

  @Module
  public EjbJar beans() {
    EjbJar ejbJar = new EjbJar("movie-beans");
    ejbJar.addEnterpriseBean(new StatelessBean(PersonRepository.class));
    return ejbJar;
  }

  @Configuration
  public Properties config() throws Exception {
    Properties p = new Properties();
    p.put("movieDatabase", "new://Resource?type=DataSource");
    p.put("movieDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
    p.put("movieDatabase.JdbcUrl", "jdbc:hsqldb:mem:moviedb");
    return p;
  }

  @EJB
  private PersonRepository personRepository;

  @Test
  public void whenFindAll_thenGetTwoPersons() {
    Stream<Person> personsStream = personRepository.findAll();

    List<Person> persons = personsStream.collect(Collectors.toList());

    assertThat(persons).hasSize(2).extracting(person -> person.getName()).contains("Arjan");
  }

}
