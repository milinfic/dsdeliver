package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import entities.Order;
import entities.OrderStatus;
import entities.Product;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DB.getConnection();
	
		Statement st = conn.createStatement();
			
		ResultSet rs = st.executeQuery("select * from tb_order");
			
		while (rs.next()) {
			
			Order order = instantiateOrder(rs);		
			
			
			System.out.println(order);
		}
	}
	
	private static Product instantiateProduct(ResultSet rs) throws SQLException {
		Product product = new Product();
		product.setId(rs.getLong("id"));
		product.setDescription(rs.getString("description"));
		product.setName(rs.getString("name"));
		product.setImageUri(rs.getString("image_uri"));
		product.setPrice(rs.getDouble("price"));
		
		return product;
	}
	
	private static Order instantiateOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong("id"));
		order.setLatitude(rs.getDouble("latitude"));
		order.setLongitude(rs.getDouble("longitude"));
		order.setMoment(rs.getTimestamp("moment").toInstant());
		//para pegar o "momento" é necessário utilizar o método getTimestamp com o calor do atributo gravado no bd
		//e depois converter para .toInstant()
		order.setStatus(OrderStatus.values()[rs.getInt("status")]);
		//OrderStatus.values()[*] em * você escreve os itens que será retornado do bd, este metódio irá converter em
		//no enum que foi criado
		
		return order;
	}
	
	
	
}






















