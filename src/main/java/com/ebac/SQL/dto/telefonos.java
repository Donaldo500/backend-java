package com.ebac.SQL.dto;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "telefonos")
public class telefonos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTelefono;

    @Column(name = "numero")
    private String numero;

    @Column(name = "tipo")
    private String tipo;

    public int getIdTelefono() {
        return idTelefono;
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setIdTelefono(int idTelefono) {
        this.idTelefono = idTelefono;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Deprecated
    public String toString() {
        return "telefonos{idTelefono=" + idTelefono + ", numero='" + numero + "', tipo='" + tipo + "'}";
    }
}
