## 뉴스 데이터 parsing flow
- - -
### New 데이터
- 지정된 폴더에 *.xml 파일이 들어오면 생성 감지를 통해 메서드 동작
- StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY 통해 생성, 변경 감지
  - xml -> Json parsing 
  - contentID 값을 통해 지정된 위치에 AppendData 관리 폴더 생성
  - db를 contentID로 content 테이블 조회 -> 존재하지 않으면 history, content 테이블에 insert
  - 이미 존재하는 content ->  history 테이블에 insert
  - 첨부파일을 contentID 값을 통해 생성된 폴더에 저장
### Delete 데이터
- Controller 통해 요청
  - history 테이블을 통한 논리적 삭제 진행





