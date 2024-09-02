/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.util.Conexion;
import com.mysql.cj.result.Row;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.text.Document;



/**
 *
 * @author hernan
 */
public class Print extends javax.swing.JFrame {

    /**
     * Creates new form Imprimir
     */
    public Print() {
        initComponents();
        setLocationRelativeTo(null); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PDF = new javax.swing.JButton();
        TXT = new javax.swing.JButton();
        EXCEL = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(246, 243, 235));

        PDF.setBackground(new java.awt.Color(232, 224, 194));
        PDF.setFont(new java.awt.Font("Bradley Hand", 0, 24)); // NOI18N
        PDF.setText("PDF");
        PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PDFActionPerformed(evt);
            }
        });

        TXT.setBackground(new java.awt.Color(232, 224, 194));
        TXT.setFont(new java.awt.Font("Bradley Hand", 0, 24)); // NOI18N
        TXT.setText("TXT");
        TXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTActionPerformed(evt);
            }
        });

        EXCEL.setBackground(new java.awt.Color(232, 224, 194));
        EXCEL.setFont(new java.awt.Font("Bradley Hand", 0, 24)); // NOI18N
        EXCEL.setText("Excel");
        EXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EXCELActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bradley Hand", 0, 48)); // NOI18N
        jLabel2.setText("Print here!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(PDF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(EXCEL)
                .addGap(124, 124, 124)
                .addComponent(TXT)
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(164, 164, 164))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PDF)
                    .addComponent(TXT)
                    .addComponent(EXCEL))
                .addGap(87, 87, 87))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PDFActionPerformed
        Connection conexion = null;
    try {
        // Obtener la ruta del directorio de descargas
        String userHome = System.getProperty("user.home");
        String downloadDir = userHome + "/Downloads";
        String filePath = downloadDir + "/output.pdf"; // Nombre del archivo

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        document.add(new Paragraph("Shop Statistics"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.addCell("Shop ID");
        table.addCell("Manager ID");
        table.addCell("Manager Name");
        table.addCell("Total Sales");

        conexion = Conexion.getInstance().conectar();
        String query = "SELECT sh.id AS shop_id, sh.manager_id AS manager_id, emp.name AS manager_name, SUM(ord.total_value) AS total_sales " +
                       "FROM Shops sh " +
                       "JOIN Orders ord ON sh.id = ord.shop_id " +
                       "JOIN Employees emp ON sh.manager_id = emp.id " +
                       "GROUP BY sh.id, sh.manager_id, emp.name;";
        Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            table.addCell(String.valueOf(rs.getInt("shop_id")));
            table.addCell(String.valueOf(rs.getInt("manager_id")));
            table.addCell(rs.getString("manager_name"));
            table.addCell(String.valueOf(rs.getDouble("total_sales")));
        }

        document.add(table);
        document.close();
    } catch (com.itextpdf.text.DocumentException | IOException | SQLException e) {
        e.printStackTrace();
    } finally {
        if (conexion != null) {
            Conexion.getInstance().cerrarConexion(conexion);
        }
    }
    }//GEN-LAST:event_PDFActionPerformed

    private void TXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTActionPerformed
        Connection conexion = null;
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
        conexion = Conexion.getInstance().conectar();
        String query = "SELECT sh.id AS shop_id, sh.manager_id AS manager_id, emp.name AS manager_name, SUM(ord.total_value) AS total_sales " +
                       "FROM Shops sh " +
                       "JOIN Orders ord ON sh.id = ord.shop_id " +
                       "JOIN Employees emp ON sh.manager_id = emp.id " +
                       "GROUP BY sh.id, sh.manager_id, emp.name;";
        Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        writer.write("Shop ID\tManager ID\tManager Name\tTotal Sales");
        writer.newLine();

        while (rs.next()) {
            writer.write(rs.getInt("shop_id") + "\t" + rs.getInt("manager_id") + "\t" + rs.getString("manager_name") + "\t" + rs.getDouble("total_sales"));
            writer.newLine();
        }
    } catch (IOException | SQLException e) {
        e.printStackTrace();
    } finally {
        Conexion.getInstance().cerrarConexion(conexion);
    }
    }//GEN-LAST:event_TXTActionPerformed

    private void EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EXCELActionPerformed
        Connection conexion = null;
    try (Workbook workbook = new XSSFWorkbook()) {
        Sheet sheet = workbook.createSheet("Shop Statistics");

        // Usa el Row de Apache POI, no el de MySQL
        org.apache.poi.ss.usermodel.Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Shop ID");
        header.createCell(1).setCellValue("Manager ID");
        header.createCell(2).setCellValue("Manager Name");
        header.createCell(3).setCellValue("Total Sales");

        conexion = Conexion.getInstance().conectar();
        String query = "SELECT sh.id AS shop_id, sh.manager_id AS manager_id, emp.name AS manager_name, SUM(ord.total_value) AS total_sales " +
                       "FROM Shops sh " +
                       "JOIN Orders ord ON sh.id = ord.shop_id " +
                       "JOIN Employees emp ON sh.manager_id = emp.id " +
                       "GROUP BY sh.id, sh.manager_id, emp.name;";
        Statement stmt = conexion.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        int rowNum = 1;
        while (rs.next()) {
            org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rs.getInt("shop_id"));
            row.createCell(1).setCellValue(rs.getInt("manager_id"));
            row.createCell(2).setCellValue(rs.getString("manager_name"));
            row.createCell(3).setCellValue(rs.getDouble("total_sales"));
        }

        try (FileOutputStream fileOut = new FileOutputStream("output.xlsx")) {
            workbook.write(fileOut);
        }
    } catch (IOException | SQLException e) {
        e.printStackTrace();
    } finally {
        if (conexion != null) {
            Conexion.getInstance().cerrarConexion(conexion);
        }
    }
    }//GEN-LAST:event_EXCELActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(Print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Print().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EXCEL;
    private javax.swing.JButton PDF;
    private javax.swing.JButton TXT;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
