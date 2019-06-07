package net.java_school.board;

import java.util.HashMap;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

public interface BoardService {

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_GOOGLE')")
	public Board getBoard(String boardCd);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_GOOGLE')")
	public List<Board> getBoards();

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void createBoard(Board board);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void editBoard(Board board);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_GOOGLE')")
	public List<Article> getArticleList(HashMap<String, String> hashmap);

	public int getTotalRecord(String boardCd, String searchWord);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public int addArticle(Article article);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public void addAttachFile(AttachFile attachFile);
	
	@PreAuthorize("(hasRole('ROLE_USER') and #article.email == principal.email) or hasRole('ROLE_ADMIN')")
	public void modifyArticle(Article article);

	@PreAuthorize("(hasRole('ROLE_USER') and #article.email == principal.email) or hasRole('ROLE_ADMIN')")
	public void removeArticle(Article article);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_GOOGLE')")
	public void increaseHit(Integer articleNo, String ip, String yearMonthDayHour);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_GOOGLE')")
	public Article getArticle(int articleNo);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_GOOGLE')")
	public Article getNextArticle(int articleNo,
			String boardCd, String searchWord);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_GOOGLE')")
	public Article getPrevArticle(int articleNo,
			String boardCd, String searchWord);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_GOOGLE')")
	public List<AttachFile> getAttachFileList(int articleNo);

	@PreAuthorize("(hasRole('ROLE_USER') and #attachFile.email == principal.email) or hasRole('ROLE_ADMIN')")
	public void removeAttachFile(AttachFile attachFile);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public void addComment(Comment comment);

	@PreAuthorize("(hasRole('ROLE_USER') and #comment.email == principal.email) or hasRole('ROLE_ADMIN')")
	public void modifyComment(Comment comment);

	@PreAuthorize("(hasRole('ROLE_USER') and #comment.email == principal.email) or hasRole('ROLE_ADMIN')")
	public void removeComment(Comment comment);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_GOOGLE')")
	public List<Comment> getCommentList(int articleNo);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_GOOGLE')")
	public AttachFile getAttachFile(int attachFileNo);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public Comment getComment(int commentNo);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void setEditableTrue(List<Comment> comments);

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_GOOGLE')")
	public int getTotalViews(int articleNo);

}