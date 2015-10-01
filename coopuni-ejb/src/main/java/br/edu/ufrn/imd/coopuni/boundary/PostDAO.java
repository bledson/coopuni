package br.edu.ufrn.imd.coopuni.boundary;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import br.edu.ufrn.imd.coopuni.model.Post;

public class PostDAO implements AbstractDAO<Long, Post> {

	@Inject
	private EntityManager em;

	@Override
	public void create(Post entity) {
		em.persist(entity);
	}

	@Override
	public void delete(Long id) {

	}

	@Override
	public Post retrieve(Long id) {
		return em.find(Post.class, id);
	}

	@Override
	public void update(Post entity) {
		
	}
	
	public void updateVote(String type, long postID){
		Post post = this.retrieve(postID);
		int likes = post.getLikes();
		int down = post.getDownvotes();
		
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaUpdate<Post> update = cb.createCriteriaUpdate(Post.class);
		Root<Post> e = update.from(Post.class);		
		
		if(type.equals("up")) {
			update.set("likes", likes+1);
		}
		else { 
			if(type.equals("down")) {
				update.set("downvotes", down+1);
			}
		}
		
		update.where(cb.equal(e.get("id"), postID));
		this.em.createQuery(update).executeUpdate();
	}
	
	
	public List<Post> getPostsByUserId(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> c = cb.createQuery(Post.class);
		Root<Post> post = c.from(Post.class);
		c.select(post).where(cb.equal(post.get("member").get("id"), id));
		return em.createQuery(c).getResultList();
	}
	
	public List<Post> getAllPosts() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> c = cb.createQuery(Post.class);
		Root<Post> post = c.from(Post.class);
        CriteriaQuery<Post> all = c.select(post);
        TypedQuery<Post> allQuery = em.createQuery(all);
        return allQuery.getResultList();
	}

}
