package org.codenova.start.model.animal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item {
    private String desertionNo; // 유기번호
    private String happenDt; // 발생일
    private String happenPlace; // 발생장소
    private String kindFullNm; // 품종 전체 이름
    private String upKindCd; // 대분류 코드
    private String upKindNm; // 대분류 이름
    private String kindCd; // 품종 코드
    private String kindNm; // 품종 이름
    private String colorCd; // 색상
    private String age; // 나이
    private String weight; // 몸무게
    private String noticeNo; // 공고번호
    private String noticeSdt; // 공고 시작일
    private String noticeEdt; // 공고 종료일
    private String popfile1; // 이미지 1
    private String popfile2; // 이미지 2
    private String processState; // 상태
    private String sexCd; // 성별 (M/F)
    private String neuterYn; // 중성화 여부 (Y/N)
    private String specialMark; // 특징
    private String careRegNo; // 보호소 등록번호
    private String careNm; // 보호소 이름
    private String careTel; // 보호소 연락처
    private String careAddr; // 보호소 주소
    private String careOwnerNm; // 보호소 책임자
    private String orgNm; // 담당 기관
    private String updTm; // 정보 업데이트 시간
}
