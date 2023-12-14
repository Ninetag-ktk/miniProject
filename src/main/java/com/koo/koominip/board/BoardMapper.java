package com.koo.koominip.board;

import java.util.List;

public interface BoardMapper {
	public abstract int deleteComment(BoardComment comment);
	public abstract int deletePost(BoardPost post);
	public abstract int countAllPost();
	public abstract int countPost(BoardSelector selector);
	public abstract List<BoardComment> getComment(BoardPost post);
	public abstract List<BoardPost> getPost(BoardSelector selector);
	public abstract int writeComment(BoardComment comment);
	public abstract int writePost(BoardPost post);
	public abstract int updatePost(BoardPost post);
}
