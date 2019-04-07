/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.M_Informe_Servicio;
import modelo.M_Cliente;
import vista.V_Informe_Servicio;

/**
 *
 * @author KAKU
 */
public class C_Informe_Servicio implements ActionListener {
    private M_Informe_Servicio modelo_informe_servicio;
    private M_Cliente modelo_cliente;
    private V_Informe_Servicio vista_informe_servicio;

    public C_Informe_Servicio() {
        this.modelo_informe_servicio = new M_Informe_Servicio();
        this.modelo_cliente = new M_Cliente();
        this.vista_informe_servicio = new V_Informe_Servicio();
        
        initComponent();
    }
    
    public void initComponent(){
        this.vista_informe_servicio.btn_registrar.addActionListener(this);
        this.vista_informe_servicio.btn_editar.addActionListener(this);
        this.vista_informe_servicio.btn_eliminar.addActionListener(this);
        this.vista_informe_servicio.btn_limpiar.addActionListener(this);
        this.vista_informe_servicio.selector_cliente.addActionListener(this);
        
        
        this.vista_informe_servicio.setVisible(true);
        actualizarVista();
        
        
    }
    
    private void actualizarVista(){
        vista_informe_servicio.actualizarTabla(modelo_informe_servicio.getInformes());
        vista_informe_servicio.cargarSelector(modelo_cliente.getClientesAsc());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Registrar 
        if (e.getSource() == vista_informe_servicio.btn_registrar) {
            modelo_informe_servicio.setId(Integer.parseInt(vista_informe_servicio.txt_id.getText()));
            modelo_informe_servicio.setFecha_recepcion(vista_informe_servicio.txt_fecha_recepcion.getText());
            modelo_informe_servicio.setCosto_total(Float.parseFloat(vista_informe_servicio.txt_costo_total.getText()));
            modelo_informe_servicio.setEstado(vista_informe_servicio.txt_estado.getText());
            
            String tipo[] = vista_informe_servicio.selector_cliente.getSelectedItem().toString().split(",");
            modelo_informe_servicio.setCliente_id(Integer.parseInt(tipo[0]));
            
            if (modelo_informe_servicio.registrar()) {
                JOptionPane.showMessageDialog(null, "Se registro un equipo");
            }else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar un equipo");
            }
            vista_informe_servicio.limpiarCampos();
            actualizarVista();
        } else {
            // EDITAR
            if (e.getSource() == vista_informe_servicio.btn_editar) {
                modelo_informe_servicio.setId(Integer.parseInt(vista_informe_servicio.txt_id.getText()));
                modelo_informe_servicio.setFecha_recepcion(vista_informe_servicio.txt_fecha_recepcion.getText());
                                 
                String tipo[] = vista_informe_servicio.selector_cliente.getSelectedItem().toString().split(",");
                modelo_informe_servicio.setCliente_id(Integer.parseInt(tipo[0]));
                
                if (modelo_informe_servicio.editar()) {
                    JOptionPane.showMessageDialog(null, "Se edito un equipo");
                }else {
                    JOptionPane.showMessageDialog(null, "No se pudo editar un equipo");
                }
                vista_informe_servicio.limpiarCampos();
                actualizarVista();
            } else {
                // ELIMINAR
                if (e.getSource() == vista_informe_servicio.btn_eliminar) {
                    modelo_informe_servicio.setId(Integer.parseInt(vista_informe_servicio.txt_id.getText()));

                    if (modelo_informe_servicio.eliminar()) {
                        JOptionPane.showMessageDialog(null, "Se elimino un equipo");
                    }else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar un equipo");
                    }
                    vista_informe_servicio.limpiarCampos();
                    actualizarVista();
                } else {
                    if (e.getSource() == vista_informe_servicio.btn_limpiar) {
                        vista_informe_servicio.limpiarCampos();
                    }
                }
            }
        }
        
    }
    
    public static void main(String[] args) {
        C_Informe_Servicio cc = new C_Informe_Servicio();
    }
    
}
