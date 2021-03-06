package uy.proitc.jpa.entity;

import static uy.proitc.jpa.entity.Person.findById;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(name = findById, query = "select p from Person p where p.id=:id")
public class Person {

  public static final String findById = "Person.findById";

  @Id
  @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
  private Integer id;

  @Version
  private Integer version;

  @NotNull
  @Size(min = 3, max = 40, message = "{prod.name}")
  private String name;

  @NotNull
  private String address;

  @Past
  private LocalDate birthday;

  private Location location;

  public Person() {
  }

  public Person(Integer id, Integer version,
      @NotNull @Size(min = 3, max = 40, message = "{prod.name}") String name,
      @NotNull String address, @Past LocalDate birthday, Location location) {
    this.id = id;
    this.version = version;
    this.name = name;
    this.address = address;
    this.birthday = birthday;
    this.location = location;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  @Override
  public String toString() {
    return "Person{" +
           "id=" + id +
           ", version=" + version +
           ", name='" + name + '\'' +
           ", address='" + address + '\'' +
           ", birthday=" + birthday + '\'' +
           ", location=" + location +
           '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(id, person.id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}