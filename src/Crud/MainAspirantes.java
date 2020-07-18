package Crud;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainAspirantes extends JFrame implements ActionListener {

    private JLabel lblId, lblNombre, lblApellidos, lblDireccion, lblCorreo, lblTelefono;
    private JTextField txtId, txtNombre, txtApellidos, txtDireccion, txtCorreo, txtTelefono;
    private JButton btnInsertar, btnActualizar, btnEliminar, btnBuscar;
    private JPanel panel;
    Font estilo = new Font("Arial", Font.ITALIC, 16);

    DatosAspirantes da = new DatosAspirantes();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRUDJPAPU");
    EntityManager em = emf.createEntityManager(); 
    EntityTransaction tx = em.getTransaction();

    public MainAspirantes() {
        super("Registro Aspirante");
        initComponents();
    }

    public void initComponents() {
        panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);

        ImageIcon guardar = new ImageIcon(getClass().getResource("/Imagenes/insertar.png"));
        btnInsertar = new JButton("Insertar");
        btnInsertar.setIcon(guardar);
        btnInsertar.setFont(estilo);
        btnInsertar.setBounds(0, 0, 130, 30);
        panel.add(btnInsertar);
        btnInsertar.addActionListener(this);

        ImageIcon modificar = new ImageIcon(getClass().getResource("/Imagenes/modificar.png"));
        btnActualizar = new JButton("Modificar");
        btnActualizar.setIcon(modificar);
        btnActualizar.setFont(estilo);
        btnActualizar.setBounds(130, 0, 140, 30);
        panel.add(btnActualizar);
        btnActualizar.addActionListener(this);

        ImageIcon eliminar = new ImageIcon(getClass().getResource("/Imagenes/eliminar.png"));
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setIcon(eliminar);
        btnEliminar.setFont(estilo);
        btnEliminar.setBounds(270, 0, 140, 30);
        panel.add(btnEliminar);
        btnEliminar.addActionListener(this);

        ImageIcon insertar = new ImageIcon(getClass().getResource("/Imagenes/buscar.png"));
        btnBuscar = new JButton("Buscar");
        btnBuscar.setIcon(insertar);
        btnBuscar.setFont(estilo);
        btnBuscar.setBounds(410, 0, 120, 30);
        panel.add(btnBuscar);
        btnBuscar.addActionListener(this);

        lblId = new JLabel("Clave: ");
        lblId.setBounds(10, 60, 90, 30);
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        lblId.setFont(estilo);
        panel.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(140, 60, 120, 30);
        txtId.setFont(estilo);
        panel.add(txtId);

        lblNombre = new JLabel("Nombre(s): ");
        lblNombre.setBounds(10, 100, 120, 30);
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setFont(estilo);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 100, 270, 30);
        txtNombre.setFont(estilo);
        panel.add(txtNombre);

        lblApellidos = new JLabel("Apellido(s): ");
        lblApellidos.setBounds(10, 140, 120, 30);
        lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
        lblApellidos.setFont(estilo);
        panel.add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(140, 140, 270, 30);
        txtApellidos.setFont(estilo);
        panel.add(txtApellidos);

        lblDireccion = new JLabel("Direccion: ");
        lblDireccion.setBounds(10, 180, 140, 30);
        lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
        lblDireccion.setFont(estilo);
        panel.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(140, 180, 270, 30);
        txtDireccion.setFont(estilo);
        panel.add(txtDireccion);

        lblCorreo = new JLabel("Correo: ");
        lblCorreo.setBounds(10, 220, 140, 30);
        lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
        lblCorreo.setFont(estilo);
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(140, 220, 270, 30);
        txtCorreo.setFont(estilo);
        panel.add(txtCorreo);

        lblTelefono = new JLabel("Telefono: ");
        lblTelefono.setBounds(10, 260, 140, 30);
        lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
        lblTelefono.setFont(estilo);
        panel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(140, 260, 270, 30);
        txtTelefono.setFont(estilo);
        panel.add(txtTelefono);
    }

    public void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnInsertar) {
            da.setIdAspirante(Integer.parseInt(txtId.getText()));
            da.setNombre(txtNombre.getText());
            da.setApellidos(txtApellidos.getText());
            da.setDireccion(txtDireccion.getText());
            da.setCorreo(txtCorreo.getText());
            da.setTelefono(txtTelefono.getText());
            tx.begin();
            try {
                em.persist(da);
                tx.commit();
                JOptionPane.showMessageDialog(null, "Aspirante: " + da.getAspiranteInfo() + " Agregado Correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                tx.rollback();
                JOptionPane.showMessageDialog(null, "No Se Pudo Relizar La Peticion", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == btnActualizar) {
            da = em.find(DatosAspirantes.class, Integer.parseInt(txtId.getText()));
            da.setNombre(txtNombre.getText());
            da.setApellidos(txtApellidos.getText());
            da.setDireccion(txtDireccion.getText());
            da.setCorreo(txtCorreo.getText());
            da.setTelefono(txtTelefono.getText());
            tx.begin();
            try {
                em.persist(da);
                tx.commit();
                JOptionPane.showMessageDialog(null, "Aspirante " + da.getAspiranteInfo() + "Actualizado Correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                tx.rollback();
                JOptionPane.showMessageDialog(null, "No Se Pudo Relizar La Peticion", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == btnEliminar) {
            da = em.find(DatosAspirantes.class, Integer.parseInt(txtId.getText()));
            tx.begin();
            try {
                em.remove(da);
                tx.commit();
                JOptionPane.showMessageDialog(null, "Aspirante " + da.getAspiranteInfo() + "Eliminado Correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
            } catch (Exception ex) {
                tx.rollback();
                JOptionPane.showMessageDialog(null, "No Se Pudo Relizar La Peticion", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == btnBuscar) {
            da = em.find(DatosAspirantes.class, Integer.parseInt(txtId.getText()));
            txtNombre.setText(da.getNombre());
            txtApellidos.setText(da.getApellidos());
            txtDireccion.setText(da.getDireccion());
            txtCorreo.setText(da.getCorreo());
            txtTelefono.setText(da.getTelefono());
            JOptionPane.showMessageDialog(null, "Aspirante " + da.getAspiranteInfo() + "Correcto", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainAspirantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainAspirantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainAspirantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainAspirantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        MainAspirantes app = new MainAspirantes();

        app.setBounds(0, 0, 540, 400);
        app.setLocationRelativeTo(null);
        app.setResizable(false);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}