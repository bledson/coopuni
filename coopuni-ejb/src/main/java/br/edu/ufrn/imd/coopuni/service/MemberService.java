package br.edu.ufrn.imd.coopuni.service;

import br.edu.ufrn.imd.coopuni.boundary.MemberDAO;
import br.edu.ufrn.imd.coopuni.model.Member;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

@Stateless
public class MemberService {

  @Inject
  private MemberDAO memberDAO;

  @Inject
  private Event<Member> memberEventSrc;

  @Inject
  private Logger log;

  public void register(Member member) throws Exception {
    log.info("Registrando " + member.getUsername());
    memberDAO.create(member);
    memberEventSrc.fire(member);
  }
  
  public Member retrive(long id) {
	  return memberDAO.retrieve(id);
  }
  
  public boolean checkLogin(String username, String password) throws NoSuchAlgorithmException {
	  return memberDAO.checkLogin(username,password);
  }
}
