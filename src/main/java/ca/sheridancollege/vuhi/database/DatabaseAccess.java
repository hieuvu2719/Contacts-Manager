package ca.sheridancollege.vuhi.database;

import ca.sheridancollege.vuhi.beans.Contact;
import ca.sheridancollege.vuhi.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DatabaseAccess {
    @Autowired
    NamedParameterJdbcTemplate jdbc;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Long insertContact(Contact contact) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = " INSERT INTO contact (name,phoneNumber,address,email,role) " +
                "VALUES(:name,:phoneNumber,:address,:email,:role)";
        namedParameters.addValue("name",contact.getName());
        namedParameters.addValue("phoneNumber",contact.getPhoneNumber());
        namedParameters.addValue("address",contact.getAddress());
        namedParameters.addValue("email",contact.getEmail());
        namedParameters.addValue("role",contact.getRole());
        jdbc.update(query,namedParameters,generatedKeyHolder);
        return (Long) generatedKeyHolder.getKey();
    }

    public List<Contact> getAllContact (){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = " SELECT * FROM contact";
        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<>(Contact.class));
    }

    public List<Contact> getContactById(Long id){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = " SELECT * FROM contact WHERE id = :id";
        namedParameters.addValue("id",id);
        return jdbc.query(query,namedParameters,new BeanPropertyRowMapper<>(Contact.class));
    }

    public void updateContact(Contact contact){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = " UPDATE contact SET name=:name, phoneNumber=:phoneNumber, address =:address, email =:email, role =:role " +
                "WHERE id =:id";
        namedParameters.addValue("name",contact.getName());
        namedParameters.addValue("phoneNumber",contact.getPhoneNumber());
        namedParameters.addValue("address",contact.getAddress());
        namedParameters.addValue("email",contact.getEmail());
        namedParameters.addValue("role",contact.getRole());
        namedParameters.addValue("id",contact.getId());
        jdbc.update(query,namedParameters);
    }

    public void deleteContact(Long id){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "DELETE FROM contact WHERE id = :id";
        namedParameters.addValue("id",id);
        jdbc.update(query,namedParameters);
    }

    public void addUser(String email, String password){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="INSERT INTO sec_user " +
                "(email, encryptedPassword) " +
                "VALUES (:email, :password)";
        namedParameters.addValue("email",email);
        namedParameters.addValue("password",passwordEncoder.encode(password));
        jdbc.update(query,namedParameters);
    }


    public void addRole(Long userId, Long roleId){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query =" INSERT INTO user_role(userId, roleId) " +
                "VALUES(:userId, :roleId) ";
        namedParameters.addValue("userId",userId);
        namedParameters.addValue("roleId",roleId);
        jdbc.update(query,namedParameters);
    }


    public User findUserAccount(String email) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM sec_user where email=:email";
        parameters.addValue("email", email);
        ArrayList<User> users = (ArrayList<User>)jdbc.query(query, parameters, new
                BeanPropertyRowMapper<User>(User.class));
        if (users.size()>0)
            return users.get(0);
        else
            return null;


    }


    public List<String> getRolesById(Long userId) {

        ArrayList<String> roles = new ArrayList<String>();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "select user_role.userId, sec_role.roleName "
                + "FROM user_role, sec_role "
                + "WHERE user_role.roleId=sec_role.roleId "
                + "AND userId=:userId";
        parameters.addValue("userId", userId);
        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        for (Map<String, Object> row : rows) {
            roles.add((String)row.get("roleName"));
        }
        return roles;
    }

}
