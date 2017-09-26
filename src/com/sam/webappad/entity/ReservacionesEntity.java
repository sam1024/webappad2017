package com.sam.webappad.entity;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** @author sam **/

@Entity
@Table(name = "reservaciones")
public class ReservacionesEntity {

    @Id
    //@SequenceGenerator(name = "seq", sequenceName = "SEQUENCE_RESERVACIONES")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq_new_reservacion", allocationSize = 1)
    //ANTES DE PONER allocationSize = 1 CUANDO GUARDABA EN LA DB EL ID DE LA TABLA TOMABA VALORES NEGATIVOS E INCOHERENTES
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private int id;
    private Date fecha;
    private String evento;
    private String responsable;
    private int cancelada;
    private int tipo;
    private Date fecha_creacion;
    private int id_repetir;
    private String no_participantes;
    private String requerimientos;
    
    @ManyToOne
    /*SE DEBE PONER insertable = true, updatable = true, SI NO SE HACE ASÍ LA INFORMACIÓN NO SE GUARDARA EN DB*/
    @JoinColumn(name = "id_recurso", insertable = true, updatable = true)
    private RecursosEntity recursos_entity;
    
    /*ESPECIFICAMOS QUE EXISTE UNA RELACION UNO A UNO*/
    @ManyToOne
    /*@JoinColumn ESPECIFICA LA LLAVE FORANEA, EL NOMBRE TIENE QUE SER IGUAL AL QUE TENEMOS EN LA TABLA RESERVACIONES*/
    @JoinColumn(name = "id_hora_inicio", insertable = true, updatable = true)
    private HorasEntity horas_entity_id_horaini;
    
    @ManyToOne
    /*@JoinColumn ESPECIFICA LA LLAVE FORANEA*/
    @JoinColumn(name = "id_hora_fin", insertable = true, updatable = true)
    private HorasEntity horas_entity_id_horafin;
    
    @ManyToOne
    //@JoinColumn(name = "id_usuario")
    @JoinColumn(name = "id_usuario", insertable = true, updatable = true)
    private UsuariosEntity usuarios_entity;
    
    @ManyToOne
    @JoinColumn(name = "id_acomodo", insertable = true, updatable = true)
    private AcomodosEntity acomodos_entity;

    public ReservacionesEntity() { }

    public ReservacionesEntity(int id, Date fecha, String evento, String responsable, int cancelada, int tipo,
			Date fecha_creacion, int id_repetir, String no_participantes, String requerimientos) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.evento = evento;
		this.responsable = responsable;
		this.cancelada = cancelada;
		this.tipo = tipo;
		this.fecha_creacion = fecha_creacion;
		this.id_repetir = id_repetir;
		this.no_participantes = no_participantes;
		this.requerimientos = requerimientos;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int getCancelada() {
        return cancelada;
    }

    public void setCancelada(int cancelada) {
        this.cancelada = cancelada;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public RecursosEntity getRecursos_entity() {
        return recursos_entity;
    }

    public void setRecursos_entity(RecursosEntity recursos_entity) {
        this.recursos_entity = recursos_entity;
    }

    public HorasEntity getHoras_entity_id_horaini() {
        return horas_entity_id_horaini;
    }

    public void setHoras_entity_id_horaini(HorasEntity horas_entity_id_horaini) {
        this.horas_entity_id_horaini = horas_entity_id_horaini;
    }

    public HorasEntity getHoras_entity_id_horafin() {
        return horas_entity_id_horafin;
    }

    public void setHoras_entity_id_horafin(HorasEntity horas_entity_id_horafin) {
        this.horas_entity_id_horafin = horas_entity_id_horafin;
    }

    public UsuariosEntity getUsuarios_entity() {
        return usuarios_entity;
    }

    public void setUsuarios_entity(UsuariosEntity usuarios_entity) {
        this.usuarios_entity = usuarios_entity;
    }
    
    public int getId_repetir() {
		return id_repetir;
	}

	public void setId_repetir(int id_repetir) {
		this.id_repetir = id_repetir;
	}	
	
	public AcomodosEntity getAcomodos_entity() {
		return acomodos_entity;
	}

	public void setAcomodos_entity(AcomodosEntity acomodos_entity) {
		this.acomodos_entity = acomodos_entity;
	}
	
	public String getNo_participantes() {
		return no_participantes;
	}

	public void setNo_participantes(String no_participantes) {
		this.no_participantes = no_participantes;
	}

	public String getRequerimientos() {
		return requerimientos;
	}

	public void setRequerimientos(String requerimientos) {
		this.requerimientos = requerimientos;
	}

	@Override
	public String toString() {
		return "ReservacionesEntity [id=" + id + ", fecha=" + fecha + ", evento=" + evento + ", responsable="
				+ responsable + ", cancelada=" + cancelada + ", tipo=" + tipo + ", fecha_creacion=" + fecha_creacion
				+ ", id_repetir=" + id_repetir + ", no_participantes=" + no_participantes + ", requerimientos="
				+ requerimientos + ", recursos_entity=" + recursos_entity + ", horas_entity_id_horaini="
				+ horas_entity_id_horaini + ", horas_entity_id_horafin=" + horas_entity_id_horafin
				+ ", usuarios_entity=" + usuarios_entity + ", acomodos_entity=" + acomodos_entity + "]";
	}

		

}

///*ESPECIFICAMOS QUE EXISTE UNA RELACION UNO A UNO*/
//@OneToOne(fetch = FetchType.LAZY)
///*@JoinColumn ESPECIFICA LA LLAVE FORANEA, EL NOMBRE TIENE QUE SER IGUAL AL QUE TENEMOS EN LA TABLA RESERVACIONES*/
//@JoinColumn(name = "id_hora_inicio", insertable = true, updatable = true)
//private HorasEntity horas_entity_id_horaini;
//
//@OneToOne (fetch = FetchType.LAZY) //, cascade = {CascadeType.ALL})
///*@JoinColumn ESPECIFICA LA LLAVE FORANEA*/
//@JoinColumn(name = "id_hora_fin", insertable = true, updatable = true)
//private HorasEntity horas_entity_id_horafin;