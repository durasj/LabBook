package sk.upjs.paz1c.persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Project;

public class MysqlItemDAO implements ItemDAO {

	private JdbcTemplate jdbcTemplate;

	public MysqlItemDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addItem(Item item) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("item");
		insert.usingGeneratedKeyColumns("id_item");
		insert.usingColumns("name", "quantity", "available", "laboratory_id_laboratory");

		Map<String, Object> values = new HashMap<>();
		values.put("name", item.getName());
		values.put("quantity", item.getQuantity());
		values.put("available", item.isAvailable());
		values.put("laboratory_id_laboratory", item.getLaboratoryID());

		item.setItemID(insert.executeAndReturnKey(values).longValue());
	}

	@Override
	public void saveItem(Item item) {
		if (item == null)
			return;
		if (item.getItemID() == null) { // CREATE
			addItem(item);
		} else { // UPDATE
			String sql = "UPDATE item SET " + "name = ?, quantity = ?, available = ?, laboratory_id_laboratory = ? "
					+ "WHERE id_item = ?";
			jdbcTemplate.update(sql, item.getName(), item.getQuantity(), item.isAvailable(), item.getLaboratoryID(),
					item.getItemID());
		}
	}

	@Override
	public List<Item> getAll() {
		String sql = "SELECT id_item, name, quantity, available, laboratory_id_laboratory " + "FROM lab_book.item";
		return jdbcTemplate.query(sql, new RowMapper<Item>() {

			@Override
			public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
				Item item = new Item();
				item.setItemID(rs.getLong("id_item"));
				item.setName(rs.getString("name"));
				item.setQuantity(rs.getInt("quantity"));
				item.setAvailable(rs.getBoolean("available"));
				item.setLaboratoryID(rs.getLong("laboratory_id_laboratory"));
				return item;
			}
		});
	}

	@Override
	public void deleteItem(Item item) {
		String sql = "DELETE FROM item WHERE id_item = " + item.getItemID();
		jdbcTemplate.update(sql);
	}

	// FIXME urobit test
	@Override
	public Item getByID(Long id) {
		String sql = "SELECT name, quantity, available, laboratory_id_laboratory " + "FROM item "
				+ "WHERE item_id = " + id;
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Item.class));
	}

}
