package com.sam.webappad.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/** @author sam **/

@Entity
@Table(name = "horas")
public class HorasEntity {
    
    @Id
    @Column(name = "id")
    private int id;
    private String hora;
    
    //@OneToOne(mappedBy = "horas_entity_id_horaini", cascade = CascadeType.REMOVE)
    /*CADA VEZ QUE ELIMINEMOS UNA HORA VA A BORRAR EN CASCADA LA RESERVACION LIGADA AL MISMO GRACIAS A cascade = CascadeType.REMOVE. EN mappedBy = "horas_entity" DEBEMOS
    PONER EL NOMBRE DEL ATRIBUTO QUE HACE POSIBLE LA RELACION EN ESTE CASO ES horas_entity, YA QUE EN ReservacionesEntity
    TENEMOS ESA PROPIEDAD DECLARADA COMO 'private HorasEntity horas_entity'*/
    @OneToMany(mappedBy = "horas_entity_id_horaini")
    private Set<ReservacionesEntity> reservaciones_entity_id_horaini;
    
    //@OneToOne(mappedBy = "horas_entity_id_horafin", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OneToMany(mappedBy = "horas_entity_id_horafin")
    private Set<ReservacionesEntity> reservaciones_entity_id_horafin;

    public HorasEntity() { }

    public HorasEntity(String hora) {
        this.hora = hora;
    }

    public int getId_horas() {
        return id;
    }

    public void setId_horas(int id_horas) {
        this.id = id_horas;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Set<ReservacionesEntity> getReservaciones_entity_id_horaini() {
        return reservaciones_entity_id_horaini;
    }

    public void setReservaciones_entity_id_horaini(Set<ReservacionesEntity> reservaciones_entity_id_horaini) {
        this.reservaciones_entity_id_horaini = reservaciones_entity_id_horaini;
    }

    public Set<ReservacionesEntity> getReservaciones_entity_id_horafin() {
        return reservaciones_entity_id_horafin;
    }

    public void setReservaciones_entity_id_horafin(Set<ReservacionesEntity> reservaciones_entity_id_horafin) {
        this.reservaciones_entity_id_horafin = reservaciones_entity_id_horafin;
    }
    
}

//@OneToOne(mappedBy = "horas_entity_id_horaini", cascade = CascadeType.ALL, orphanRemoval = true,
//  fetch = FetchType.LAZY)
//private ReservacionesEntity reservaciones_entity_id_horaini;
//
////@OneToOne(mappedBy = "horas_entity_id_horafin", cascade = CascadeType.REMOVE, orphanRemoval = true)
//@OneToOne(mappedBy = "horas_entity_id_horafin", cascade = {CascadeType.ALL}, orphanRemoval = true,
//fetch = FetchType.LAZY)
//private ReservacionesEntity reservaciones_entity_id_horafin;