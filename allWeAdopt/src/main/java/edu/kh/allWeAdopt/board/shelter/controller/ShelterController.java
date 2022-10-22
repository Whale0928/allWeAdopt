package edu.kh.allWeAdopt.board.shelter.controller;


import edu.kh.allWeAdopt.board.shelter.model.vo.Shelter;
import org.apache.commons.codec.binary.Base64;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shelter")
@SessionAttributes({"list"})
public class ShelterController {
	
	private final Logger logger = LoggerFactory.getLogger(ShelterController.class);

	
	
	// 보호소 불러오기
	@GetMapping("/shelterList")
	public String openShelter(Model model, @RequestParam(value="pageNo", required=false, defaultValue = "1") String pageNo,
								@RequestParam(value="upkind", required=false, defaultValue = "")String upkind, 
								@RequestParam(value="upr_cd", required=false, defaultValue = "") String upr_cd
								, HttpServletResponse resp
								/* @RequestParam(value="org_cd", required=false, defaultValue = " ") String org_cd */) throws Exception {

		/*URL*/
		String urlBuilder = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic" + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=ORROf4zK3CwwkwCJ%2Fdt4FRDISXLZQ5w7%2B13CMxRIzMqZz504Vtr86DcBmJqTMgWJgCVfGnafTLBY6kHXgi9CIw%3D%3D" + /*Service Key*/
				"&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pageNo, StandardCharsets.UTF_8) + /*페이지 번호*/
				"&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("9", StandardCharsets.UTF_8) + /*한 페이지 결과 수(1,000 이하)*/
				"&" + URLEncoder.encode("upkind", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(upkind, StandardCharsets.UTF_8) + /*축종코드*/
				"&" + URLEncoder.encode("upr_cd", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(upr_cd, StandardCharsets.UTF_8); /*시도코드*/
//	    urlBuilder.append("&" + URLEncoder.encode("org_cd","UTF-8") + "=" + URLEncoder.encode(org_cd, "UTF-8")); /*시군구코드*/
	    
//	    urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode(" ", "UTF-8")); /*xml(기본값) 또는 json*/
	       
		logger.info("유기견 목록 조회 수행");
	    
	    URL url = new URL(urlBuilder);
	       
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
	    conn.setRequestProperty("Content-type", "application/xml");
	       
	    conn.connect();
	    	        
	    System.out.println("Response code: " + conn.getResponseCode());
	    
	    SAXBuilder builder = new SAXBuilder();
	    
	    Document document = builder.build(conn.getInputStream());
	    
	    Element root = document.getRootElement();
	    
	    Element body = root.getChild("body");
	    
	    Element items = body.getChild("items");
	    
	    List<Element> itemList = items.getChildren("item");
	    
	    List<Shelter> list = new ArrayList<Shelter>();
    	
    		
		for(Element item : itemList) {
			
			String desertionNo = item.getChildText("desertionNo");
			String filename = item.getChildText("filename");
			String happenDt = item.getChildText("happenDt");
			String happenPlace = item.getChildText("happenPlace");
			String kindCd = item.getChildText("kindCd");
			String colorCd = item.getChildText("colorCd");
			String age = item.getChildText("age");
			String weight = item.getChildText("weight");
			String popfile =  "data:image/png;base64," + getBase64EncodedImage(item.getChildText("popfile"));
			
			
			String noticeSdt = item.getChildText("noticeSdt");
			String noticeEdt = item.getChildText("noticeEdt");
			String processState = item.getChildText("processState");
			String sexCd = item.getChildText("sexCd");
			String specialMark = item.getChildText("specialMark");	        
			String careNm = item.getChildText("careNm");
			String careAddr = item.getChildText("careAddr");
			String careTel = item.getChildText("careTel");
			
			String numOfRows = item.getChildText("numOfRows");
//			pageNo = item.getChildText("pageNo");
			String totalCount = item.getChildText("totalCount");
			
			Shelter shelter = new Shelter();
			
			shelter.setDesertionNo(desertionNo);
			shelter.setFilename(filename);
			shelter.setHappenDt(happenDt);
			shelter.setHappenPlace(happenPlace);
			shelter.setKindCd(kindCd);
			shelter.setColorCd(colorCd);
			shelter.setAge(age);
			shelter.setWeight(weight);
			shelter.setPopfile(popfile);
			shelter.setNoticeSdt(noticeSdt);
			shelter.setNoticeEdt(noticeEdt);
			shelter.setProcessState(processState);
			shelter.setSexCd(sexCd);
			shelter.setSpecialMark(specialMark);
			shelter.setCareNm(careNm);
			shelter.setCareAddr(careAddr);
			shelter.setCareTel(careTel);
			shelter.setNumOfRows(numOfRows);
//			shelter.setPageNo("10");
			shelter.setTotalCount(totalCount);
			
			
			list.add(shelter);

    	}
	    
	    
//	    System.out.println(shelter.getNumOfRows());
//	    System.out.println(list.get(1));
//		System.out.println(pageNo);
	    
	    model.addAttribute("list", list);	    
	    model.addAttribute("pageNo", pageNo);	    
	    model.addAttribute("upkind", upkind);	    
	    model.addAttribute("upr_cd", upr_cd);	    
//	    model.addAttribute("org_cd", org_cd);	    

//	    resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
		return "shelter/shelter-main";
	}
	
	// 보호소 상세 조회
	@GetMapping("shelterDetail/{desertionNo}")
	public String shelterDetail(@PathVariable("desertionNo") String desertionNo) {
		
//		System.out.println(desertionNo);
		
		logger.info("보호소 상세 조회 수행");
		
		return "shelter/shelter-Detail";
	}
	
	
    public static String getBase64EncodedImage(String imageURL) throws IOException {
        java.net.URL url = new java.net.URL(imageURL);
        InputStream is = url.openStream();
        byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(is);
        return Base64.encodeBase64String(bytes);
    }
}
