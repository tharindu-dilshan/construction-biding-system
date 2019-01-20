/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package service;

import enums.RoleEnum;
import java.sql.SQLException;
import java.util.List;
import model.Post;

/**
 *
 * @author HaShaN
 */
public interface PostService {
    boolean addPost(Post post) throws ClassNotFoundException,SQLException;
    Post getById(int postId) throws ClassNotFoundException,SQLException;
    List<Post> getByUser(int userId) throws ClassNotFoundException,SQLException;
    List<Post> getAll() throws ClassNotFoundException,SQLException;
    boolean deletePost(Post post) throws ClassNotFoundException,SQLException;
    List<Post> getByRole(RoleEnum role) throws ClassNotFoundException,SQLException;
}
