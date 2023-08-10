package com.together.springStudy.service;

import com.together.springStudy.mapper.PostMapper;
import com.together.springStudy.model.*;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    //    컨트롤러에서 서비스 객체와 여기서 private final을 해주지 않으면
    //    Bean주입이 안된다고함. -> NullPointExection 뜸 bean 공부해보기..

//    다시, controller에서 service 객체는 final 안해줘도 되는데,
//    매퍼 객체는 final로 안하면 nullPointExcection 뜸.
    @Autowired
    PostMapper postMapper;

//    post 관련
    @Transactional
    public List<PostMainData> getAllPostForMain(int userId){ return postMapper.getAllPostForMain(userId); }

    @Transactional
    public PostMainData getPostByPrimaryKey(PostId postId){ return postMapper.getPostByPrimaryKey(postId); }

    @Transactional
    public List<PostMainData> getPostByBoardId(BoardId boardId){ return postMapper.getPostByBoardId(boardId); }

    @Transactional
    public BoardId getBoardIdByClubId(int clubId){ return postMapper.getBoardIdByClubId(clubId); }

    @Transactional
    public List<PostMainData> getPostForKeyword(Keyword keyword){ return postMapper.getPostForKeyword(keyword); }

    @Transactional
    public Integer createPost(PostsData postsData){ return postMapper.createPost(postsData); }

    @Transactional
    public List<PostMainData> getPostsByUserId(int userId){ return postMapper.getPostsByUserId(userId); }


//    댓글 관련
    @Transactional
    public int createComment(PostComment postComment){ return postMapper.createComment(postComment); }

    @Transactional
    public int createReply(PostComment postComment){ return postMapper.createReply(postComment); }

    @Transactional
    public List<PostCommentData> getAllCommentByPostId(int postId){ return postMapper.getAllCommentByPostId(postId);}


//    좋아요 관련
    @Transactional
    public int createLike(PostLike postLike){ return postMapper.createLike(postLike);}

    @Transactional
    public PostLike getPostLike(PostLike postLike){ return postMapper.getPostLike(postLike);}

    @Transactional
    public int deleteLike(PostLike postLike){ return postMapper.deleteLike(postLike);}

//    게시판 관련
    @Transactional
    public int createBoardData(BoardData boardData){ return postMapper.createBoardData(boardData); }
}
