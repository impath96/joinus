package com.joinus.persistence;


import java.util.List;
import java.util.Map;

import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubGradesVo;
import com.joinus.domain.ClubMeetingsVo;
import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.BoardCommentsVo;
import com.joinus.domain.BoardCriteria;
import com.joinus.domain.BoardLikesVo;
import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.Criteria;
import com.joinus.domain.MeetingTotalBean;
import com.joinus.domain.MembersVo;
import com.joinus.domain.RentalPlacesVo;
import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubMeetingsVo;
import com.joinus.domain.InterestDetailsVo;
import com.joinus.domain.InterestsVo;

public interface ClubDao {
	
	//=========================고은비=========================
	//클럽회원 리스트
	public List<ClubTotalBean> clubMemberList(int club_no);
	
	//클럽 전체 개수 조회
	public Integer getTotalCnt(Integer interest_no);
	public Integer getTotalCnt();
	
	//클럽리스트(관심사o)
	public List<ClubTotalBean> clubList(int interest_no, Criteria cri);

	//클럽리스트(관심사x)
	public List<ClubTotalBean> clubList(Criteria cri);
	
	//클럽 정보
	public List<ClubsVo> clubInfo(int club_no);
	
	//모임 회원 권한 가져오기
	public Integer clubRole(int club_no, int member_no);
	
	//클럽 강퇴
	public void clubBan(int member_no, int club_no);
	
	//모임장 양도
	public void clubAuth(Integer member_no, int club_no);
	
	//클럽 나가기
	public void clubLeave(MembersVo member, Integer club_no);
	
	//클럽 정보 수정
	public void clubUpdate(ClubsVo clubsvo, Integer club_no);
	
	//예약정보 가져오기 - LIST
	public List<MeetingTotalBean> getRental(int member_no);
	
	//예약정보 가져오기 - REST
	public List<MeetingTotalBean> getRentalREST(int rental_places_no);
	
	//정모 만들기
	public void createMeeting(ClubMeetingsVo vo);
	
	//정모 정보 가져오기
	public List<ClubMeetingsVo> getMeetings_no(Integer club_meeting_no);
	
	//정모 수정하기
	public Integer updateMeeting(Integer club_meeting_no, ClubMeetingsVo vo);

	
	//=========================고은비=========================
	
	
	//=========================허수빈========================
	// 글쓰기
	public void writeBoard(ClubBoardsVo vo);
	
	// 모임고유값에 따른 게시글리스트
	public List<BoardTotalBean> getBoardListAll(Integer club_no, BoardCriteria cri);
	
	public Integer getTotalBoardCnt(int club_no);
	
	public List<BoardTotalBean> getBoardList(Integer club_no, Integer board_type_no, BoardCriteria cri);
	
	public Integer getTypeBoardCnt(int club_no, int board_type_no);
	
	public List<ClubBoardsVo> getBoardImageList(Integer club_no);
	
	// 게시글 본문
//	public ClubBoardsVo getBoardContent(Integer club_board_no);
	public BoardTotalBean getBoardContent(Integer club_board_no);
	
	// 게시글 수정
	public void modifyBoardContent(ClubBoardsVo vo);
	
	// 게시글 삭제
	public void deleteBoard(Integer club_board_no);
	
	// 댓글 등록
	public void writeComment(BoardCommentsVo vo);
	
	// 댓글수
	public int getCommentCnt(int club_board_no);
	
	// 댓글 출력
	public List<BoardTotalBean> getCommentList(int club_board_no);
	
	// 댓글수 + 1
	public void updateCommentCnt(int club_board_no);
	
	// 댓글 등록
	public void updateComment(BoardCommentsVo vo);
	
	// 댓글 삭제
	public void deleteComment(int board_comment_no);
	
	// 댓글수 - 1
	public void decreaseCommentCnt(int club_board_no);
	
	// 좋아요수
	public int getLikeCnt(int club_board_no);
	
	// 좋아요 눌렀는지 확인(1:좋아요O / 0:좋아요X)
	public int checkLike(int club_board_no, int member_no);
	
	// 좋아요 멤버 리스트
	public List<BoardTotalBean> getLikeList(int club_board_no);
	
	// 좋아요 등록
	public void insertLike(BoardLikesVo vo);
	
	// 좋아요수 + 1
	public void increaseLikeCnt(int club_board_no);
	
	// 좋아요 취소
	public void cancelLike(int club_board_no, int member_no);
	
	// 좋아요수 - 1
	public void decreaseLikeCnt(int club_board_no);
	
	//=========================허수빈========================

	//=========================김민호========================
	
	// 내 모임 리스트
	public List<ClubsVo> ClubListByMemberNo(int member_no);

	// 나가 만든 모임 리스트
	public List<ClubsVo> myClubList(int member_no);
	
	//=========================김민호========================
	
	
	
	//=========================강성민========================
	
	// 회원정보
	public MembersVo getMember(Integer num);
	// 회원관심사
	public InterestsVo interest(Integer num);
		
	// 세부관심사리스트 가져오기
	public List<InterestDetailsVo> getDetailName(Integer num);
		
	// 회원이 선택한 관심사 넘버값 가져오기
	public InterestDetailsVo getInterestNo(String name);
	
	// 모임 이름 중복체크
	public ClubsVo checkClubName(String name);
	
	// 모임생성
	public void newClub(ClubsVo vo);
	// 모임관심사 저장
	public void newClubInterest(Integer club_no, Integer interest_no,Integer interest_detail_no);
	// 모임가입하기
	public void join(ClubMembersVo members);
	// 모임별점주기
	public void clubGrade(ClubGradesVo vo);
	// 모임 별점 정보 가져오기
	public List<ClubGradesVo> getClubGrade(Integer num);
	// 모임 별점 참여자 가져오기
	public Integer getGradeinfo(Integer num, Integer num2);
	// 모임 별점 평균값, 참여자 수 가져오기
	public List<Map<String, Integer>> getClubAvgCnt(Integer num);
		
	// 모임 정보 가져오기
	public ClubsVo getClubInfo(Integer num);
	// 모임 관심사 가져오기
	public String getClubInterestDName(Integer num);
	// 모임 회원 정보 가져오기(리스트)
	public List<ClubMembersVo> getClubMembers(Integer num);
	// 모임 회원 정보 가져오기(특정)
	public ClubMembersVo getClubMemberNo(Integer num,Integer num2);
	
	// 모임 찜하기
	public void clubDip(Integer num,Integer num2);
	// 모임 찜 여부 확인
	public List<Integer> dip(Integer num);
	// 모임 찜 취소
	public void dipX(Integer num,Integer num2);
	
	//정모 리스트
	public List<ClubMeetingsVo> getMeetings(Integer num);
	//게시글 리스트
	public List<ClubBoardsVo> getBoards(Integer num);

	
	//=========================강성민========================
	
	
	
}
