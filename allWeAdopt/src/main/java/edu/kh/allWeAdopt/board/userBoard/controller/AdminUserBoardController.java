package edu.kh.allWeAdopt.board.userBoard.controller;

import com.google.gson.JsonObject;
import edu.kh.allWeAdopt.board.model.vo.Board;
import edu.kh.allWeAdopt.board.userBoard.model.service.UserBoardService;
import edu.kh.allWeAdopt.board.userBoard.model.vo.Animal;
import edu.kh.allWeAdopt.board.userBoard.model.vo.Area;
import edu.kh.allWeAdopt.common.Util;
import edu.kh.allWeAdopt.member.model.vo.Member;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/board")
@SessionAttributes({"loginMember"})
public class AdminUserBoardController {
	
	@Autowired
	private UserBoardService service;
	
	// 사용자 게시판 리스트 출력
		@GetMapping("/user")
		public String boardList(Model model,HttpSession session,
				@RequestParam(value="boardPeriod", required = false, defaultValue = "") String boardPeriod,
				@RequestParam(value="boardPeriod2", required = false, defaultValue = "") String boardPeriod2,
				@RequestParam(value="category", required = false, defaultValue = "") String category,
				@RequestParam(value="area", required = false, defaultValue = "") String area,
				@RequestParam(value="areaDetail", required = false, defaultValue = "") String areaDetail,
				@RequestParam(value="animalType", required = false, defaultValue = "") String animalType,
				@RequestParam(value="animalDetail", required = false, defaultValue = "") String animalDetail) {
			
			Board board = new Board();
			Member loginMember = (Member)session.getAttribute("loginMember");
			int displayValue = 0;
			
			if(boardPeriod.equals("")) boardPeriod = null;
			board.setBoardPeriod(boardPeriod);
			
			if(boardPeriod2.equals("")) boardPeriod2 = null;
			board.setBoardPeriod2(boardPeriod2);
			
			if(category.equals("") || category.equals("상태여부")) category = null;
			board.setCategory(category);
			
			if(area.equals("") || area.equals("지역선택")) area = null;
			board.setArea(area);
			
			if(areaDetail.equals("") || areaDetail.equals("상세지역 선택")) areaDetail = null;
			board.setAreaDetail(areaDetail);
			
			if(animalType.equals("") || animalType.equals("축종")) animalType = null;
			board.setAnimalType(animalType);
			
			if(animalDetail.equals("") || animalDetail.equals("품종")) animalDetail = null;
			board.setAnimalDetail(animalDetail);
			
			// 지역 리스트 출력
			List<Area> areaList = service.areaList();
			
			// 게시글 리스트 출력
			List<Board> list = new ArrayList<Board>();
			
			if((board.getAnimalDetail()==null)&&(board.getAnimalType()==null)
					&&(board.getArea()==null)&&(board.getAreaDetail()==null)
					&&(board.getBoardPeriod()==null)&&(board.getBoardPeriod2()==null)
					&&(board.getCategory()==null)) {
				list =  service.boardList();
				displayValue = 0;
			}else {
				list = service.searchList(board);
				displayValue = 1;
			}
			
			
			model.addAttribute("areaList",areaList);
			model.addAttribute("boardList",list);
			model.addAttribute("adminMember",loginMember);
			model.addAttribute("searchList",board);
			model.addAttribute("displayValue",displayValue);
			
			return "board/userBoardList";
		}
		
		// 상세 게시판 페이지
		@GetMapping("/detail/2/{boardNo}")
		public String boardDetail(@PathVariable("boardNo") int boardNo, Model model,
				HttpSession session) {
			
			// 게시판 내용 출력
			Board board = service.boardDetail(boardNo);
			Member loginMember = (Member)session.getAttribute("loginMember");
			
			// 관심동물 리스트 출력
			Board likeBoard = new Board();
			int like = 0;
			if(loginMember!=null) {
				likeBoard.setBoardNo(boardNo);
				likeBoard.setMemberNo(loginMember.getMemberNo());
				like = service.likeList(likeBoard);
			}
			
			model.addAttribute("board",board);
			model.addAttribute("adminMember",loginMember);
			model.addAttribute("like",like);
			
			return "board/userBoardDetail";
		}
		
//		썸머노트 이미지 미리보기
		@RequestMapping(value={"/detail/2/{boardNo}/uploadSummernoteImageFile"}, 
				produces = "application/json; charset=utf8")
		@ResponseBody
		public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
			JsonObject jsonObject = new JsonObject();
			
	        /*
			 * String fileRoot = "C:\\summernote_image\\"; // 외부경로로 저장을 희망할때.
			 */
			
			String webPath = "/resources/images/board/";
			
			// 컴퓨터 상에서 webPath까지의 실제 경로를 불러옴
			String fileRoot = request.getSession().getServletContext().getRealPath(webPath);
			
			String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
			String savedFileName = Util.fileRename(originalFileName);
			File targetFile = new File(fileRoot + savedFileName);	
			try {
				InputStream fileStream = multipartFile.getInputStream();
				FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
				jsonObject.addProperty("url", "/resources/images/board/"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
				jsonObject.addProperty("responseCode", "success");
					
			} catch (IOException e) {
				FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
				jsonObject.addProperty("responseCode", "error");
				e.printStackTrace();
			}
			String a = jsonObject.toString();
			return a;
		}
		
		// 상세 지역 호출
		@RequestMapping(value ={"/loadAreaList","/detail/2/{boardNo}/loadAreaList"})
		@ResponseBody
		public List<Area> loadAreaList(@RequestParam("area") String area) {
			List<Area> list = service.loadAreaList(area);
			
			return list;
		}
		
		// 품종 호출
		@RequestMapping(value={"/loadAnimalList","/detail/2/{boardNo}/loadAnimalList"})
		@ResponseBody
		public List<Animal> loadAnimalList(@RequestParam("animalType") String animalType) {
			List<Animal> list = service.loadAnimalList(animalType);
			
			return list;
		}
		
		// 게시글 삭제
		@GetMapping("/detail/2/{boardNo}/boardDelete")
		public String boardDelete(@PathVariable("boardNo") int boardNo,
				RedirectAttributes ra, @RequestHeader("referer") String referer) {
			String path = null;
			String message = "게시글이 삭제되었습니다";
			int result = service.boardDelete(boardNo);
			
			if(result>0) {
				path ="/admin/board/user";
			} else {
				path = referer;
			}
			ra.addFlashAttribute("message",message);
			return "redirect:" + path;
		}
		
		// 게시글 수정 페이지
		@GetMapping("/detail/2/{boardNo}/boardModify")
		public String boardModify(@PathVariable("boardNo") int boardNo,
				@ModelAttribute("loginMember") Member loginMember, Model model) {
			
			// 지역 리스트 출력
			List<Area> areaList = service.areaList();
			model.addAttribute("areaList",areaList);
			
			// 게시판 내용 출력
			Board board = service.boardDetail(boardNo);
			board.setBoardContent(board.getBoardContent().replaceAll("(\r\n|\r|\n|\n\r)", " "));
			model.addAttribute("board",board);
	
			
			return "board/userBoardRegist";
		}
		
		// 게시글 수정
		@PostMapping("/detail/2/{boardNo}/boardModify")
		public String userBoardModify(Board board, Animal animal, Area area,
				HttpSession session, @RequestParam("neuterings") String neuterings,
				@RequestParam("genders") String genders,
				@RequestParam(value="boardPeriod", required = false) String boardPeriod,
				@RequestParam(value="boardPeriod2", required = false) String boardPeriod2,
				@ModelAttribute("loginMember") Member loginMember,
				RedirectAttributes ra, @RequestHeader("referer") String referer) {
			String profileImage = null;
			String category = board.getCategory();
			String path = referer;
			String message = "게시글이 수정되었습니다";
			int boardNo = 0;
			board.setMemberNo(loginMember.getMemberNo());
			boardNo = board.getBoardNo();
		
			if(boardPeriod.equals("")&&boardPeriod2.equals("")&&category.trim().equals("보호")) {
				// 현재날짜 값이 비었을 때 설정
				LocalDate now = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String formatedNow = now.format(formatter);
				boardPeriod = formatedNow;
				board.setBoardPeriod(boardPeriod);
				
				Calendar cal = Calendar.getInstance();
			    cal.setTime(new Date());
			    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    cal.add(Calendar.DATE, +7);
				boardPeriod2 = df.format(cal.getTime());
				board.setBoardPeriod2(boardPeriod2);
			}else if(!category.trim().equals("보호")) {
				board.setBoardPeriod(null);
				board.setBoardPeriod2(null);
			}
			
			// 썸네일 설정
			profileImage = Util.thumbnail(board.getBoardContent());
			board.setProfileImage(profileImage);
			
			// 게시글 등록
			int result = service.userBoardModify(board);
			
			// 게시글 등록 시 번호 얻어오기
			if(result > 0) {
				area.setBoardNo(boardNo);
				result = service.boardAreaModify(area);
			}
			
			if(result> 0) {
				animal.setBoardNo(boardNo);
				
				if(animal.getAnimalType().equals("기타")) {
					animal.setAnimalDetail("기타");
				}
				
				// 성별 설정
				if(genders.equals("수컷")) animal.setGender('M');
				else if(genders.equals("암컷"))animal.setGender('W');
				
				// 중성화 설정
				if(neuterings.equals("완료")) animal.setNeutering('Y');
				else if(neuterings.equals("미완료")) animal.setNeutering('N');
				
				result = service.boardAnimalModify(animal);
				path = "/admin/board/detail/2/"+board.getBoardNo();
			}
			
			ra.addFlashAttribute("message",message);
			return "redirect:" + path;
		}
		
		// 관심동물 등록
		@RequestMapping(value ="/detail/2/likeRegist")
		@ResponseBody
		public int likeRegist(@RequestParam("memberNo") int memberNo,
			@RequestParam("boardNo") int boardNo, @RequestParam("likeNo") int likeNo) {
			Board board = new Board();
			board.setBoardNo(boardNo);
			board.setMemberNo(memberNo);
			int result = service.likeRegist(board,likeNo);
			
			return result;
		}
}
