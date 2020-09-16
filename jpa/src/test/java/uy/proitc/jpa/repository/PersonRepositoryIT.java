package uy.proitc.jpa.repository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.StatelessBean;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.testing.Configuration;
import org.apache.openejb.testing.Module;
import org.apache.openejb.testing.SingleApplicationComposerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import uy.proitc.jpa.entity.Person;

//@RunWith(ApplicationComposer.class)
@RunWith(SingleApplicationComposerRunner.class)
public class PersonRepositoryIT {

  @EJB
  private PersonRepository personRepository;

  @Test
  public void whenFindAll_thenGetTwoPersons() {
    List<Person> persons = personRepository.findAll();

    assertThat(persons).hasSize(2).extracting(Person::getName).contains("Arjan");
  }


  @Module
  public PersistenceUnit persistence() {
    PersistenceUnit unit = new PersistenceUnit("TEST_PU");
    unit.setJtaDataSource("movieDatabase");
    unit.setNonJtaDataSource("movieDatabaseUnmanaged");
    unit.setProvider(org.hibernate.jpa.HibernatePersistenceProvider.class);
    unit.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    unit.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
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
}
