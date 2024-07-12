/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        int status;
        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)");
            
            prep.setString(1,produto.getNome());
            prep.setInt(2,produto.getValor());
            prep.setString(3,produto.getStatus());
            
            status = prep.executeUpdate();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        conn = new conectaDAO().connectDB();
        
        try {
        String sqlFiltro = "select * from produtos";
             
             prep = conn.prepareStatement(sqlFiltro);
             
             ResultSet resultset = prep.executeQuery();
             
             while (resultset.next()) {
                 ProdutosDTO produtoEncontrado = new ProdutosDTO();
                 produtoEncontrado.setId(resultset.getInt("id"));
                 produtoEncontrado.setNome(resultset.getString("nome"));
                 produtoEncontrado.setValor(resultset.getInt("valor"));
                 produtoEncontrado.setStatus(resultset.getString("status"));
                 listagem.add(produtoEncontrado);
             }
             
             return listagem;
         } catch (SQLException ex) {
             System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
         }
        
    }
    
    public void venderProduto(Integer id) {
    conn = new conectaDAO().connectDB();
    
    try { 
        String query = "UPDATE produtos SET status = 'Vendido' WHERE id = ?"; 
        prep = conn.prepareStatement(query);
        prep.setInt(1, id); prep.executeUpdate(); 
    } catch (SQLException e) 
    { e.printStackTrace(); } 
}
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    
        conn = new conectaDAO().connectDB();
        
    try {
        String query = "SELECT * FROM produtos WHERE status = 'Vendido'";
        prep = conn.prepareStatement(query);
        resultset = prep.executeQuery();
        
        while(resultset.next()) {
            int id = resultset.getInt("id");
            String nome = resultset.getString("nome");
            int valor = resultset.getInt("valor");
            String status = resultset.getString("status");
            
            ProdutoDTO produto = new ProdutoDTO(id, nome, valor, status);
            produtos.add(produto);
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return produtos;
}
        
}

