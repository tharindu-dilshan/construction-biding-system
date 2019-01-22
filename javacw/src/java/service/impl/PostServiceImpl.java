/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package service.impl;

import dao.PostDao;
import dao.impl.PostDaoImpl;
import enums.RoleEnum;
import java.sql.SQLException;
import java.util.List;
import model.Post;
import service.PostService;

/**
 *
 * @author HaShaN
 */
public class PostServiceImpl implements PostService{
    private final PostDao postDao = new PostDaoImpl();
    
    @Override
    public boolean addPost(Post post) throws ClassNotFoundException,SQLException {
        return postDao.addPost(post) > 0;
    }
    
    @Override
    public Post getById(int postId) throws ClassNotFoundException, SQLException {
        return postDao.getById(postId);
    }
    
    @Override
    public List<Post> getByUser(int userId) throws ClassNotFoundException, SQLException {
        return postDao.getByUser(userId);
    }
    
    @Override
    public List<Post> getAll() throws ClassNotFoundException, SQLException {
        return postDao.getAll();
    }

    @Override
    public boolean deletePost(Post post) throws ClassNotFoundException, SQLException {
        return postDao.updatePostStatus(post) > 0;
    }

    @Override
    public List<Post> getByRole(RoleEnum role) throws ClassNotFoundException, SQLException {
        return postDao.getByRole(role);
    }
    
}
