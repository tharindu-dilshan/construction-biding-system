/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao;

import enums.RoleEnum;
import java.sql.SQLException;
import java.util.List;
import model.Post;

/**
 *
 * @author HaShaN
 */
public interface PostDao {
    public final String INSERT_SQL = "INSERT INTO post(user_id, title, description, budget, date_time, email, contact_no, attachment, attachment_name, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public final String GET_BY_USER_SQL = "SELECT * FROM post WHERE user_id = ? AND status <> ?";
    public final String GET_BY_ID_SQL = "SELECT * FROM post WHERE id = ? AND status <> ?";
    public final String GET_ALL_SQL = "SELECT * FROM post WHERE status <> ?";
    public final String STATUS_UPDATE_SQL = "UPDATE post SET status = ? WHERE id = ?";
    public final String GET_BY_ROLE_SQL = "SELECT * FROM post p INNER JOIN user u ON p.user_id=u.id INNER JOIN role r ON u.role_id=r.id WHERE LOWER(r.role_name)=LOWER(?) AND p.status <> ?";
    
    int addPost(Post post) throws ClassNotFoundException,SQLException;
    Post getById(int postId) throws ClassNotFoundException,SQLException;
    List<Post> getByUser(int userId) throws ClassNotFoundException,SQLException;
    List<Post> getAll() throws ClassNotFoundException,SQLException;
    int updatePostStatus(Post post) throws ClassNotFoundException,SQLException;
    List<Post> getByRole(RoleEnum role) throws ClassNotFoundException,SQLException;
}
