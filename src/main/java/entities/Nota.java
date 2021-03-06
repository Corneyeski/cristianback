package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Nota {

    private int id;
    private String nota;

    public Nota() {}

    public Nota(String nota) {
        this.nota = nota;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nota")
    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota1 = (Nota) o;
        return id == nota1.id &&
                Objects.equals(nota, nota1.nota);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nota);
    }
}
