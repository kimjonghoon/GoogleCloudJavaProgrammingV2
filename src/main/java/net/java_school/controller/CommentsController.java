package net.java_school.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import net.java_school.board.BoardService;
import net.java_school.board.Comment;
import net.java_school.user.GaeUser;
import net.java_school.user.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentsController {

	private BoardService boardService;
	private UserService userService;

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/{articleNo}", method = RequestMethod.GET)
	public List<Comment> getAllComments(@PathVariable Integer articleNo, Principal principal, Authentication authentication) {
		List<Comment> comments = boardService.getCommentList(articleNo);
		
		for(Comment comment : comments) {
			String email = comment.getEmail();
			String name = null;
			
			//For robust local test environment
			GaeUser owner = this.userService.findUser(email);
			if (owner == null) {
				name = email;
			} else {
				name = owner.getNickname();
			}
			
			comment.setName(name);
		}

		boolean isAdmin = false;
		boolean isUser = false;

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			String role = authority.getAuthority();
			if (role.equals("ROLE_USER")) {
				isUser = true;
			}
			if (role.equals("ROLE_ADMIN")) {
				isAdmin = true;
			}
		}

		if (isUser) {
			String username = principal.getName();
			for (Comment comment : comments) {
				if (comment.getEmail().equals(username)) {
					comment.setEditable(true);
				}
			}
		}
		
		if (isAdmin) {
			boardService.setEditableTrue(comments);
		}

		//for security reasons
		for (Comment comment : comments) {
			comment.setEmail(null);
		}

		return comments;
	}

	@RequestMapping(value = "/{articleNo}", method = RequestMethod.POST)
	public void addComment(String memo, @PathVariable Integer articleNo, Principal principal) {
		Comment comment = new Comment();
		comment.setMemo(memo);
		comment.setArticleNo(articleNo);
		comment.setEmail(principal.getName());

		boardService.addComment(comment);
	}

	@RequestMapping(value = "/{articleNo}/{commentNo}", method = RequestMethod.PUT)
	public void updateComment(String memo, @PathVariable Integer articleNo, @PathVariable Integer commentNo, Principal principal) {
		Comment comment = boardService.getComment(commentNo);
		comment.setMemo(memo);
		boardService.modifyComment(comment);
	}

	@RequestMapping(value = "/{articleNo}/{commentNo}", method = RequestMethod.DELETE)
	public void deleteComment(@PathVariable Integer articleNo, @PathVariable Integer commentNo) {
		Comment comment = boardService.getComment(commentNo);
		boardService.removeComment(comment);
	}

}