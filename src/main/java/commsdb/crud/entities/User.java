package commsdb.crud.entities;


import commsdb.crud.StringSetAttributeConverter;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;
@Builder
@Entity
@Table(name="comms_user")
@NoArgsConstructor
@AllArgsConstructor
@UserDefinition
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_generator")
    @SequenceGenerator(name="user_generator", sequenceName = "comms_user_id_seq")
    public Long id;

    @Password
    public String password;
    public String email;
    public Timestamp lastLogin;
    @Username
    public String userName;
    public String firstName;
    public String lastName;
    @Convert(converter = StringSetAttributeConverter.class)
    @Column(name = "roles", nullable = false)
    @Roles
    public Set<String> roles;
    public static User findByName(String userName){
        return find("userName", userName).firstResult(); //optional?
    }

    public static void add(String username, String firstName, String lastName, String email, String password, Set<String> roles) {
        User user =  User.builder().userName(username).firstName(firstName)
                .lastName(lastName).email(email).password(BcryptUtil.bcryptHash(password)).roles(roles).build();

        user.persist();
    }

    /*
//
//    @Column(name="last_name")
    public static List<Person> findAlive(){
        return list("status", Status.Alive);
    }

    public static void deleteStefs(){
        delete("name", "Stef");
    }




    import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

// creating a person
Person person = new Person();
person.name = "Stef";
person.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
person.status = Status.Alive;

// persist it
person.persist();

// note that once persisted, you don't need to explicitly save your entity: all
// modifications are automatically persisted on transaction commit.

// check if it is persistent
if(person.isPersistent()){
    // delete it
    person.delete();
}

// getting a list of all Person entities
List<Person> allPersons = Person.listAll();

// finding a specific person by ID
person = Person.findById(personId);

// finding a specific person by ID via an Optional
Optional<Person> optional = Person.findByIdOptional(personId);
person = optional.orElseThrow(() -> new NotFoundException());

// finding all living persons
List<Person> livingPersons = Person.list("status", Status.Alive);

// counting all persons
long countAll = Person.count();

// counting all living persons
long countAlive = Person.count("status", Status.Alive);

// delete all living persons
Person.delete("status", Status.Alive);

// delete all persons
Person.deleteAll();

// delete by id
boolean deleted = Person.deleteById(personId);

// set the name of all living persons to 'Mortal'
Person.update("name = 'Mortal' where status = ?1", Status.Alive);
 */
}
 