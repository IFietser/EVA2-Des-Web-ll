package cl.ipss.saborgourmet.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Mesa {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;
    private int numero;
    private int asientos;
    private boolean disponible;
    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "mesa",
        cascade = {CascadeType.ALL}
    )
    private List<Reservacion> reservaciones;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public int getAsientos() {
        return asientos;
    }
    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }
    public boolean isDisponible() {
        return disponible;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public List<Reservacion> getReservaciones() {
        return reservaciones;
    }
    public void setReservaciones(List<Reservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }

    
}
