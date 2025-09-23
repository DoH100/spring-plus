#  Spring Plus 주차 개인 과제
---

## Level 1: 기본 기능 구현 및 오류 수정

### 1. @Transactional 문제 해결
- **문제**: 클래스 전체에 `@Transactional(readOnly = true)`가 적용되어 쓰기 작업 불가능.
- **해결**: `saveTodo()` 메소드에 개별적으로 `@Transactional`을 추가하여 정상 저장 가능하도록 수정.

```java
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional // 쓰기 가능 트랜잭션으로 오버라이딩
    public Todo saveTodo(TodoRequest request, User user) {
        Todo todo = new Todo(request.getTitle(), request.getContents(), user);
        return todoRepository.save(todo);
    }
}
```
### 2. JWT에 닉네임 정보 추가
- **요구사항**: JWT에 `nickname` 포함.
- **해결**:
    - `User` 엔티티에 `nickname` 필드 추가.
    - `JwtUtil`에서 토큰 생성 시 `nickname` 클레임 추가.
    - JWT 필터에서 `nickname` 추출 가능하도록 구현.

---

### 3. JPA 동적 검색
- **요구사항**: `weather`, `modifiedAt`(기간) 조건 검색 지원.
- **해결**:
    - JPQL/Specification 기반 동적 검색 구현.
    - `null` 조건은 WHERE 절에서 제외되도록 처리.
```@Query("SELECT t FROM Todo t " +
       "WHERE (:weather IS NULL OR t.weather = :weather) " +
       "AND (:start IS NULL OR t.modifiedAt >= :start) " +
       "AND (:end IS NULL OR t.modifiedAt <= :end)")
List<Todo> searchTodos(@Param("weather") String weather,
                       @Param("start") LocalDateTime start,
                       @Param("end") LocalDateTime end);
 ```
---

### 4. 컨트롤러 테스트 코드 수정
- **문제**: 존재하지 않는 ID 조회 시 테스트 실패.
- **해결**:
    - `given(...).willThrow(...)`로 예외 설정.
    - `andExpect(status().isBadRequest())` 검증 추가.

---

### 5. AOP Pointcut 수정
- **문제**: `UserAdminController.changeUserRole()` 실행 전 로그가 동작하지 않음.
- **해결**:
    - Pointcut을 전체 패키지 경로로 지정하여 정상 동작하도록 수정.

---

## Level 2: 심화 기능 및 성능 개선

### 6. JPA Cascade
- **요구사항**: 할 일 생성 시 생성자가 담당자로 자동 등록.
- **해결**:
    - `@OneToMany(cascade = CascadeType.PERSIST)` 적용.
    - Todo 저장 시 Manager도 자동 저장.

---

### 7. N+1 문제 해결
- **문제**: 댓글 조회 시 User 연관 정보로 인한 N+1 발생.
- **해결**:
    - `JOIN FETCH` 적용하여 User 함께 로딩.

---

### 8. JPQL → QueryDSL 전환
- **요구사항**: `findByIdWithUser`를 QueryDSL로 리팩터링.
- **해결**:
    - `JPAQueryFactory`와 Q-Type 활용.
    - 타입 안정성 확보 및 N+1 방지.
```
QTodo todo = QTodo.todo;
QUser user = QUser.user;

return queryFactory.selectFrom(todo)
        .join(todo.user, user).fetchJoin()
        .where(todo.id.eq(todoId))
        .fetchOne();
```
---

### 9. Spring Security 전환
- **요구사항**: 기존 `Filter` + `Argument Resolver` 인증/인가를 Spring Security로 교체.
- **해결**:
    - `SecurityConfig` 생성, `SecurityFilterChain` 등록.
    - JWT 인증 필터 추가, 세션 비활성화.
    - `authorizeHttpRequests()`로 접근 권한 제어.
    - 권한 검증을 Spring Security 표준 방식으로 전환.

---

## 🚀 정리
- **Level 1**: @Transactional 오류 해결, JWT 확장, JPA 동적 검색, 테스트 코드 수정, AOP 수정
- **Level 2**: Cascade 자동 저장, N+1 해결, QueryDSL 전환, Spring Security 도
