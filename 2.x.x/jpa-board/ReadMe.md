# JPA 실습예제  

<pre>
Springboot 2.x.x 버전(Gradle)에서 QueryDSL 설정과 기초적인 JPA활용이 실습이 목표인 예제입니다.
기본적인 CRUD 기능을 실습하기 위해 게시판 기능을 구현했습니다.
게시판과 댓글의 작성 기능에 사용된 텍스트 에디터는 Toast-Ui-Editor를 뢀용했습니다.
</pre>

<h3>기능 목록</h3>
<ul style="list-style: square">
    <li>게시글 작성 기능</li>
    <li>게시글 조회 기능</li>
    <li>게시글 수정 기능</li>
    <li>게시글 삭제 기능</li?>
</ul>
<ul style="list-style: square">
    <li>댓글 작성 기능</li>
    <li>댓글 조회 기능</li>
</ul>

<h3>Board.java 의 SQL</h3>
<pre>
DROP TABLE IF EXISTS `board`;
CREATE TABLE `board` (
  `boardIdx` bigint NOT NULL AUTO_INCREMENT,
  `boardTitle` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `boardAuthor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'guest',
  `boardContent` longblob,
  `boardDelete` int NOT NULL DEFAULT '0' COMMENT '0=공개, 1=비공개, 2=삭제대기',
  `boardCreatedDate` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `boardModifiedDate` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`boardIdx`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
</pre>

<h3>BoardComment.java 의 SQL</h3>
<pre>
DROP TABLE IF EXISTS `boardcomment`;
CREATE TABLE `boardcomment` (
  `boardCommentIdx` bigint NOT NULL AUTO_INCREMENT,
  `boardCommentAuthor` varchar(255) NOT NULL DEFAULT 'guest',
  `boardCommentContent` longblob,
  `boardCommentDelete` int NOT NULL DEFAULT '0',
  `boardCommentModifiedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `boardCommentCreatedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `boardIdx` bigint DEFAULT NULL,
  PRIMARY KEY (`boardCommentIdx`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
</pre>