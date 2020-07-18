package Crud;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.swing.JOptionPane;

@Entity
@Table(name = "aspirantes")
public class DatosAspirantes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "idAspirante", nullable = false)
    private Integer idAspirante;

    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = false)
    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;

    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;

    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;

    @Transient
    private String aspiranteInfo;

    @PrePersist
    @PreUpdate
    private void validarFormulario() {
        if (nombre == null || "".equals(nombre)) {
            JOptionPane.showMessageDialog(null, "Debe De Llenar Todos Los Campos", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Nombre No Valido");
        } else if (apellidos == null || "".equals(apellidos)) {
            JOptionPane.showMessageDialog(null, "Debe De Llenar Todos Los Campos", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Apellidos No Validos");
        } else if (direccion == null || "".equals(direccion)) {
            JOptionPane.showMessageDialog(null, "Debe De Llenar Todos Los Campos", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Apellidos No Validos");
        } else if (correo == null || "".equals(correo)) {
            JOptionPane.showMessageDialog(null, "Debe De Llenar Todos Los Campos", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Apellidos No Validos");
        }
        if (idAspirante == null || "".equals(idAspirante)) {
            JOptionPane.showMessageDialog(null, "Debe De Llenar Todos Los Campos", "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Apellidos No Validos");
        }
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    public void info() {
        aspiranteInfo = idAspirante + ", " + "\n"
                + "Nombre: " + nombre + ", " + "\n"
                + "Apellidos: " + apellidos + ", " + "\n"
                + "Direccion: " + direccion + ", " + "\n"
                + "Correo: " + correo + ", " + "\n"
                + "Telefono: " + telefono + "\n";
    }

    public DatosAspirantes() {
    }

    public DatosAspirantes(Integer idAspirante, String nombre, String apellidos, String direccion, String correo, String telefono) {
        this.idAspirante = idAspirante;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Integer getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(Integer idAspirante) {
        this.idAspirante = idAspirante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAspiranteInfo() {
        return aspiranteInfo;
    }

    public void setAspiranteInfo(String aspiranteInfo) {
        this.aspiranteInfo = aspiranteInfo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAspirante != null ? idAspirante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosAspirantes)) {
            return false;
        }
        DatosAspirantes other = (DatosAspirantes) object;
        if ((this.idAspirante == null && other.idAspirante != null) || (this.idAspirante != null && !this.idAspirante.equals(other.idAspirante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Crud.DatosAspirantes[ id=" + idAspirante + " ]";
    }
}