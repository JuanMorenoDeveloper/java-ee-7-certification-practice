package uy.proitc.jpa.repository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import javax.ejb.EJB;
import javax.validation.ConstraintViolationException;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.StatelessBean;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Configuration;
import org.apache.openejb.testing.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import uy.proitc.jpa.converter.LocationConverter;
import uy.proitc.jpa.entity.Location;
import uy.proitc.jpa.entity.Person;

@RunWith(ApplicationComposer.class)
public class PersonRepositoryUnitTest {

  @EJB
  private PersonRepository personRepository;

  @Test
  public void whenFindById_thenGetPerson() {
    Optional<Person> person = personRepository.findById(1001);

    assertThat(person).isNotEmpty();
  }

  @Test
  public void whenFindAll_thenGetTwoPersons() {
    List<Person> persons = personRepository.findAll();

    assertThat(persons).hasSizeGreaterThanOrEqualTo(2).extracting(Person::getName)
        .contains("Arjan");
  }

  @Test
  public void whenSavePersonWithInvalidDateAndAddress_thenThrowsConstraintValidationExceptions() {
    Person person = new Person(null, null, "Ad", "Address",
        LocalDate.of(LocalDate.now().getYear() + 1, 12, 12), new Location(2.3, 4.5));

    Throwable exception = catchThrowable(() -> personRepository.save(person));

    assertSoftly(softly -> {
      softly.assertThat(exception)
          .hasRootCauseExactlyInstanceOf(ConstraintViolationException.class);
      softly.assertThat(exception.getCause().getCause().getCause().getMessage())
          .contains("must be a past date").contains("prod.name");
    });
  }

  @Module
  public PersistenceUnit persistence() {
    PersistenceUnit unit = new PersistenceUnit("TEST_PU");
    unit.setJtaDataSource("personDatabase");
    unit.setNonJtaDataSource("personDatabaseUnmanaged");
    unit.setProvider(org.hibernate.jpa.HibernatePersistenceProvider.class);
    unit.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    unit.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
    unit.setProperty("javax.persistence.sql-load-script-source", "META-INF/sql/insert.sql");
    unit.setProperty("javax.persistence.schema-generation.database.action", "drop-and-create");
    unit.setProperty("hibernate.show_sql", "true");
    unit.setProperty("hibernate.format_sql", "true");
    unit.getClazz().add(Person.class.getName());
    unit.getClazz().add(LocationConverter.class.getName());
    return unit;
  }

  @Module
  public EjbJar beans() {
    EjbJar ejbJar = new EjbJar("person-beans");
    ejbJar.addEnterpriseBean(new StatelessBean(PersonRepository.class));
    return ejbJar;
  }

  @Configuration
  public Properties config() {
    Properties p = new Properties();
    p.put("personDatabase", "new://Resource?type=DataSource");
    p.put("personDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
    p.put("personDatabase.JdbcUrl", "jdbc:hsqldb:mem:moviedb");
    return p;
  }
}
