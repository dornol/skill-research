# Spring Data Envers 테스트

1. entity 등록, 수정, 삭제 시 audit 테이블에 기록된다.
2. revinfo 테이블에 revisionDate 등의 정보 설정가능 (@RevisionEntity)
3. Super Class 의 속성도 기록하고 싶으면 Super Class 에 @Audited 설정한다.

## revinfo
- rev: PK

## *_his
- revtype: 등록(0), 수정(1), 삭제(2)