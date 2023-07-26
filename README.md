# 한기대 대즐 선수금 관리 프로그램(DazzleAdvancePaymentManagement)

- 프로젝트 진행 기간(2023-07-13 ~ 2023-07-25)
- 사용된 기술스택 : 스프링부트(Spring Boot), H2 Database, JavaScript, Css, HTML, 타임리프, 부트스트랩
- 개발인원 : 1인

## 개요
한기대 카페 대즐에는 점장님께서 수기로 선수금을 기록하고 계산기로 일일이 값을 계산하여 월간 선수금을 기록하고 계셨다. 한기대 생협의 의뢰를 받아 이를 보다 쉽고 빠르게 기록하고 계산할 수 있는 프로그램을 제작하게 되었다.

## 핵심 기능
- DB데이터 CRUD
- DB데이터를 활용하여 선수금, 판매량 계산
- 계산된 선수금, 판매량과 개인별 선수금 사용내역을 엑셀파일로 다운로드

## 시스템 설계도
<p align="center">
  <img src="https://github.com/SeongUk52/DazzleAdvancePaymentManagement/assets/81956276/bee1dbc1-2fbe-499b-afe8-2016cfacf313">
</p>

## DB 논리적 설계
<p align="center">
  <img src="https://github.com/SeongUk52/DazzleAdvancePaymentManagement/assets/81956276/da4c3ab9-9b14-4e1b-857a-3bf2a85a6163">
</p>
