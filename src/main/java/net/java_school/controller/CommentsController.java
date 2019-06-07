package net.java_school.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import net.java_school.board.BoardService;
import net.java_school.board.Comment;
import net.java_school.user.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
			String name = this.userService.findUser(email).getNickname();
			comment.setName(name);
		}

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		boolean isAdmin = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
		boolean isUser = authorities.contains(new SimpleGrantedAuthority("ROLE_USER"));

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