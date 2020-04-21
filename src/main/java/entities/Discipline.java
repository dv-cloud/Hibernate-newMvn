package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long idDiscipline;

    @Column
    private String name;

    @OneToMany
    @JoinColumn
    private Set<User> members;

    @OneToOne
    @JoinColumn
    private User HeadOfDiscipline;


    public long getId() {
        return idDiscipline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    public User getHeadOfDiscipline() {
        return HeadOfDiscipline;
    }

    public void setHeadOfDiscipline(User headOfDiscipline) {
        HeadOfDiscipline = headOfDiscipline;
    }

    public Discipline() {
    }

    public Discipline(String name, Set<User> members, User headOfDiscipline) {
        this.name = name;
        this.members = members;
        HeadOfDiscipline = headOfDiscipline;
    }
}
