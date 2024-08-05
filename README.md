### 뉴스 데이터 parsing flow
- - -
1. New 데이터
- 지정된 폴더에 *.xml 파일이 들어오면 생성 감지를 통해 메서드 동작
- StandardWatchEventKinds.ENTRY_CREATE 통해 생성감지
- xml -> Json parsing 후 필요한 데이터 news db에 저장 
  - contentIdD 값으로 DB조회 null check -> null 아니면 history 테이블 insert
  - null 이라면 history 밑 content 테이블 insert
- appendData(첨부파일) contentID 값으로 폴더 생성 후 저장
  - 이미 폴더가 존재하면 
2. Update 데이터
- StandardWatchEventKinds.ENTRY_MODIFY 통해 파일 변경 감지
- 변경된 파일 parsing 후 이력관리 db에 insert, 추가된 appendData(첨부파일) 저장

3. Delete 데이터
- StandardWatchEventKinds.ENTRY_DELETE 통해 파일 삭제 감지
- 이력 테이블 update




