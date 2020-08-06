<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <img alt="license" src="https://img.shields.io/github/license/woowacourse/atdd-subway-2020">
</p>

<br>

# 레벨2 최종 미션 - 지하철 노선도 애플리케이션

## 🎯 요구사항
- [프론트엔드 미션](https://github.com/woowacourse/atdd-subway-2020/blob/master/frontend-mission.md)
- [백엔드 미션](https://github.com/woowacourse/atdd-subway-2020/blob/master/backend-mission.md)

## 😌 레벨2 최종 미션을 임하는 자세
레벨2 과정을 스스로의 힘으로 구현했다는 것을 증명하는데 집중해라
- [ ] 기능 목록을 잘 작성한다.  
- [ ] 자신이 구현한 기능에 대해 인수 테스트를 작성한다.
- [ ] 자신이 구현한 코드에 대해 단위 테스트를 작성한다.
- [ ] TDD 사이클 이력을 볼 수 있도록 커밋 로그를 잘 작성한다.
- [ ] 사용자 친화적인 예외처리를 고민한다.
- [ ] 읽기 좋은 코드를 만든다.

## 백엔드 프로그래밍 요구사항
1. 인수 테스트 작성
2. 경로 조회 기능의 문서화
3. 기능 구현시 TDD 활용(커밋 단위로 TDD 사이클 확인)

## 백엔드 기능목록
1. 경로 조회 응답 결과에 요금 정보 추가(필수)
    - [ ] 경로 조회 응답 결과에 요금 정보 추가
        - [x] 거리에 따라 요금을 부과한다 
        - [x] 라인에 따라 추가 요금을 부과한다
        - [ ] 회원에 따라 알맞은 할인을 제공한다
2. 가장 빠른 경로 도착 경로 타입 추가(선택)
    - 1번 기능목록 달성 시 추가 예정
    
## 프론트엔드 기능목록
1. 백엔드 요금 조회 api를 사용할 수 있도록 연동
    - [ ] 백엔드에서 구현한 path 검색 api의 endpoint 추가
2. 템플릿 리터럴을 이용해 시간 보기 좋게 렌더링
3. validator를 구현해 form의 유효성 검사
    - [ ] path와 departureTime form 유효성 검사
    - [ ] path form에서 유효성 검사
        - [ ] source, target id 값 (양의 정수인지 등)
    - [ ] departure Time form에서 유효성 검사
        - [ ] dayTime: '오전' or '오후' 텍스트 값
        - [ ] hour: 1~12 의 정수인지
        - [ ] minute: 0~60의 정수인
4. 길찾기를 위해 사용자가 입력한 값을 이용해 검색 결과를 불러오는 핸들러 구현
    - [ ] 요금조회 버튼에 대한 이벤트 핸들러 구현