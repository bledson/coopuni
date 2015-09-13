package br.edu.ufrn.imd.coopuni.controller;

import br.edu.ufrn.imd.coopuni.model.Post;
import br.edu.ufrn.imd.coopuni.service.PostService;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

@Model
public class PostController {
  @Inject
  private FacesContext facesContext;

  @Inject
  private PostService postService;

  private List<Post> posts;
  private Post post;

  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public String register() throws Exception {
    try {
      postService.register(post);
      facesContext.addMessage(null,
          new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado!", "Registro feito com sucesso"));
      post = new Post();
      return "sucess";
    } catch (Exception e) {
//	      String errorMessage = getRootErrorMessage(e);
//	      FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registro sem sucesso");
//	      facesContext.addMessage(null, m);
    }
    return null;
  }

  public List<Post> getAllPost() throws Exception {
    try {
      posts = postService.getAll();
      return posts;
    } catch (Exception e) {
//			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, 
//					"Houve um erro ao tentar buscar as publicações");
//		    facesContext.addMessage(null, m);
    }
    return null;
  }


}